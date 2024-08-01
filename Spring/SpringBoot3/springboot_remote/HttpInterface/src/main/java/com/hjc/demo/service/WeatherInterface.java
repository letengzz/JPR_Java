package com.hjc.demo.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

public interface WeatherInterface {
    @GetExchange(url = "https://ali-weather.showapi.com/area-to-weather",accept = "application/json")
    Mono<String> getWeather(@RequestParam("area")String city,
                            @RequestHeader("Authorization") String auth);
}
