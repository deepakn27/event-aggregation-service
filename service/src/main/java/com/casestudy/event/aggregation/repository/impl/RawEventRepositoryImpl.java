package com.casestudy.event.aggregation.repository.impl;

import com.casestudy.event.aggregation.repository.MongoRepository;
import com.casestudy.event.aggregation.repository.RawEventRepository;
import com.casestudy.event.aggregation.repository.RedisRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@AllArgsConstructor
public class RawEventRepositoryImpl implements RawEventRepository {

    private final MongoRepository mongoRepository;
    private final RedisRepository<String, Object> redisRepository;

    @Override
    public <T> void saveRawEvent(String redisKey, T event) {
        log.info("Saving raw event in mongoDB: {} and in redis with key {}", event, redisKey);
        mongoRepository.save(event);
        redisRepository.save(redisKey, event);
    }

    public <T> void saveRawEvent(T event) {
        log.info("Saving raw event in mongoDB: {}", event);
        mongoRepository.save(event);
    }
}
