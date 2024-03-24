package com.hjc.demo;

import com.hjc.demo.config.ValidationConfig;
import com.hjc.demo.entity.Admin;
import com.hjc.demo.entity.Message;
import com.hjc.demo.entity.Person;
import com.hjc.demo.entity.User;
import com.hjc.demo.service.MsgService;
import com.hjc.demo.service.MyService;
import com.hjc.demo.service.MyService1;
import com.hjc.demo.service.MyService2;
import com.hjc.demo.validation.PersonValidator;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

public class ValidationTest {
    /**
     * 通过Validator接口实现
     */
    @Test
    void testValidationInterface() {
        //创建person对象
        Person person = new Person();
        person.setName("lucy");
        person.setAge(-1);

        // 创建Person对应的DataBinder
        DataBinder binder = new DataBinder(person);

        // 设置校验
        binder.setValidator(new PersonValidator());

        // 由于Person对象中的属性为空，所以校验不通过
        binder.validate();

        //输出结果
        BindingResult results = binder.getBindingResult();
        System.out.println(results.getAllErrors());
    }


    /**
     * Bean Validation注解实现
     */
    @Test
    void testBeanValidation1() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ValidationConfig.class);
        MyService1 myService = context.getBean(MyService1.class);
        User user = new User();
        user.setAge(-1);
        boolean validator = myService.validator(user);
        System.out.println("校验结果(校验通过true 不通过false)：" + validator);
    }

    @Test
    public void testMyService2() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ValidationConfig.class);
        MyService2 myService = context.getBean(MyService2.class);
        User user = new User();
        user.setName("lucy");
        user.setAge(130);
        user.setAge(-1);
        boolean validator = myService.validaPersonByValidator(user);
        System.out.println("校验结果(校验通过false 不通过true)："+validator);
    }

    /**
     * 基于方法实现校验
     */
    @Test
    void testMethod() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ValidationConfig.class);
        MyService myService = context.getBean(MyService.class);
        Admin admin = new Admin();
        admin.setAge(-1);
        myService.testParams(admin);
    }

    @Test
    void testCustomAnnotation() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ValidationConfig.class);
        MsgService msgService = context.getBean(MsgService.class);
        Message message = new Message();
        message.setMsgName("nihao a a a");
        msgService.testParams(message);
    }
}
