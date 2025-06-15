package com.casestudy.event.aggregation.service.handler;

import com.casestudy.models.avro.ad.AdEvent;

/**
 * Interface for handling ad events in a chain of responsibility pattern.
 * Each handler can process an ad event and pass it to the next handler in the chain.
 */
public interface AdEventHandler {

    /**
     * Handles the given ad event.
     *
     * @param event the ad event to handle
     */
    void handleEvent(AdEvent event);

    /**
     * Sets the next handler in the chain.
     *
     * @param nextHandler the next ad event handler
     */
    void setNextChain(AdEventHandler nextHandler);
}
