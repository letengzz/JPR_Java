package com.hjc.demo.controller;

import com.hjc.demo.service.WeatherService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author hjc
 */
@RestController
public class WeatherController {
    @Resource
    private WeatherService weatherService;

    @GetMapping("/weather")
    public Mono<String> weather(@RequestParam("city")String city){
        //查询天气
        Mono<String> weather = weatherService.weather(city);
        return weather;
    }
}
