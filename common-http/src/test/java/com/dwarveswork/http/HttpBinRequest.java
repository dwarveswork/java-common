package com.dwarveswork.http;

import java.io.Serializable;

public class HttpBinRequest implements Serializable {

  private static final long serialVersionUID = -6214905613405455151L;

  private String name;
  private Integer age;

  public HttpBinRequest(String name, Integer age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "HttpBinRequest{" +
        "name='" + name + '\'' +
        ", age=" + age +
        '}';
  }
}
