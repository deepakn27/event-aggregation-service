package com.casestudy.event.aggregation.service.processor;

public interface EventProcessor<T> {

    /**
     * Processes the given event.
     *
     * @param event the event to process
     */
    void processEvent(T event);

}
