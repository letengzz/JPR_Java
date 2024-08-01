package com.hjc.demo.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherService {
    public Mono<String> weather(String city) {
        //远程调用阿里云API

        //1. 创建一个WebClient
//        WebClient client = WebClient.create("https://ali-weather.showapi.com/area-to-weather-date?area="+city); //使用此方式 或下方方式来对get请求参数进行拼接
        WebClient client = WebClient.create();

        //2. 准备数据
        Map<String, String> params = new HashMap<>();
        params.put("area",city);

        //3. 定义发请求的行为
        Mono<String> mono = client.get() //接收get请求 post使用body()定义请求体
                .uri("https://ali-weather.showapi.com/area-to-weather?area={area}",params)
                .accept(MediaType.APPLICATION_JSON) // 定义响应的内容类型为JSON
                .header("Authorization", "APPCODE cdc9f6939285485585dfe330d2978db4")  //定义请求头
                .retrieve()
                .bodyToMono(String.class);//响应体数据使用为String
        mono.subscribe(System.out::println);
        return mono;
    }
}
