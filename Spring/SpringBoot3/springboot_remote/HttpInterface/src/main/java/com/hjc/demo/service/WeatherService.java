package com.hjc.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;

@Service
public class WeatherService {

    @Autowired
    WeatherInterface weatherInterface;

    public Mono<String> weather(String city){
        /*//1、创建客户端
        WebClient client = WebClient.builder()
                .baseUrl("https://ali-weather.showapi.com")
                .codecs(clientCodecConfigurer -> {
                    clientCodecConfigurer
                            .defaultCodecs()
                            .maxInMemorySize(256 * 1024 * 1024);
                    //响应数据量太大有可能会超出BufferSize，所以这里设置的大一点
                })
                .build();
        //2、创建工厂
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(client)).build();
        //3、获取代理对象
        WeatherInterface weatherAPI = factory.createClient(WeatherInterface.class);
        Mono<String> weather = weatherAPI.getWeather(city, "APPCODE cdc9f6939285485585dfe330d2978db4");*/
        Mono<String> weather = weatherInterface.getWeather(city, "APPCODE cdc9f6939285485585dfe330d2978db4");
        return weather;
    }
}
