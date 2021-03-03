package com.example.microservice.controller;

import com.example.microservice.configuration.JedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

@RequestMapping("redis")
@RestController
public class RedisController {
    @Autowired
    private JedisConfig jedisConfig;

    @RequestMapping("set")
    public String set(@RequestParam String value) {
        JedisPool jedisPool = jedisConfig.redisPoolFactory();
        jedisPool.getResource().set("key1", value);
        return jedisPool.getResource().get("key1");
    }
}