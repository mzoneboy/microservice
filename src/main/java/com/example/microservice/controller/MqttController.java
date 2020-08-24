package com.example.microservice.controller;

import com.example.microservice.iot.mosquitto.MqttPublisher;
import com.example.microservice.iot.mosquitto.MqttSubscriber;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("mqtt")
@RestController
public class MqttController {
    private static final Logger logger = LoggerFactory.getLogger(MqttController.class);
    @RequestMapping("pub")
    public String pubMsg(String topic, String msg){
        MqttPublisher mqttPublisher;
        try {
            mqttPublisher = new MqttPublisher();
            MqttMessage mqttMessage = new MqttMessage();
            // Quality of Service，QoS
            // •级别0：尽力而为。消息可能会丢，但绝不会重复传输
            // •级别1：消息绝不会丢，但可能会重复传输
            // •级别2：恰好一次。每条消息肯定会被传输一次且仅传输一次
            mqttMessage.setQos(1);
            mqttMessage.setRetained(true);
            mqttMessage.setPayload(msg.getBytes());
            mqttPublisher.publish(topic, mqttMessage);
        } catch (MqttException e) {
            logger.error(e.toString());
        }

        return "success";
    }

    @RequestMapping("sub")
    public String subTopic(String topic){
        MqttSubscriber mqttSubscriber;
        try {
            mqttSubscriber = new MqttSubscriber();
            mqttSubscriber.subscribe(topic);
        } catch (MqttException e) {
            logger.error(e.toString());
        }

        return "success";
    }
}
