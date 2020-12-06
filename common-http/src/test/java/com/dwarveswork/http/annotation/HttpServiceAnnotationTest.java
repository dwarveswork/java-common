package com.dwarveswork.http.annotation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dwarveswork.exception.RemoteException;
import com.dwarveswork.exception.RemoteExecutionException;
import com.dwarveswork.exception.RemoteTimeoutException;
import com.dwarveswork.http.HttpBinRequest;
import com.dwarveswork.http.HttpBinResponse;
import com.dwarveswork.http.HttpServiceFactory;
import com.dwarveswork.lang.Strings;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class HttpServiceAnnotationTest {

  private static final String TEST_PARAM = "key";
  private static final String TEST_HEADER = "X-Header";

  private HttpBinService service = HttpServiceFactory.createProxy(HttpBinService.class, "https://httpbin.org/");
//
//  @Test
//  void testGet404() {
//    assertThrows(HttpException.class, () -> service.get404());
//  }

  @Test
  void testTimeout() {
    assertThrows(RemoteTimeoutException.class, () -> service.getTimeout());
  }

  @Test
  void testVariable() {
    String value = Strings.randomAlphanumeric(20);
    assertThrows(RemoteExecutionException.class, () -> service.auth("auth", ImmutableMap.of("user", "user", "password", value)));
  }

  @Test
  void testGetString() {
    String value = Strings.randomAlphabetic(10);
    String header = Strings.randomAlphanumeric(20);
    String content = service.get(ImmutableMap.of(TEST_PARAM, value), header);
    assertTrue(content.contains("\"" + value + "\""));
    assertTrue(content.contains("\"" + header + "\""));
  }

  @Test
  void testGetObjectWithHeaders() {
    String value = Strings.randomAlphabetic(10);
    String header = Strings.randomAlphabetic(10);
    HttpBinResponse content = service.get(value, ImmutableMap.of(TEST_HEADER, header));
    assertTrue(content.getArgs().containsValue(value));
    assertTrue(content.getHeaders().containsValue(header));
  }

  @Test
  void testPostBytes() {
    byte[] data = Strings.randomAlphanumeric(6).getBytes();
    String response = service.postBytes(data);
    assertTrue(response.contains("\"" + new String(data) + "\""));
    assertNotNull(service.postBytes(null));
  }

  @Test
  void testPostString() {
    String value = Strings.randomAlphabetic(6);
    HttpBinResponse response = service.postString(value);
    assertEquals(value, response.getData());
    assertEquals("", service.postString(null).getData());
  }

  @Test
  void testPostForm() {
    String value = Strings.randomAlphanumeric(20);
    Map<String, String> form = ImmutableMap.of("name", value);
    String content = service.postForm(form);
    assertTrue(content.contains("\"" + value + "\""));
    assertNotNull(service.postForm(null));
  }

  @Test
  void testPostJson() {
    String name = Strings.randomAlphabetic(6);
    HttpBinRequest req1 = new HttpBinRequest(name, 10);
    HttpBinResponse resp1 = service.postJson(req1);
    assertEquals(name, resp1.getJson().getName());
    assertNull(service.postJson(null).getJson());
  }
}
