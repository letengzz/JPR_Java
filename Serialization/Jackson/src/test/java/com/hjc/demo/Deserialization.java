package com.hjc.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

/**
 * 反序列化
 * @author hjc
 */
public class Deserialization {
    @Test
    void basic() {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "{\"name\":\"Alice\",\"age\":30}";
        try {
            Person person = objectMapper.readValue(jsonString, Person.class);
            System.out.println("Name: " + person.getName() + ", Age: " + person.getAge()); // 输出：Name: Alice, Age: 30
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
