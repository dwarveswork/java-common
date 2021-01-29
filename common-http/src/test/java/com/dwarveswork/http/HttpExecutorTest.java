package com.dwarveswork.http;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dwarveswork.exception.RemoteTimeoutException;
import com.dwarveswork.lang.Strings;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import org.junit.jupiter.api.Test;

class HttpExecutorTest {

  private static final String BASE_URL = "https://httpbin.org/";
  private static final String URL_GET = BASE_URL + "get";
  private static final String URL_POST = BASE_URL + "post";
  private static final String URL_PATCH = BASE_URL + "patch";
  private static final String URL_DELETE = BASE_URL + "delete";
  private static final String URL_404 = BASE_URL + "404";
  private static final String URL_DELAY = BASE_URL + "delay/5";
  private static final String TEST_HEADER = "X-Header";

  private HttpExecutor httpExecutor = new DefaultHttpExecutor();

  @Test
  void testTimeout() {
    GetRequest request = new GetRequest(URL_DELAY, String.class);
    request.setExecuteTimeout(Duration.ofSeconds(1));
    assertThrows(RemoteTimeoutException.class, () -> httpExecutor.get(request));
  }

  @Test
  void testGetData() {
    GetRequest request = new GetRequest(URL_GET, byte[].class);
    HttpResponse<byte[]> response = httpExecutor.get(request);
    assertTrue(response.getBody().length > 0);
    String a = Strings.randomAlphanumeric(10);
    request.addHeader("a", a);
    response = httpExecutor.get(request);
    assertTrue(response.getBody().length > 0);
    assertTrue(new String(response.getBody(), StandardCharsets.UTF_8).contains("\"" + a + "\""));
    response = httpExecutor.get(URL_404, byte[].class);
    assertEquals(404, response.getStatusCode());
  }

  @Test
  void testGetString() {
    GetRequest request = new GetRequest(URL_GET, String.class);
    HttpResponse<String> response = httpExecutor.get(request);
    assertNotNull(response.getBody());
    String a = Strings.randomAlphanumeric(10);
    request.addHeader("a", a);
    response = httpExecutor.get(request);
    assertNotNull(response.getBody());
    assertTrue(response.getBody().contains("\"" + a + "\""));
  }

