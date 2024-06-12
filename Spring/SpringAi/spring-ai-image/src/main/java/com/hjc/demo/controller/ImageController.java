package com.hjc.demo.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageClient;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {
    /**
     * SpringAI自动装配的可以直接注入使用
     */
    @Resource
    private OpenAiImageClient openAiImageClient;

    /**
     * 调用OpenAI接口
     * @param msg 消息
     * @return result
     */
    @RequestMapping("/ai/image")
    public Object image(@RequestParam(value = "msg")String msg){
        ImageResponse imageResponse = openAiImageClient.call(new ImagePrompt(msg));
        System.out.println("imageResponse = " + imageResponse);

        String imageUrl = imageResponse.getResult().getOutput().getUrl();
        //把图片进行业务处理

        return imageResponse.getResult().getOutput();

    }

@RequestMapping("/ai/image2")
public Object image2(@RequestParam(value = "msg")String msg){
    ImageResponse imageResponse = openAiImageClient.call(new ImagePrompt(msg, OpenAiImageOptions.builder()
            .withQuality("hd") //高清图像
            .withN(1) //生成4张图片 必须是1～10之间 必须模型支持
            .withHeight(1024) //图片高度
            .withWidth(1024)  //图片宽度
            .build()
    ));
    System.out.println("imageResponse = " + imageResponse);

    String imageUrl = imageResponse.getResult().getOutput().getUrl();
    //把图片进行业务处理

    return imageResponse.getResult().getOutput();

}
}
