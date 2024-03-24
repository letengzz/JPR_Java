package com.hjc.demo.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hjc
 */
public class HelloWorld {

    private Logger logger = LoggerFactory.getLogger(HelloWorld.class);
    public HelloWorld() {
        logger.info("执行成功");
        System.out.println("无参数构造方法执行");
    }
    public void sayHello(){
        System.out.println("hello world");
    }
}

