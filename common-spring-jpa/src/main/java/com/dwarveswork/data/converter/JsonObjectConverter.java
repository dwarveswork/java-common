package com.dwarveswork.data.converter;

import com.dwarveswork.lang.Strings;
import com.google.gson.Gson;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.persistence.AttributeConverter;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class JsonObjectConverter<T> implements AttributeConverter<T, String> {

  private final Type genericType;
  private Gson gson;

  public JsonObjectConverter() {
    this.genericType = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  }

  @Autowired
  public void setGson(Gson gson) {
    this.gson = gson;
  }

  @Override
  public String convertToDatabaseColumn(T t) {
    if (t == null) {
      return "{}";
    }
    return gson.toJson(t);
  }

  @Override
  public T convertToEntityAttribute(String s) {
    if (Strings.isBlank(s)) {
      return null;
    }
    return gson.fromJson(s, genericType);
  }
}
