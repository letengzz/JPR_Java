package com.hjc.demo.init;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * @author hjc
 */
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    //不用重写任何内容
    //这里实际上会自动注册一个Filter，SpringSecurity底层就是依靠N个过滤器实现的
}