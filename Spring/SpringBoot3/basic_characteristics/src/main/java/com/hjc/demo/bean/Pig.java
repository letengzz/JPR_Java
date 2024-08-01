package com.hjc.demo.bean;

import lombok.Data;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author hjc
 */
//@Component
//@Profile({"dev","test"})  //在dev、test下都会生效
@Data
public class Pig {
    private Long id;
    private String name;
    private Integer age;
}