  @Test
  void testGetObject() {
    GetRequest request = new GetRequest(URL_GET, HttpBinResponse.class);
    HttpResponse<HttpBinResponse> r1 = httpExecutor.get(request);
    assertNotNull(r1.getBody().getUrl());
    String a = Strings.randomAlphanumeric(10);
    request.addHeader("a", a);
    r1 = httpExecutor.get(request);
    assertNotNull(r1.getBody().getUrl());
    assertEquals(r1.getBody().getHeaders().get("A"), a);
  }
//
//  @Test
//  void testPostBytes() {
//    byte[] data = Strings.randomAlphabetic(10).getBytes();
//    String r1 = HttpTemplate.postBytes(URL_POST, TEXT_PLAIN_UTF8, data);
//    assertTrue(r1.contains("\"" + new String(data) + "\""));
//    String header = Strings.randomAlphabetic(10);
//    String r2 = HttpTemplate.postBytes(URL_POST, TEXT_PLAIN_UTF8, data, ImmutableMap.of(TEST_HEADER, header));
//    assertTrue(r2.contains("\"" + new String(data) + "\""));
//    assertTrue(r2.contains("\"" + header + "\""));
//  }
//
//  @Test
//  void testPostBytesForObject() {
//    byte[] data = Strings.randomAlphabetic(10).getBytes();
//    io.honestnode.http.HttpBinResponse r1 = HttpTemplate.postBytesForObject(URL_POST, TEXT_PLAIN_UTF8, data, io.honestnode.http.HttpBinResponse.class);
//    assertEquals(new String(data), r1.getData());
//    String header = Strings.randomAlphabetic(10);
//    io.honestnode.http.HttpBinResponse r2 = HttpTemplate
//        .postBytesForObject(URL_POST, TEXT_PLAIN_UTF8, data, io.honestnode.http.HttpBinResponse.class, ImmutableMap.of(TEST_HEADER, header));
//    assertEquals(new String(data), r2.getData());
//    assertTrue(r2.getHeaders().containsValue(header));
//  }
//
//  @Test
//  void testPatchBytes() {
//    byte[] data = Strings.randomAlphabetic(10).getBytes();
//    String r1 = HttpTemplate.patchBytes(URL_PATCH, TEXT_PLAIN_UTF8, data);
//    assertTrue(r1.contains("\"" + new String(data) + "\""));
//    String header = Strings.randomAlphabetic(10);
//    String r2 = HttpTemplate.patchBytes(URL_PATCH, TEXT_PLAIN_UTF8, data, ImmutableMap.of(TEST_HEADER, header));
//    assertTrue(r2.contains("\"" + new String(data) + "\""));
//    assertTrue(r2.contains("\"" + header + "\""));
//  }
//
//  @Test
//  void testPatchBytesForObject() {
//    byte[] data = Strings.randomAlphabetic(10).getBytes();
//    io.honestnode.http.HttpBinResponse r1 = HttpTemplate.patchBytesForObject(URL_PATCH, TEXT_PLAIN_UTF8, data, io.honestnode.http.HttpBinResponse.class);
//    assertEquals(new String(data), r1.getData());
//    String header = Strings.randomAlphabetic(10);
//    io.honestnode.http.HttpBinResponse r2 = HttpTemplate
//        .patchBytesForObject(URL_PATCH, TEXT_PLAIN_UTF8, data, io.honestnode.http.HttpBinResponse.class, ImmutableMap.of(TEST_HEADER, header));
//    assertEquals(new String(data), r2.getData());
//    assertTrue(r2.getHeaders().containsValue(header));
//  }
//
//  @Test
//  void testPostString() {
//    String data = Strings.randomAlphabetic(10);
//    String r1 = HttpTemplate.postString(URL_POST, data);
//    assertTrue(r1.contains("\"" + data + "\""));
//    String r2 = HttpTemplate.postString(URL_POST, TEXT_PLAIN_UTF8, data);
//    assertTrue(r2.contains("\"" + data + "\""));
//    String header = Strings.randomAlphabetic(10);
//    String r3 = HttpTemplate.postString(URL_POST, data, ImmutableMap.of(TEST_HEADER, header));
//    assertTrue(r3.contains("\"" + data + "\""));
//    assertTrue(r3.contains("\"" + header + "\""));
//  }
//
//  @Test
//  void testPostStringForObject() {
//    String data = Strings.randomAlphabetic(10);
//    io.honestnode.http.HttpBinResponse r1 = HttpTemplate.postStringForObject(URL_POST, data, io.honestnode.http.HttpBinResponse.class);
//    assertEquals(data, r1.getData());
//    io.honestnode.http.HttpBinResponse r2 = HttpTemplate.postStringForObject(URL_POST, TEXT_PLAIN_UTF8, data, io.honestnode.http.HttpBinResponse.class);
//    assertEquals(data, r2.getData());
//    String header = Strings.randomAlphabetic(10);
//    io.honestnode.http.HttpBinResponse r3 = HttpTemplate
//        .postStringForObject(URL_POST, TEXT_PLAIN_UTF8, data, io.honestnode.http.HttpBinResponse.class, ImmutableMap.of(TEST_HEADER, header));
//    assertEquals(data, r3.getData());
//    assertTrue(r3.getHeaders().containsValue(header));
//  }
//
//  @Test
//  void testPatchString() {
//    String data = Strings.randomAlphabetic(10);
//    String r1 = HttpTemplate.patchString(URL_PATCH, data);
//    assertTrue(r1.contains("\"" + data + "\""));
//    String r2 = HttpTemplate.patchString(URL_PATCH, TEXT_PLAIN_UTF8, data);
//    assertTrue(r2.contains("\"" + data + "\""));
//    String header = Strings.randomAlphabetic(10);
//    String r3 = HttpTemplate.patchString(URL_PATCH, TEXT_PLAIN_UTF8, data, ImmutableMap.of(TEST_HEADER, header));
//    assertTrue(r3.contains("\"" + data + "\""));
//    assertTrue(r3.contains("\"" + header + "\""));
//  }
//
//  @Test
//  void testPatchStringForObject() {
//    String data = Strings.randomAlphabetic(10);
//    io.honestnode.http.HttpBinResponse r1 = HttpTemplate.patchStringForObject(URL_PATCH, data, io.honestnode.http.HttpBinResponse.class);
//    assertEquals(data, r1.getData());
//    io.honestnode.http.HttpBinResponse r2 = HttpTemplate.patchStringForObject(URL_PATCH, TEXT_PLAIN_UTF8, data, io.honestnode.http.HttpBinResponse.class);
//    assertEquals(data, r2.getData());
//    String header = Strings.randomAlphabetic(10);
//    io.honestnode.http.HttpBinResponse r3 = HttpTemplate
//        .patchStringForObject(URL_PATCH, TEXT_PLAIN_UTF8, data, io.honestnode.http.HttpBinResponse.class, ImmutableMap.of(TEST_HEADER, header));
//    assertTrue(r3.getHeaders().containsValue(header));
//  }
//
//  @Test
//  void testPostForm() {
//    String v1 = Strings.randomAlphabetic(10);
//    String v2 = Strings.randomAlphabetic(10);
//    String header = Strings.randomAlphabetic(10);
//    Map<String, String> form = ImmutableMap.of("k1", v1, "k2", v2);
//    String r1 = HttpTemplate.postForm(URL_POST, form);
//    assertTrue(r1.contains("\"" + v1 + "\""));
//    String r2 = HttpTemplate.postForm(URL_POST, form, ImmutableMap.of(TEST_HEADER, header));
//    assertTrue(r2.contains("\"" + v2 + "\""));
//    assertTrue(r2.contains("\"" + header + "\""));
//  }
//
//  @Test
//  void testPostFormForObject() {
//    String v1 = Strings.randomAlphabetic(10);
//    String v2 = Strings.randomAlphabetic(10);
//    Map<String, String> form = ImmutableMap.of("k1", v1, "k2", v2);
//    io.honestnode.http.HttpBinResponse r1 = HttpTemplate.postFormForObject(URL_POST, form, io.honestnode.http.HttpBinResponse.class);
//    assertTrue(r1.getForm().containsValue(v1));
//    String header = Strings.randomAlphabetic(10);
//    io.honestnode.http.HttpBinResponse r2 = HttpTemplate
//        .postFormForObject(URL_POST, form, io.honestnode.http.HttpBinResponse.class, ImmutableMap.of(TEST_HEADER, header));
//    assertTrue(r2.getForm().containsValue(v2));
//    assertTrue(r2.getHeaders().containsValue(header));
//  }
//
//  @Test
//  void testPatchForm() {
//    String v1 = Strings.randomAlphabetic(10);
//    String v2 = Strings.randomAlphabetic(10);
//    String header = Strings.randomAlphabetic(10);
//    Map<String, String> form = ImmutableMap.of("k1", v1, "k2", v2);
//    String r1 = HttpTemplate.patchForm(URL_PATCH, form);
//    assertTrue(r1.contains("\"" + v1 + "\""));
//    String r2 = HttpTemplate.patchForm(URL_PATCH, form, ImmutableMap.of(TEST_HEADER, header));
//    assertTrue(r2.contains("\"" + v2 + "\""));
//    assertTrue(r2.contains("\"" + header + "\""));
//  }
//
//  @Test
//  void testPatchFormForObject() {
//    String v1 = Strings.randomAlphabetic(10);
//    String v2 = Strings.randomAlphabetic(10);
//    Map<String, String> form = ImmutableMap.of("k1", v1, "k2", v2);
//    io.honestnode.http.HttpBinResponse r1 = HttpTemplate.patchFormForObject(URL_PATCH, form, io.honestnode.http.HttpBinResponse.class);
//    assertTrue(r1.getForm().containsValue(v1));
//    String header = Strings.randomAlphabetic(10);
//    io.honestnode.http.HttpBinResponse r2 = HttpTemplate
//        .patchFormForObject(URL_PATCH, form, io.honestnode.http.HttpBinResponse.class, ImmutableMap.of(TEST_HEADER, header));
//    assertTrue(r2.getForm().containsValue(v2));
//    assertTrue(r2.getHeaders().containsValue(header));
//  }
//
//  @Test
//  void testPostMultipartForm() {
//    String v1 = Strings.randomAlphabetic(10);
//    String header = Strings.randomAlphabetic(10);
//    String r1 = HttpTemplate.postMultipartForm(URL_POST, Lists.newArrayList(new HttpMultipartFormData("v1", v1)));
//    assertTrue(r1.contains("\"" + v1 + "\""));
//    byte[] file = Strings.randomAlphabetic(10).getBytes();
//    String r2 = HttpTemplate.postMultipartForm(URL_POST, Lists.newArrayList(new HttpMultipartFormData("v1", v1),
//                                                                            new HttpMultipartFormData("v2", "filename", file)),
//                                               ImmutableMap.of(TEST_HEADER, header));
//    assertTrue(r2.contains("\"" + new String(file, StandardCharsets.UTF_8) + "\""));
//    assertTrue(r2.contains("\"" + header + "\""));
//  }
//
//  @Test
//  void testPostMultipartFormForObject() {
//    String v1 = Strings.randomAlphabetic(10);
//    String header = Strings.randomAlphabetic(10);
//    io.honestnode.http.HttpBinResponse r1 = HttpTemplate.postMultipartFormForObject(URL_POST, Lists.newArrayList(new HttpMultipartFormData("v1", v1)), io.honestnode.http.HttpBinResponse.class);
//    assertTrue(r1.getForm().containsValue(v1));
//    byte[] file = Strings.randomAlphabetic(10).getBytes();
//    io.honestnode.http.HttpBinResponse r2 = HttpTemplate.postMultipartFormForObject(URL_POST, Lists.newArrayList(new HttpMultipartFormData("v1", v1),
//                                                                                                                 new HttpMultipartFormData("v2", "filename", file)),
//                                                                                    io.honestnode.http.HttpBinResponse.class, ImmutableMap.of(TEST_HEADER, header));
//    assertTrue(r2.getFiles().containsValue(new String(file, StandardCharsets.UTF_8)));
//    assertTrue(r2.getHeaders().containsValue(header));
//  }
//
//  @Test
//  void testPatchMultipartForm() {
//    String v1 = Strings.randomAlphabetic(10);
//    String header = Strings.randomAlphabetic(10);
//    String r1 = HttpTemplate.patchMultipartForm(URL_PATCH, Lists.newArrayList(new HttpMultipartFormData("v1", v1)));
//    assertTrue(r1.contains("\"" + v1 + "\""));
//    byte[] file = Strings.randomAlphabetic(10).getBytes();
//    String r2 = HttpTemplate.patchMultipartForm(URL_PATCH, Lists.newArrayList(new HttpMultipartFormData("v1", v1),
//                                                                              new HttpMultipartFormData("v2", "filename", file)),
//                                                ImmutableMap.of(TEST_HEADER, header));
//    assertTrue(r2.contains("\"" + new String(file, StandardCharsets.UTF_8) + "\""));
//    assertTrue(r2.contains("\"" + header + "\""));
//  }
//
//  @Test
//  void testPatchMultipartFormForObject() {
//    String v1 = Strings.randomAlphabetic(10);
//    String header = Strings.randomAlphabetic(10);
//    io.honestnode.http.HttpBinResponse r1 = HttpTemplate.patchMultipartFormForObject(URL_PATCH, Lists.newArrayList(new HttpMultipartFormData("v1", v1)), io.honestnode.http.HttpBinResponse.class);
//    assertTrue(r1.getForm().containsValue(v1));
//    byte[] file = Strings.randomAlphabetic(10).getBytes();
//    io.honestnode.http.HttpBinResponse r2 = HttpTemplate.patchMultipartFormForObject(URL_PATCH, Lists.newArrayList(new HttpMultipartFormData("v1", v1),
//                                                                                                                   new HttpMultipartFormData("v2", "filename", file)),
//                                                                                     io.honestnode.http.HttpBinResponse.class, ImmutableMap.of(TEST_HEADER, header));
//    assertTrue(r2.getFiles().containsValue(new String(file, StandardCharsets.UTF_8)));
//    assertTrue(r2.getHeaders().containsValue(header));
//  }
//
//  @Test
//  void testPostJson() {
//    String name = Strings.randomAlphabetic(6);
//    HttpBinRequest request = new HttpBinRequest(name, 20);
//    String r1 = HttpTemplate.postJson(URL_POST, request);
//    assertTrue(r1.indexOf("\"" + name + "\"") > 0);
//    String header = Strings.randomAlphabetic(10);
//    String r2 = HttpTemplate.postJson(URL_POST, request, ImmutableMap.of(TEST_HEADER, header));
//    assertTrue(r2.contains("20"));
//    assertTrue(r2.contains("\"" + header + "\""));
//  }
//
//  @Test
//  void testPostJsonForObject() {
//    String name = Strings.randomAlphabetic(6);
//    HttpBinRequest request = new HttpBinRequest(name, 20);
//    io.honestnode.http.HttpBinResponse r1 = HttpTemplate.postJsonForObject(URL_POST, request, io.honestnode.http.HttpBinResponse.class);
//    assertEquals(name, r1.getJson().getName());
//    String header = Strings.randomAlphabetic(10);
//    io.honestnode.http.HttpBinResponse r2 = HttpTemplate.postJsonForObject(URL_POST, request, io.honestnode.http.HttpBinResponse.class, ImmutableMap.of(TEST_HEADER, header));
//    assertEquals(20, r1.getJson().getAge());
//    assertTrue(r2.getHeaders().containsValue(header));
//  }
//
//  @Test
//  void testPatchJson() {
//    String name = Strings.randomAlphabetic(6);
//    HttpBinRequest request = new HttpBinRequest(name, 20);
//    String r1 = HttpTemplate.patchJson(URL_PATCH, request);
//    assertTrue(r1.indexOf("\"" + name + "\"") > 0);
//    String header = Strings.randomAlphabetic(10);
//    String r2 = HttpTemplate.patchJson(URL_PATCH, request, ImmutableMap.of(TEST_HEADER, header));
//    assertTrue(r2.contains("20"));
//    assertTrue(r2.contains("\"" + header + "\""));
//  }
//
//  @Test
//  void testPatchJsonForObject() {
//    String name = Strings.randomAlphabetic(6);
//    HttpBinRequest request = new HttpBinRequest(name, 20);
//    io.honestnode.http.HttpBinResponse r1 = HttpTemplate.patchJsonForObject(URL_PATCH, request, io.honestnode.http.HttpBinResponse.class);
//    assertEquals(name, r1.getJson().getName());
//    String header = Strings.randomAlphabetic(10);
//    io.honestnode.http.HttpBinResponse r2 = HttpTemplate.patchJsonForObject(URL_PATCH, request, io.honestnode.http.HttpBinResponse.class, ImmutableMap.of(TEST_HEADER, header));
//    assertEquals(20, r1.getJson().getAge());
//    assertTrue(r2.getHeaders().containsValue(header));
//  }
}
