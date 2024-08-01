package com.hjc.demo.health;

import com.hjc.demo.component.MyHahaComponent;
import jakarta.annotation.Resource;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * 后缀名必须为 Indicator
 * 1、实现HealthIndicator接口来定制组件的健康状态对象(Health)返回
 * 2、实现抽象类 AbstractHealthIndicator
 * @author hjc
 */
@Component
public class MyHahaComponentHealthIndicator extends AbstractHealthIndicator {
    @Resource
    private MyHahaComponent component;

    /**
     * 健康检查
     */
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        //自定义检查方法
        int check = component.check();
        if (check == 1){
            builder.up()
                    .withDetail("msg", "up")
                    .withDetail("code", "100")
                    .withDetail("data","haha")
                    .build();
        }else {
            builder.down()
                    .withDetail("msg", "error service")
                    .withDetail("code", "500")
                    .withException(new RuntimeException())
                    .build();
        }
    }
}
