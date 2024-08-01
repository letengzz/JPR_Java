package com.hjc.demo.component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

/**
 * 自定义组件
 *
 * @author hjc
 */
@Component
public class MyHahaComponent {
    Counter counter = null;

    /**
     * 注入 MeterRegistry 来保存和统计所有指标
     * @param registry MeterRegistry
     */
    public MyHahaComponent(MeterRegistry registry) {
        //注册指标
        //得到名叫 myhaha.haha 的计数器
        counter = registry
                .counter("myhaha.haha");
    }

    public int check() {
        //业务代码判断这个组件是否该是存货状态
        return 1;
    }

    public void hello() {
        System.out.println("hello");
        //计数器+1
        counter.increment();
    }
}
