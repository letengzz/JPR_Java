package com.hjc.demo.config;

import com.hjc.demo.biz.UserBizHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.server.RequestPath;
import org.springframework.web.servlet.function.RequestPredicate;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RequestPredicates.accept;
import static org.springframework.web.servlet.function.RouterFunctions.route;

/**
 * 函数式Web:
 *  1、给容器中放一个Bean：类型是RouterFunction<ServerResponse>。集中所有路由信息
 *  2、每一个业务准备一个自己的Handler
 * @author hjc
 */
@Configuration(proxyBeanMethods = false)
@Slf4j
public class MyRoutingConfiguration {
    private static final RequestPredicate ACCEPT_JSON = accept(MediaType.APPLICATION_JSON);

    @Bean
    public RouterFunction<ServerResponse> routerFunction(UserBizHandler userBizHandler/*UserBizHandler会被自动注入进来*/){
        //链式调用
        return route()
                .GET("/person/{id}",accept(MediaType.ALL)/*必须携带参数*/.and(RequestPredicates.path("id")),request -> {
                    //业务处理
                    String id = request.pathVariable("id");
                    log.info(id);
                    for (int i = 0; i < userBizHandler.getList().size(); i++) {
                        if (userBizHandler.getList().get(i).getId().toString().equals(id)){
//                            return ServerResponse.ok().build();
                            //构造响应
                            return ServerResponse.ok().body(userBizHandler.getList().get(i));  //凡是body中的对象，就是以前@ResponseBody原理。利用HttpMessageConverter写出为json
                        }
                    }
                    //构造响应
                    return ServerResponse.status(400).build();
                })
                .GET("/persons",userBizHandler::getUsers)
                .POST("/person",ACCEPT_JSON,userBizHandler::saveUser)
                .PUT("/person/{id}",ACCEPT_JSON,userBizHandler::updateUser)
                .DELETE("/person/{id}",userBizHandler::deleteUser)
                .build();
    }
}
