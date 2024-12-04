package com.ltx;

import cn.hutool.core.bean.BeanUtil;
import com.ltx.event.CustomEvent;
import lombok.SneakyThrows;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableAsync;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableAsync
public class Application {

    @SneakyThrows
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        // 打印所有单例对象
        printSingletonObjects(beanFactory);
        // 获取国际化信息
        System.out.println(context.getMessage("hi", null, Locale.CHINA));
        System.out.println(context.getMessage("hi", null, Locale.US));
        // 获取项目类路径下的资源
        System.out.println(context.getResource("classpath:application.properties"));
        // 获取jar包中的资源
        Arrays.stream(context.getResources("classpath*:META-INF/spring.factories")).forEach(System.out::println);
        // 获取环境配置信息,不区分大小写
        ConfigurableEnvironment environment = context.getEnvironment();
        Arrays.asList("java_home", "server.port").forEach(name -> System.out.println(environment.getProperty(name)));
        // 发布自定义事件
        context.publishEvent(new CustomEvent(context));
    }

    /**
     * 打印所有单例对象
     */
    @SneakyThrows
    public static void printSingletonObjects(BeanFactory beanFactory) {
        Field field = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
        field.setAccessible(true);
        Map<String, Object> map = BeanUtil.beanToMap(field.get(beanFactory));
        map.forEach((key, value) -> System.out.println(key + "=" + value));
    }
}
