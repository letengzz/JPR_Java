package com.hjc.demo;

import com.hjc.demo.config.JdbcConfig;
import com.hjc.demo.config.MyConfig;
import com.hjc.demo.entity.Admin;
import com.hjc.demo.entity.Pet;
import com.hjc.demo.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.util.Arrays;

@SpringBootApplication
public class SpringbootAnnotationApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringbootAnnotationApplication.class, args);
		//查看容器中的组件
		String[] names = context.getBeanDefinitionNames();
		for (String name :
				names) {
			System.out.println("name = " + name);
		}
		//注解测试
		Pet tom01 = context.getBean("tom", Pet.class);
		Pet tom02 = context.getBean("tom", Pet.class);
		System.out.println("多次获得bean :" + (tom01 == tom02));
		MyConfig myConfig = context.getBean(MyConfig.class);
		System.out.println(myConfig);
		User user01 = myConfig.user();
		User user02 = myConfig.user();
		System.out.println("是否保持调⽤⽅法单例" + (user01 == user02));
		System.out.println("⽤户的宠物是否为容器中的宠物 : " + (user01.getPet()
				== tom01));
		// import测试
		Arrays.stream(context.getBeanNamesForType(JdbcConfig.class)).forEach((
				name) -> {
			System.out.println(name);
		});

		// ImportResource测试
		System.out.println(context.containsBean("zhangsan"));
		System.out.println(context.containsBean("lisi"));

		for (String s:context.getBeanNamesForType(Pet.class)){
			System.out.println(s);
		}

		Admin admin = context.getBean(Admin.class);
		System.out.println("admin = " + admin);

	}
}
