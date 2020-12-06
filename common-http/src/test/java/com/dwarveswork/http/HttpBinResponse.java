package com.dwarveswork.http;

import java.io.Serializable;
import java.util.Map;

public class HttpBinResponse implements Serializable {

  private static final long serialVersionUID = -1105831286530295828L;

  private Map<String, String> args;
  private Map<String, String> headers;
  private String data;
  private Map<String, String> form;
  private Map<String, String> files;
  private HttpBinRequest json;
  private String origin;
  private String url;

  public Map<String, String> getArgs() {
    return args;
  }

  public HttpBinResponse setArgs(Map<String, String> args) {
    this.args = args;
    return this;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  public HttpBinResponse setHeaders(Map<String, String> headers) {
    this.headers = headers;
    return this;
  }

  public String getData() {
    return data;
  }

  public HttpBinResponse setData(String data) {
    this.data = data;
    return this;
  }

  public Map<String, String> getForm() {
    return form;
  }

  public HttpBinResponse setForm(Map<String, String> form) {
    this.form = form;
    return this;
  }

  public Map<String, String> getFiles() {
    return files;
  }

  public HttpBinResponse setFiles(Map<String, String> files) {
    this.files = files;
    return this;
  }

  public HttpBinRequest getJson() {
    return json;
  }

  public HttpBinResponse setJson(HttpBinRequest json) {
    this.json = json;
    return this;
  }

  public String getOrigin() {
    return origin;
  }

  public HttpBinResponse setOrigin(String origin) {
    this.origin = origin;
    return this;
  }

  public String getUrl() {
    return url;
  }

  public HttpBinResponse setUrl(String url) {
    this.url = url;
    return this;
  }

  @Override
  public String toString() {
    return "HttpBinResponse{" +
        "args=" + args +
        ", headers=" + headers +
        ", data='" + data + '\'' +
        ", form=" + form +
        ", files=" + files +
        ", json=" + json +
        ", origin='" + origin + '\'' +
        ", url='" + url + '\'' +
        '}';
  }
}
