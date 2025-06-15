package com.casestudy.event.aggregation.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
@Slf4j
public class MongoRepository {

    private final MongoTemplate mongoTemplate;

    /**
     * Saves the given entity to the MongoDB collection.
     *
     * @param entity the entity to save
     * @param <T>    the type of the entity
     * @return the saved entity
     */
    public <T> T save(T entity) {
        return mongoTemplate.save(entity);
    }

    /**
     * Finds and modifies a document in the MongoDB collection.
     *
     * @param query   the query to find the document
     * @param update  the update to apply
     * @param options options for the find and modify operation
     * @param classz  the class type of the document
     * @param <T>     the type of the document
     */
    public <T> void findAndModify(Query query, Update update, FindAndModifyOptions options, Class<T> classz) {
        mongoTemplate.findAndModify(query,update, options, classz);
    }

}
