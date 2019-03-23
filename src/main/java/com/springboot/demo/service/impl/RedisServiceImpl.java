package com.springboot.demo.service.impl;

import com.springboot.demo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void setStringCache(String key, String value, long timeout) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    @Override
    public String getStringCache(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
