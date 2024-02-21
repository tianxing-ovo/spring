package com.ltx.test;

import com.ltx.config.BeanFactoryConfig;
import com.ltx.constant.Constant;
import com.ltx.entity.Bean2;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 测试BeanFactory功能
 */
public class TestBeanFactory {

    private static final String BEAN_NAME = "config";

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 加载xml配置文件
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(new ClassPathResource(Constant.CLASS_PATH_CONFIG_LOCATION));
        reader.loadBeanDefinitions(new FileSystemResource(Constant.FILE_SYSTEM_CONFIG_LOCATION));
        // 获取bean定义
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(BeanFactoryConfig.class)
                .setScope(BeanDefinition.SCOPE_SINGLETON)
                .getBeanDefinition();
        // 注册bean定义
        beanFactory.registerBeanDefinition(BEAN_NAME, beanDefinition);
        // BeanFactory添加注解相关的后处理器
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);
        // 执行BeanFactoryPostProcessor
        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values().forEach(beanFactoryPostProcessor -> beanFactoryPostProcessor.postProcessBeanFactory(beanFactory));
        // 添加BeanPostProcessor
        Collection<BeanPostProcessor> beanPostProcessors = beanFactory.getBeansOfType(BeanPostProcessor.class).values();
        beanFactory.addBeanPostProcessors(beanPostProcessors);
        // 默认情况,[AutowiredAnnotationBeanPostProcessor, CommonAnnotationBeanPostProcessor]
        System.out.println(beanPostProcessors.stream()
                .map(beanPostProcessor -> beanPostProcessor.getClass().getSimpleName())
                .collect(Collectors.toList()));
        // 根据order排序,[CommonAnnotationBeanPostProcessor, AutowiredAnnotationBeanPostProcessor]
        System.out.println(beanPostProcessors.stream()
                .sorted(Objects.requireNonNull(beanFactory.getDependencyComparator()))
                .map(beanPostProcessor -> beanPostProcessor.getClass().getSimpleName())
                .collect(Collectors.toList()));
        // 提前实例化所有单例bean
        beanFactory.preInstantiateSingletons();
        // 获取Bean2
        System.out.println(beanFactory.getBean(Bean2.class).getBean1());
    }
}