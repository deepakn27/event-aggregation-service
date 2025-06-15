package com.casestudy.event.aggregation.repository;

public interface RawEventRepository {

    /**
     * Saves a raw event to the both redis and mongoDB Collection.
     *
     * @param redisKey the Redis key under which the event will be stored
     * @param event the raw event to save
     * @param <T> the type of the event
     */
    <T> void saveRawEvent(String redisKey, T event);

    /**
     * Saves a raw event to the MongoDB collection.
     *
     * @param event the raw event to save
     * @param <T> the type of the event
     */
    <T> void saveRawEvent(T event);
}
