package com.example.microservice.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.example.microservice.heartbeat.BeatInfo;
import com.example.microservice.heartbeat.BeatSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nacos")
public class NacosController {
    private static final Logger logger = LoggerFactory.getLogger(NacosController.class);
    private BeatSender beatSender;

    @NacosValue(value = "${nacos.config.param1:30}", autoRefreshed = true)
    private String timeout;

    @RequestMapping("/test")
    public String testHeartBeat(Integer port){
        BeatInfo beatInfo = new BeatInfo();
        beatInfo.setIp("127.0.0.1");
        beatInfo.setPort(port);
        if(null == beatSender){
            logger.info("beatSender is null, init");
            beatSender = new BeatSender();
        }
        beatSender.addBeatInfo(beatInfo);
        return "hi,nacos";
    }

    @GetMapping("/to")
    @ResponseBody
    public String showTimeout(){
        return timeout;
    }

}
