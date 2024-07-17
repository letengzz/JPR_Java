package com.hjc.demo.annotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hjc.demo.Person;
import org.junit.jupiter.api.Test;

public class JsonPropertyTest {
    @Test
    void deserialization() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Person person = objectMapper.readValue("{\"name\":\"John Doe\",\"age\":30}", Person.class);
            System.out.println(person.getFullName());
            System.out.println(person.getAge());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void serialization() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(new Person("John Doe", 30));
            System.out.println(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    static class Person {
        @JsonProperty(value = "name")
        private String fullName;
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private int age;

        public Person() {
        }

        public Person(String fullName, int age) {
            this.fullName = fullName;
            this.age = age;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
