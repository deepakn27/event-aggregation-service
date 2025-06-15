package com.casestudy.event.aggregation.service.processor.impl;

import com.casestudy.event.aggregation.annotation.EventType;
import com.casestudy.event.aggregation.mapper.CartEventMapper;
import com.casestudy.models.avro.cart.CartEvent;
import com.casestudy.event.aggregation.repository.RawEventRepository;
import com.casestudy.event.aggregation.service.processor.EventProcessor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@EventType(CartEvent.class)
@AllArgsConstructor
@Slf4j
public class CartEventProcessor implements EventProcessor<CartEvent> {

    private final RawEventRepository rawEventRepository;

    /**
     * Processes the CartEvent by saving it to the raw events service.
     *
     * @param event the CartEvent to process
     */
    @Override
    public void processEvent(CartEvent event) {
        log.info("Processing CartEvent: {}", event);
        var cartEventEntity = CartEventMapper.INSTANCE.cartEventAvroModelToDBEntity(event);
        rawEventRepository.saveRawEvent(cartEventEntity);
    }
}
