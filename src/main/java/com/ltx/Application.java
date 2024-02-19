package com.ltx;

import io.github.tianxingovo.common.ObjectUtil;
import lombok.SneakyThrows;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.Resource;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Map;

@SpringBootApplication(exclude = {RedissonAutoConfiguration.class})
public class Application {

    @SneakyThrows
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        // 获取所有单例对象
        Field field = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
        field.setAccessible(true);
        Map<String, Object> map = ObjectUtil.castToMap(field.get(beanFactory));
        map.forEach((key, value) -> System.out.println(key + "=" + value));
        // 获取国际化信息
        System.out.println(context.getMessage("hi", null, Locale.CHINA));
        System.out.println(context.getMessage("hi", null, Locale.US));
        // 获取项目类路径下的资源
        System.out.println(context.getResource("classpath:application.properties"));
        // 获取jar包中的资源
        for (Resource resource : context.getResources("classpath*:META-INF/spring.factories")) {
            System.out.println(resource);
        }
        // 获取环境配置信息,不区分大小写
        ConfigurableEnvironment environment = context.getEnvironment();
        System.out.println(environment.getProperty("java_home"));
        System.out.println(environment.getProperty("server.port"));
    }
}
