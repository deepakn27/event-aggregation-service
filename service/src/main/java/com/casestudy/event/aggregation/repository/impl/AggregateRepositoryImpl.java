package com.casestudy.event.aggregation.repository.impl;

import com.casestudy.event.aggregation.repository.AggregateRepository;
import com.casestudy.event.aggregation.repository.MongoRepository;
import com.casestudy.event.aggregation.repository.RedisRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@AllArgsConstructor
public class AggregateRepositoryImpl implements AggregateRepository {

    private final MongoRepository mongoRepository;
    private final RedisRepository<String, Object> redisRepository;

    public <T> void incrementCount(String fieldToSearch, String fieldValue, String counterToIncrease, Class<T> classz, String redisKey) {
        log.debug("Starting count increment operation - Field: {}, Value: {}, Counter: {}, Class: {}, RedisKey: {}",
                 fieldToSearch, fieldValue, counterToIncrease, classz.getSimpleName(), redisKey);
        try {
            incrementCountInMongo(fieldToSearch, fieldValue, counterToIncrease, classz);
            log.debug("Successfully incremented count in MongoDB for {} with value {}", fieldToSearch, fieldValue);
            
            incrementCountInRedis(redisKey);
            log.debug("Successfully incremented count in Redis for key {}", redisKey);
            
            log.info("Completed count increment operation for {}={} in both MongoDB and Redis", fieldToSearch, fieldValue);
        } catch (Exception e) {
            log.error("Failed to increment count - Field: {}, Value: {}, Error: {}", 
                fieldToSearch, fieldValue, e.getMessage(), e);
            throw e;
        }
    }

    private <T> void incrementCountInMongo(String fieldToSearch, String fieldValue, String counterToIncrease, Class<T> classz) {
        log.trace("Building MongoDB query for increment operation");
        Query query = new Query();
        query.addCriteria(Criteria.where(fieldToSearch).is(fieldValue));
        Update update = new Update().inc(counterToIncrease, 1);
        FindAndModifyOptions options = FindAndModifyOptions.options().upsert(true).returnNew(true);
        
        log.trace("Executing MongoDB findAndModify operation");
        mongoRepository.findAndModify(query, update, options, classz);
    }

    private void incrementCountInRedis(String key) {
        log.trace("Incrementing count in Redis for key: {}", key);
        redisRepository.findAndIncrement(key);
    }
}
