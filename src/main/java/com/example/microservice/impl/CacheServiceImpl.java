package com.example.microservice.impl;

import com.example.microservice.intf.CacheService;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class CacheServiceImpl implements CacheService {
    private static final  Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);

    private Cache<String,String> cache;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public String queryFromCache(Integer id) {
        String key = String.valueOf(id);
        if(null == cache){
            cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(3   , TimeUnit.SECONDS).build();
        }

        String name = null;
        try {
            name = cache.get(key, new Callable<String>() {
                @Override
                public String call() throws Exception {
                    Object[] params = new Object[] {id};
                    String name = jdbcTemplate.queryForObject("SELECT name FROM test_db.student where id =?",params
                            , String.class);
                    if(null != name){
                        cache.put(key, name);
                        logger.info("从数据库中获取到数据:" + name);
                        return name;
                    } else {
                        return null;
                    }
                }
            });
        } catch (ExecutionException e) {
            logger.error(e.getMessage());
        }

        logger.info("从缓存中获取到数据:" + name);
        return name;
    }
}
