package com.casestudy.event.aggregation.config;

import com.casestudy.event.aggregation.service.handler.AdEventHandler;
import com.casestudy.event.aggregation.service.handler.impl.AdClickedEventHandler;
import com.casestudy.event.aggregation.service.handler.impl.AdViewedEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up the responsibility chain for ad event handling.
 * This configuration defines the chain of handlers for processing ad events.
 */
@Configuration
public class AdEventResponsibilityChainConfig {

    /**
     * Bean definition for AdEventHandler.
     * This handler processes ad clicked events and delegates to the next handler in the chain.
     *
     * @return an instance of AdClickedEventHandler
     */
    @Bean
    public AdEventHandler adEventHandler() {
        AdClickedEventHandler adClickedEventHandler = new AdClickedEventHandler();
        adClickedEventHandler.setNextChain(adViewedEventHandler());
        return adClickedEventHandler;
    }

    /**
     * Bean definition for AdViewedEventHandler.
     * This handler processes ad viewed events in the responsibility chain.
     *
     * @return an instance of AdViewedEventHandler
     */
    @Bean
    public AdViewedEventHandler adViewedEventHandler() {
        return new AdViewedEventHandler();
    }

}
