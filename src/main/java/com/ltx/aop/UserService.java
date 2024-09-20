package com.ltx.aop;


import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void hello() {
        System.out.println("Hello Aop");
        // 打印代理类类名
        System.out.println(AopContext.currentProxy().getClass().getName());
    }
}
