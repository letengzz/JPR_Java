package com.hjc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author hjc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true) //支持链式编程
@Builder
public class User {
    private String name;
    private Integer age;
}