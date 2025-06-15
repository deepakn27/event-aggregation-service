package com.casestudy.event.aggregation.repository;

public interface AggregateRepository {

    /**
     * Increments the count of a specific field in the MongoDB collection and in redis .
     *
     * @param fieldToSearch   the field to search for
     * @param fieldValue      the value of the field to search for
     * @param counterToIncrease the counter field to increment
     * @param classz          the class type of the entity
     * @param redisKey        the Redis key for caching
     * @param <T>             the type of the entity
     */
    <T> void incrementCount(String fieldToSearch, String fieldValue, String counterToIncrease, Class<T> classz, String redisKey);
}
