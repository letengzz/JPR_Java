package com.hjc.demo;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class ResourceLoaderAwareTest {
    @Test
    void test() {
        //Spring容器会将一个ResourceLoader对象作为该方法的参数传入
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");
        TestResourceLoaderAware testBean = ctx.getBean("testBean",TestResourceLoaderAware.class);
        //获取ResourceLoader对象
        ResourceLoader resourceLoader = testBean.getResourceLoader();
        System.out.println("Spring容器将自身注入到ResourceLoaderAware Bean 中 ？ ：" + (resourceLoader == ctx));
        //加载其他资源
        Resource resource = resourceLoader.getResource("resource.txt");
        System.out.println(resource.getFilename());
        System.out.println(resource.getDescription());
    }
}
