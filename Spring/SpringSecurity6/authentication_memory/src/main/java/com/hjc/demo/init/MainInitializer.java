package com.hjc.demo.init;

import com.hjc.demo.config.SecurityConfiguration;
import com.hjc.demo.config.WebConfiguration;
import jakarta.servlet.Filter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * web工程的初始化类，用来代替web.xml
 *
 * @author hjc
 */
public class MainInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 指定基本的Spring配置类，一般用于业务层配置
     *
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{WebConfiguration.class, SecurityConfiguration.class};
    }

    /**
     * 配置DispatcherServlet的配置类、
     * 主要用于Controller等配置，这里只使用上面的基本配置类
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    /**
     * 指定DispatcherServlet的映射规则，即url-pattern
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};    //匹配路径
    }
}