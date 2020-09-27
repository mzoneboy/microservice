package com.example.microservice.pattern.observer;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class KingOrderEvent extends ApplicationEvent {
    // 圣旨
    private String kingOrder;

    public KingOrderEvent(Object source, String kingOrder) {
        super(source);
        this.kingOrder = kingOrder;
    }
}
