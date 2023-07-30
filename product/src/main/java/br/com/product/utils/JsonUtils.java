package br.com.product.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class JsonUtils {

  public <T> T jsonToObject(final String json, Class<T> tClass) {
    ObjectMapper mapper = new ObjectMapper();

    try {
      return mapper.readValue(json, tClass);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
