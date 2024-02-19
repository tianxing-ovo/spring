package com.ltx.test;

import lombok.Getter;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 测试BeanFactory功能
 */
public class TestBeanFactory {

    private static final String BEAN_NAME = "config";

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 获取bean定义
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Config.class)
                .setScope(BeanDefinition.SCOPE_SINGLETON)
                .getBeanDefinition();
        // 注册bean定义
        beanFactory.registerBeanDefinition(BEAN_NAME, beanDefinition);
        // BeanFactory添加注解相关的后处理器
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);
        // 执行BeanFactoryPostProcessor
        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values().forEach(beanFactoryPostProcessor -> beanFactoryPostProcessor.postProcessBeanFactory(beanFactory));
        // 添加BeanPostProcessor
        beanFactory.addBeanPostProcessors(beanFactory.getBeansOfType(BeanPostProcessor.class).values());
        // 提前实例化所有单例bean
        beanFactory.preInstantiateSingletons();
        // 获取Bean2
        System.out.println(beanFactory.getBean(Bean1.class).getBean2());
    }

    @Configuration
    public static class Config {

        @Bean
        public Bean1 bean1() {
            return new Bean1();
        }

        @Bean
        public Bean2 bean2() {
            return new Bean2();
        }

    }

    @Getter
    public static class Bean1 {

        @Resource
        private Bean2 bean2;

        public Bean1() {
            System.out.println("构造Bean1");
        }
    }

    public static class Bean2 {
        public Bean2() {
            System.out.println("构造Bean2");
        }
    }
}