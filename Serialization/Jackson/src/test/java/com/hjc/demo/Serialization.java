package com.hjc.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class Serialization {
    @Test
    void name() {
        Person person = new Person("Alice", 30);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(person);
            System.out.println(jsonString); // 输出：{"name":"Alice","age":30}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
