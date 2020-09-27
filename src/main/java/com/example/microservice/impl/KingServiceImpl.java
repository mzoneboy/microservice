package com.example.microservice.impl;

import com.example.microservice.intf.KingService;
import com.example.microservice.pattern.observer.KingOrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

@Service
public class KingServiceImpl implements KingService, ApplicationEventPublisherAware {
    private static final Logger logger = LoggerFactory.getLogger(KingServiceImpl.class);
    private ApplicationEventPublisher publisher;

    @Override
    public void say(String kingOrder) {
        logger.info("==========king order everyone:{}", kingOrder);
        KingOrderEvent kingOrderEvent = new KingOrderEvent("hi", kingOrder);
        kingOrderEvent.setKingOrder(kingOrder);
        publisher.publishEvent(kingOrderEvent);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
