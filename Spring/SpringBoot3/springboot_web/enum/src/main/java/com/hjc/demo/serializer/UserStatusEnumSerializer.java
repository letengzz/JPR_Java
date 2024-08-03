package com.hjc.demo.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hjc.demo.enume.UserStatusEnum;

import java.io.IOException;

/**
 * @author hjc
 */
public class UserStatusEnumSerializer extends JsonSerializer<UserStatusEnum> {
    @Override
    public void serialize(UserStatusEnum userStatusEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(userStatusEnum.getCode()+"");
    }
}
