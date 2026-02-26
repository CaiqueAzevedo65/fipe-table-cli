package br.com.myproject.fipe.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JacksonDataConverter implements JsonDeserializer {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> List<T> deserializeList(String json, Class<T> clazz) {
        try {
            var listType = mapper.getTypeFactory()
                    .constructCollectionType(List.class, clazz);
            return mapper.readValue(json, listType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T deserialize(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
