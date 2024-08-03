package com.hjc.demo.pojo;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

/**
 * @author hjc
 */
@Data
public class Product {
    private Long id;
    private String name;
    private Integer price;
    private Integer version;
}

