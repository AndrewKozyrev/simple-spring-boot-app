package com.example.demo;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(lazyInit = true)
public class TestConfig {

    @Bean
    public BeanFactoryPostProcessor lazyInitBeanFactoryPostProcessor() {
        return beanFactory -> {
            String[] beanNames = beanFactory.getBeanDefinitionNames();
            for (String beanName : beanNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                beanDefinition.setLazyInit(true);
            }
        };
    }
}
