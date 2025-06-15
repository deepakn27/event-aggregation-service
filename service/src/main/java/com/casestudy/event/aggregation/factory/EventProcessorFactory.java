package com.casestudy.event.aggregation.factory;

import com.casestudy.event.aggregation.annotation.EventType;
import com.casestudy.event.aggregation.service.processor.EventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * EventProcessorFactory is responsible for creating and managing event processors.
 * It uses Spring's ApplicationContext to find all beans annotated with @EventType
 * and maps them to their respective event classes.
 */
@Component
public class EventProcessorFactory {
    private final Map<Class<?>, EventProcessor<?>> processorMap = new HashMap<>();

    @Autowired
    public EventProcessorFactory(ApplicationContext context) {
        Map<String, Object> beans = context.getBeansWithAnnotation(EventType.class);
        for (Object bean : beans.values()) {
            EventType annotation = bean.getClass().getAnnotation(EventType.class);
            processorMap.put(annotation.value(), (EventProcessor<?>) bean);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> EventProcessor<T> getProcessor(Class<T> clazz) {
        return (EventProcessor<T>) processorMap.get(clazz);
    }
}