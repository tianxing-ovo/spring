package com.ltx;

import com.ltx.config.ApplicationContextConfig;
import com.ltx.config.WebConfig;
import com.ltx.constant.Constant;
import com.ltx.entity.Bean2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Arrays;

/**
 * 测试ApplicationContext功能
 */
public class ApplicationContextTest {

    /**
     * 测试从类路径(classpath)中加载配置文件
     */
    @Test
    public void testClassPathXmlApplicationContext() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(Constant.CLASS_PATH_CONFIG_LOCATION);
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
        System.out.println(context.getBean(Bean2.class).getBean1());
    }

    /**
     * 测试从文件系统中加载配置文件
     */
    @Test
    public void testFileSystemXmlApplicationContext() {
        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(Constant.FILE_SYSTEM_CONFIG_LOCATION);
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
        System.out.println(context.getBean(Bean2.class).getBean1());
    }

    /**
     * 测试基于注解配置的应用上下文
     */
    @Test
    public void testAnnotationConfigApplicationContext() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
        System.out.println(context.getBean(Bean2.class).getBean1());
    }

    /**
     * 测试基于注解配置的web应用上下文
     */
    @Test
    public void testAnnotationConfigServletWebServerApplicationContext() {
        AnnotationConfigServletWebServerApplicationContext context = new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
    }
}
