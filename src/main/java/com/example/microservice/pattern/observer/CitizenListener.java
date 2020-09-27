package com.example.microservice.pattern.observer;

import com.example.microservice.impl.KingServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CitizenListener implements ApplicationListener<KingOrderEvent> {
    private static final Logger logger = LoggerFactory.getLogger(CitizenListener.class);
    @Override
    public void onApplicationEvent(KingOrderEvent kingOrderEvent) {
        logger.info("==========We receive your order:{}", kingOrderEvent.getKingOrder());
    }
}
