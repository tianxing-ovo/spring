package com.ltx.config;

import com.ltx.entity.Bean1;
import com.ltx.entity.Bean2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContextConfig {

    @Bean
    public Bean1 bean1() {
        return new Bean1();
    }

    @Bean
    public Bean2 bean2(Bean1 bean1) {
        return new Bean2(bean1);
    }
}
