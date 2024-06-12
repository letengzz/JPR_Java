package com.hjc.demo.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.ChatOptionsBuilder;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {
    /**
     * SpringAI自动装配的可以直接注入使用
     */
    @Resource
    private OpenAiChatClient openAiChatClient;

    /**
     * 调用OpenAI接口
     *
     * @param question
     * @return
     */
    @RequestMapping(value = "/ai/chat")
    public String chat(@RequestParam(value = "question") String question) {
        return openAiChatClient.call(question);
    }

    @RequestMapping(value = "/ai/chat2")
    public Object chat2(@RequestParam(value = "question") String question) {
        ChatResponse chatResponse = openAiChatClient.call(new Prompt(question));
        return chatResponse.getResult().getOutput().getContent();
    }

@RequestMapping(value = "/ai/chat3")
public Object chat3(@RequestParam(value = "question") String question) {
    ChatResponse chatResponse = openAiChatClient.call(new Prompt(question,OpenAiChatOptions.builder()
            .withModel("gpt-4-32k") //gpt的版本，32k是参数量
            .withTemperature(0.4F) //温度越高，回答得比较有创新性，但是准确率会下降，温度越低，回答的准确率会更好
            .build()));
    return chatResponse.getResult().getOutput().getContent();
}
@RequestMapping(value = "/ai/chat4")
public Object chat4(@RequestParam(value = "question") String question) {
    Flux<ChatResponse> flux = openAiChatClient.stream(new Prompt(question, OpenAiChatOptions.builder()
            .withModel("gpt-4-32k") //gpt的版本，32k是参数量
            .withTemperature(0.4F) //温度越高，回答得比较有创新性，但是准确率会下降，温度越低，回答的准确率会更好
            .build()));
    flux.toStream().forEach(chatResponse -> {
        chatResponse.getResult().getOutput().getContent();
    });

    return flux.collectList(); //数据的序列 集合
}
}
