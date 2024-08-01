package com.hjc.robot;

import com.hjc.robot.controller.RobotController;
import com.hjc.robot.properties.RobotProperties;
import com.hjc.robot.service.RobotService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 扫描到该组件
 * 通过Import来导入
 * @author hjc
 */
//给容器中导入Robot功能要用的所有组件
@Import({ RobotController.class, RobotService.class})
@Configuration
public class RobotAutoConfiguration {
    @Bean //把组件导入到容器中
    public RobotProperties robotProperties() {
        return new RobotProperties();
    }
}
