package com.casestudy.event.aggregation.config;

import com.casestudy.models.avro.ad.AdEvent;
import com.casestudy.models.avro.cart.CartEvent;
import com.casestudy.models.avro.clicktobasket.ClickToBasketEvent;
import com.casestudy.event.aggregation.consumer.EventConsumer;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

/**
 * Configuration class for setting up event consumers.
 * This class defines beans for consuming different types of events.
 */
@Configuration
@AllArgsConstructor
public class EventConsumerConfig {

    private final EventConsumer eventConsumer;

    /**
     * Bean for consuming AdEvent messages.
     *
     * @return a Consumer that processes AdEvent messages
     */
    @Bean
    public Consumer<Message<AdEvent>> adEventConsumer() {
        return eventConsumer.createConsumer(AdEvent.class);
    }

    /**
     * Bean for consuming CartEvent messages.
     *
     * @return a Consumer that processes CartEvent messages
     */
    @Bean
    public Consumer<Message<CartEvent>> cartEventConsumer() {
        return eventConsumer.createConsumer(CartEvent.class);
    }

    /**
     * Bean for consuming ClickToBasketEvent messages.
     *
     * @return a Consumer that processes ClickToBasketEvent messages
     */
    @Bean
    public Consumer<Message<ClickToBasketEvent>> adClickedToCartEventConsumer() {
        return eventConsumer.createConsumer(ClickToBasketEvent.class);
    }
}