package br.com.myproject.fipe.service;

import java.util.List;

public interface JsonDeserializer {

    <T> T deserialize(String json, Class<T> clazz);

    <T> List<T> deserializeList(String json, Class<T> clazz);
}
