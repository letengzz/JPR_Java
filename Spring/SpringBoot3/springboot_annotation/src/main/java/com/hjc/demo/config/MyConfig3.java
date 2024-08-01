package com.hjc.demo.config;

import com.hjc.demo.entity.Admin;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 1. 开启组件的属性绑定
 * 2. 默认会把这个组件自己放到容器中
 * @author hjc
 */
@EnableConfigurationProperties(Admin.class)  //导入第三方写好的组件进行属性绑定
//SpringBoot 默认只扫描自己主程序所在的包。如果导入第三方包，即使组件上标注了 @Commponent、@ConfigurationProperties 注解，也没用。因为组件都扫描不进来
@SpringBootConfiguration
public class  MyConfig3 {
}
