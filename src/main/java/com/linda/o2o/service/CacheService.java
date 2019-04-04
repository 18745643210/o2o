package com.linda.o2o.service;

public interface CacheService {
    /**
     * 根据key的前缀删除匹配模式下的所有key—value
     * @param keyPrefix
     */
    public void removeFromCache(String keyPrefix);
}
