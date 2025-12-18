package com.LiveStats.LiveStats.utils;

import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class JsonMapperUtil {

    private final ObjectMapper objectMapper;

    public JsonMapperUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Erro ao converter resposta JSON para " + clazz.getSimpleName(),
                    e
            );
        }
    }
}

