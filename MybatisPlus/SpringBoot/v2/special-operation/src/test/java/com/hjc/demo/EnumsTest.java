package com.hjc.demo;

import com.hjc.demo.enums.AgeEnum;
import com.hjc.demo.enums.SexEnum;
import com.hjc.demo.mapper.StudentMapper;
import com.hjc.demo.mapper.UserMapper;
import com.hjc.demo.pojo.Student;
import com.hjc.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EnumsTest {

    @Autowired
    private StudentMapper studentMapper;
    @Test
    void testEnums() {
        Student student = new Student();
        student.setName("刘备");
        student.setEmail("222");
        student.setAge(AgeEnum.ONE);
        //设置性别信息为枚举项，会将@EnumValue注解所标识的属性值存储到数据库
        student.setSex(SexEnum.MALE);

        int insert = studentMapper.insert(student);
        System.out.println("insert = " + insert);
    }
}
