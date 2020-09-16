package com.example.microservice.intf;

import com.example.microservice.Entity.Student;
import com.google.common.cache.Cache;

public interface CacheService {
    String queryFromCache(Integer id);
}
