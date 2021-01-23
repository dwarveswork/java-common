package com.dwarveswork.data.converter;

import com.dwarveswork.lang.Strings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeConverter;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class JsonArrayConverter<T> implements AttributeConverter<List<T>, String> {

  private final Type genericType;
  private Gson gson;

  @SuppressWarnings("unchecked")
  public JsonArrayConverter() {
    Class<T> actualClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    this.genericType = TypeToken.getParameterized(List.class, actualClass).getType();
  }

  @Autowired
  public final void setGson(Gson gson) {
    this.gson = gson;
  }

  @Override
  public String convertToDatabaseColumn(List<T> ts) {
    if (ts == null) {
      return "[]";
    }
    return gson.toJson(ts);
  }

  @Override
  public List<T> convertToEntityAttribute(String s) {
    if (Strings.isBlank(s)) {
      return new ArrayList<>();
    }
    return gson.fromJson(s, genericType);
  }
}
