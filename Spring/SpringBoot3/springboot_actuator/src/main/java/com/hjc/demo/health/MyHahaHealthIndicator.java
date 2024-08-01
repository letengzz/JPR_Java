package com.hjc.demo.health;

import com.hjc.demo.component.MyHahaComponent;
import jakarta.annotation.Resource;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
/**
 * 后缀名必须为 Indicator
 * 1、实现HealthIndicator接口来定制组件的健康状态对象(Health)返回
 * 2、实现抽象类 AbstractHealthIndicator
 * @author hjc
 */
@Component
public class MyHahaHealthIndicator implements HealthIndicator {
    @Resource
    private MyHahaComponent component;
    /**
     * 返回健康状况
     * @return 健康状况
     */
    @Override
    public Health health() {
        //自定义检查方法
        int errorCode = component.check();
        if (errorCode == 0) {
            //下线
            return Health.down().withDetail("Error Code", errorCode).build();
        }
        //存活
        return  Health.up()
                .withDetail("msg", "up")
                .withDetail("code", "100")
                .withDetail("data","haha")
                .build();
    }

}
