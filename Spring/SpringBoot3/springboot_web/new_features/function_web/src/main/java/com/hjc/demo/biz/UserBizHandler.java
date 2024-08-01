package com.hjc.demo.biz;

import com.alibaba.fastjson.JSONObject;
import com.hjc.demo.entity.Person;
import jakarta.servlet.ServletException;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 专门处理User 有关的业务
 * 给每个业务定义一个自己的Handler
 *
 * @author hjc
 */
@Component
public class UserBizHandler {

    private List<Person> list = new ArrayList<>();

    public UserBizHandler() {
        list.add(new Person(1L,"张三","zhangsan@qq.com",15));
        list.add(new Person(2L,"李四","lisi@163.com",15));
        list.add(new Person(3L,"王五","wangwu@Gmail.com",15));
        list.add(new Person(4L,"赵六","zhaoliu@aa.com",15));
    }


    public List<Person> getList() {
        return list;
    }

    /**
     * 查询所有的用户
     * @param request
     * @return
     */
    public ServerResponse getUsers(ServerRequest request) throws Exception {
        //业务处理
        if (list.size() == 0) {
            //构造响应
            return ServerResponse.status(400).build();
        } else {
            //构造响应
            return ServerResponse.ok().body(list); //凡是body中的对象，就是以前@ResponseBody原理。利用HttpMessageConverter写出为json
        }
    }


    /**
     * 新增用户
     * @param request
     * @return
     */
    public ServerResponse saveUser(ServerRequest request) throws ServletException, IOException {
        JSONObject body = request.body(JSONObject.class);
        Person person = new Person();
        person.setId(Long.parseLong(body.get("id").toString()));
        person.setUserName(body.get("username").toString());
        person.setEmail(body.get("email").toString());
        person.setAge(Integer.parseInt(body.get("age").toString()));
        if (list.add(person)){
            //构造响应
            return ServerResponse.ok().body(person);
        }else {
            //构造响应
            return ServerResponse.status(400).build();
        }
    }

    /**
     * 修改用户
     * @param request
     * @return
     */
    public ServerResponse updateUser(ServerRequest request) throws ServletException, IOException {
        String id = request.pathVariable("id");
        JSONObject body = request.body(JSONObject.class);
        for (Person p : list) {
            if (p.getId().toString().equals(id)){
                p.setUserName(body.get("username").toString());
                p.setEmail(body.get("email").toString());
                p.setAge(Integer.parseInt(body.get("age").toString()));
                return ServerResponse.ok().body(p);
            }
        }
        return  ServerResponse.status(401).build();
    }

    /**
     * 删除用户
     * @param request
     * @return
     */
    public ServerResponse deleteUser(ServerRequest request) throws ServletException, IOException {
        String id = request.pathVariable("id");
        for (Person p : list) {
            if (p.getId().toString().equals(id)){
                list.remove(p);
                return ServerResponse.ok().build();
            }
        }
        return  ServerResponse.status(401).build();
    }
}

