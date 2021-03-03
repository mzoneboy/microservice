package com.example.microservice.controller;

/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;*/
import org.springframework.web.bind.annotation.RequestMapping;
/*import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;*/

@RequestMapping("/activemq")
//@RestController
public class ActivemqController {
  /*  @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    @RequestMapping("/sendQ")
    public String sendQueue(String str) {
        this.sendMessage(this.queue, str);
        return "success";
    }

    @RequestMapping("/sendT")
    public String sendTopic(String str) {
        this.sendMessage(this.topic, str);
        return "success";
    }

    // 发送消息，destination是发送到的队列，message是待发送的消息
    private void sendMessage(Destination destination, final String message){
        jmsMessagingTemplate.convertAndSend(destination, message);
    }*/
}
