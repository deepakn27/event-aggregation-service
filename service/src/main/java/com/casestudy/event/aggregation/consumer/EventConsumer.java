package com.casestudy.event.aggregation.consumer;

import com.casestudy.event.aggregation.factory.EventProcessorFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

/**
 * EventConsumer is responsible for consuming events and processing them
 * using the appropriate event processor based on the event type.
 */
@Service
@Slf4j
@AllArgsConstructor
public class EventConsumer {

    private final EventProcessorFactory eventProcessorFactory;

    /**
     * Creates a consumer for the specified event type.
     *
     * @param clazz the class of the event type
     * @param <T>   the type of the event
     * @return a Consumer that processes messages of the specified event type
     */
    public <T> Consumer<Message<T>> createConsumer(Class<T> clazz) {
        return message -> {
            String eventType = clazz.getSimpleName();
            try {
                T event = message.getPayload();
                log.info("Starting to process {} event. Event details: {}", eventType, event);
                eventProcessorFactory.getProcessor(clazz).processEvent(event);
                log.info("Successfully processed {} event", eventType);
            } catch (Exception e) {
                log.error("Failed to process {} event. Error: {}. Stack trace: {}", 
                    eventType, e.getMessage(), e);
                throw e;
            }
        };
    }
}
