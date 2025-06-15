package com.casestudy.event.aggregation.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@AllArgsConstructor
@Slf4j
public class RedisRepository <K, V> {

    private final RedisTemplate<K,V> redisTemplate;

    //pick from configuration in prod environment
    private final Integer REDIS_TIMEOUT = 1;
    private final TimeUnit TIME_UNIT = TimeUnit.HOURS;

    /**
     * Saves the given key-value pair in Redis with an expiration time.
     * If the key already exists, it will not overwrite the existing value.
     *
     * @param key   the key to save
     * @param value the value to save
     */
    public void save(K key, V value) {
        redisTemplate.opsForValue().setIfAbsent(key, value, REDIS_TIMEOUT, TIME_UNIT);
    }

    /**
     * Finds and increment the value associated with the given key in Redis.
     * If the key does not exist, it adds the key.
     *
     * @param key the key to find
     * @return the value associated with the key, or null if not found
     */
    public void findAndIncrement(K key) {
        redisTemplate.opsForValue().increment(key, 1);
        redisTemplate.expire(key, REDIS_TIMEOUT, TIME_UNIT);
    }

}
