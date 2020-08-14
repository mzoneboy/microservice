package com.example.microservice.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.microservice.intf.StuRpcService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("rpc")
@RestController
public class RpcConsumerController {
    @Reference
    private StuRpcService stuRpcService;

    @RequestMapping("invoke")
    public String invokeRpcService(){
        return String.valueOf(stuRpcService.add(1, 2));
    }
}
