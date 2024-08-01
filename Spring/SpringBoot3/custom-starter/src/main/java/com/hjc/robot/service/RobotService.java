package com.hjc.robot.service;

import com.hjc.robot.properties.RobotProperties;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author hjc
 */
@Service
public class RobotService {
    @Resource
    RobotProperties robotProperties;

    public String sayHello() {
        return "你好:【" + robotProperties.getName() + "】,年龄：" + robotProperties.getAge();
    }
}
