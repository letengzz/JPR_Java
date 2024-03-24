package com.hjc.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;


// @Component注解保证这个切面类能够放入IOC容器
@Component
public class LogAspect {
    //通知类型

    /**
     * 前置通知 @Before(value="切入点表达式配置切入点")
     *
     * @param joinPoint
     */
//    @Before("execution(public int com.hjc.demo.CalculatorImpl.*(..))")
//    @Before("pointCut()")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.println("Logger-->前置通知，方法名：" + methodName + "，参数：" + args);
    }

    /**
     * 后置通知 @After(value="切入点表达式配置切入点")
     *
     * @param joinPoint
     */
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Logger-->后置通知，方法名：" + methodName);
    }

    /**
     * 返回通知
     *
     * @param joinPoint
     * @param result
     */
    public void afterReturningMethod(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Logger-->返回通知，方法名：" + methodName + "，结果：" + result);
    }

    /**
     * 异常通知
     * 目标方法出现异常，这个通知执行
     * @param joinPoint
     * @param ex
     */
    public void afterThrowingMethod(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Logger-->异常通知，方法名：" + methodName + "，异常：" + ex);
    }

    /**
     * 环绕通知
     *
     * @param joinPoint
     * @return
     */
    public Object aroundMethod(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        Object result = null;
        try {
            System.out.println("环绕通知-->目标对象方法执行之前：" + methodName + "，参数：" + args);
            //目标对象（连接点）方法的执行
            result = joinPoint.proceed();
            System.out.println("环绕通知-->目标对象方法返回值之后：" + methodName + "，参数：" + args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("环绕通知-->目标对象方法出现异常时：" + methodName + "，参数：" + args);
        } finally {
            System.out.println("环绕通知-->目标对象方法执行完毕：" + methodName + "，参数：" + args);
        }
        return result;
    }
}
