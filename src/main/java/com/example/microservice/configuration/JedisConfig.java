package com.example.microservice.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfig extends CachingConfigurerSupport {
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;


    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;

   /* @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWaitMillis;*/

    @Bean(name="jedisPoolFactory")
    public JedisPool redisPoolFactory(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(1000);
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMinIdle(minIdle);

        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig,host,port,1000,null);

        return jedisPool;
    }

}