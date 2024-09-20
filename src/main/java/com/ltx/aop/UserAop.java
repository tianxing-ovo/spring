package com.ltx.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAop {

    /**
     * 切入点
     */
    @Pointcut("execution(public void com.ltx.aop.UserService.hello())")
    public void pointcut() {
    }

    /**
     * 前置通知: 在目标方法执行前执行
     */
    @Before("pointcut()")
    public void before() {
        System.out.println("Before Aop");
        // 打印代理类类名
        System.out.println(AopContext.currentProxy().getClass().getName());
    }
}
