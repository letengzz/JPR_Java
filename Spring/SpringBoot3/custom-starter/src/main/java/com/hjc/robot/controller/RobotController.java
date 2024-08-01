package com.hjc.robot.controller;

import com.hjc.robot.service.RobotService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hjc
 */
@RestController
public class RobotController {
    @Resource
    private RobotService robotService;

    @GetMapping("/robot/hello")
    public String sayHello() {
        return robotService.sayHello();
    }
}
