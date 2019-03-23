package com.springboot.demo.service;

public interface RedisService {
    void setStringCache(String key, String value, long timeout);

    String getStringCache(String key);
}
