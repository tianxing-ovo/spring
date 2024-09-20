package com.ltx;

import com.ltx.aop.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class AopTest {

    @Resource
    private UserService userService;

    @Test
    public void test() {
        userService.hello();
        // 是否为AOP代理对象: true
        System.out.println(AopUtils.isAopProxy(userService));
        // 是否为CGLIB代理创建的代理对象: true
        System.out.println(AopUtils.isCglibProxy(userService));
        // 是否为JDK动态代理创建的代理对象: false
        System.out.println(AopUtils.isJdkDynamicProxy(userService));
    }
}
