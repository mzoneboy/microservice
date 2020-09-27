package com.example.microservice.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.naming.Name;

@Component
public class SpringAwareLearnBean implements BeanNameAware {
    private static final Logger logger = LoggerFactory.getLogger(SpringAwareLearnBean.class);

    private String name;

    @Override
    public void setBeanName(String s) {
        logger.info("==========bean name is:{}", s);
        this.name = s;
    }

    public void say(){
        logger.info("hello, world from {}", name);
    }
}
