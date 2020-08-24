package com.example.microservice.iot.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ActivemqListener {

    private final static  Logger logger = LoggerFactory.getLogger(ActivemqListener.class);

    @JmsListener(destination="${spring.activemq.queue-name}", containerFactory="queueListener")
    public void receiveMsg(String msg){
        logger.info(msg);
    }

    @JmsListener(destination="${spring.activemq.topic-name}", containerFactory="topicListener")
    public void subMsg(String msg){
        logger.info(msg);
    }
}
