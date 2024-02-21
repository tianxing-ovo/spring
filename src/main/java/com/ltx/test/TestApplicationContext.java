package com.ltx.test;

import com.ltx.config.ApplicationContextConfig;
import com.ltx.config.WebConfig;
import com.ltx.constant.Constant;
import com.ltx.entity.Bean2;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Arrays;

/**
 * 测试ApplicationContext功能
 */
public class TestApplicationContext {
    public static void main(String[] args) {
        testClassPathXmlApplicationContext();
        testFileSystemXmlApplicationContext();
        testAnnotationConfigApplicationContext();
        testAnnotationConfigServletWebServerApplicationContext();
    }

    /**
     * 测试ClassPathXmlApplicationContext
     */
    public static void testClassPathXmlApplicationContext() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(Constant.CLASS_PATH_CONFIG_LOCATION);
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
        System.out.println(context.getBean(Bean2.class).getBean1());
    }

    /**
     * 测试FileSystemXmlApplicationContext
     */
    private static void testFileSystemXmlApplicationContext() {
        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(Constant.FILE_SYSTEM_CONFIG_LOCATION);
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
        System.out.println(context.getBean(Bean2.class).getBean1());
    }

    /**
     * 测试AnnotationConfigApplicationContext
     */
    private static void testAnnotationConfigApplicationContext() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
        System.out.println(context.getBean(Bean2.class).getBean1());
    }

    /**
     * 测试AnnotationConfigServletWebServerApplicationContext
     */
    private static void testAnnotationConfigServletWebServerApplicationContext() {
        AnnotationConfigServletWebServerApplicationContext context = new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
    }
}
