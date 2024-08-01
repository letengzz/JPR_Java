package com.hjc.demo.service;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hjc
 */
@Service
public class WeatherService {

    //RestTemplate支持多种方式的远程调用
    @Resource
    private RestTemplate restTemplate;

    @Value("${aliyun.appcode}")
    private String appcode;

    public String weather(String city){
        //请求头
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "APPCODE "+appcode);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://ali-weather.showapi.com/area-to-weather-date");
        Map<String, Object> params = new HashMap<>();
        params.put("area", city);
        if (!params.isEmpty()){
            for (Map.Entry<String,Object> e: params.entrySet()) {
                //构建查询参数
                builder.queryParam(e.getKey(), e.getValue());
            }
        }
        //拼接好参数后的URI
        String reallyUrl = builder.build().toString();

        //Get 携带请求头
        HttpEntity<String> res = restTemplate
                .exchange(reallyUrl, HttpMethod.GET, new HttpEntity<>(null, headers),
                        String.class,params);
         return res.getBody();

        //Post 携带请求头和请求体
//		String response = restTemplate
//				.postForObject("https://ali-weather.showapi.com/area-to-weather", new HttpEntity<>(params, headers), String.class);
//		System.out.println("response = " + response);
    }
}
