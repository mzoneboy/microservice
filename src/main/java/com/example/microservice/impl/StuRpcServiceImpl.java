package com.example.microservice.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.microservice.intf.StuRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Service
@Component
public class StuRpcServiceImpl implements StuRpcService {
    private final static Logger logger = LoggerFactory.getLogger(StuRpcServiceImpl.class);
    @Override
    public void sayHi() {
        logger.info("StuRpcServiceImpl#sayHi》》》》》》》");
        System.out.println("StuRpcServiceImpl#sayHi》》》》》》》");
    }
}
