package com.ltx.util;

import io.github.tianxingovo.common.ObjectUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

/**
 * BeanFactory工具类
 */
public class BeanFactoryUtil {

    /**
     * 打印所有单例对象
     */
    @SneakyThrows
    public static void printSingletonObjects(BeanFactory beanFactory) {
        Field field = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
        field.setAccessible(true);
        Map<String, Object> map = ObjectUtil.castToMap(field.get(beanFactory));
        map.forEach((key, value) -> System.out.println(key + "=" + value));
    }

    /**
     * 打印Bean定义名称
     */
    public static void printBeanDefinitionNames(DefaultListableBeanFactory beanFactory) {
        Arrays.stream(beanFactory.getBeanDefinitionNames()).forEach(System.out::println);
    }
}
