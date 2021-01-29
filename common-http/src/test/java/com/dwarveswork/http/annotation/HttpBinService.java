package com.dwarveswork.http.annotation;

import com.dwarveswork.http.HttpBinRequest;
import com.dwarveswork.http.HttpBinResponse;
import java.util.Map;

public interface HttpBinService {

  @GET("404")
  void get404();

  @GET(value = "delay/5", executeTimeoutMillis = 1000)
  void getTimeout();

  @GET(value = "digest-auth/${qop}/${user}/${password}")
  String auth(@Variable("qop") String qop, @Variables Map<String, String> authentication);

  @GET("get")
  String get(@QueryMap Map<String, String> params, @Header("X-Header") String header);

  @GET("get")
  HttpBinResponse get(@Query("key") String params, @Headers Map<String, String> headers);

  @POST(value = "post", headers = { "Content-Type", "text/plain" })
  String postBytes(byte[] data);

  @POST(value = "post", headers = {"Content-Type", "text/plain"})
  HttpBinResponse postString(String content);

  @POST(value = "post")
  String postForm(Map<String, String> form);

  @POST(value = "post")
  HttpBinResponse postJson(HttpBinRequest json);
}
