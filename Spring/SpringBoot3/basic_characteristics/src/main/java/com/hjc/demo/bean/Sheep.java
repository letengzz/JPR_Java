package com.hjc.demo.bean;

import lombok.Data;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author hjc
 */
//@Component
//@Profile("prod")  //在prod下都会生效
@Data
public class Sheep {
    private Long id;
    private String name;
    private Integer age;
}
