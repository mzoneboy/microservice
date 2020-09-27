package com.example.microservice.controller;

import com.example.microservice.intf.KingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("event")
@RestController
public class EventController {
    @Autowired
    private KingService kingService;

    @RequestMapping("say")
    public void say(){
        kingService.say("Happy New Year!");
    }
}
