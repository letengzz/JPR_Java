package com.hjc.demo.controller;

import com.hjc.demo.service.WeatherService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hjc
 */
@RestController
public class WeatherController {
    @Resource
    private WeatherService weatherService;

    @GetMapping("weather")
    public String weather(@RequestParam("city")String city){
        return weatherService.weather(city);
    }
}

