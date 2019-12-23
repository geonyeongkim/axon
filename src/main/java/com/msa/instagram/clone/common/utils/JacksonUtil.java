package com.msa.instagram.clone.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JacksonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String writeValueAsString(Object obj) {
        String result = null;
        try {
            result = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    public static <T> T readValue(String a, Class<T> clazz) {
        T t = null;
        try {
            t = objectMapper.readValue(a, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
        return t;
    }
}
