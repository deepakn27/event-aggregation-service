package com.casestudy.event.aggregation.service.processor.impl;

import com.casestudy.event.aggregation.annotation.EventType;
import com.casestudy.event.aggregation.mapper.ClickToBasketEventMapper;
import com.casestudy.models.avro.clicktobasket.ClickToBasketEvent;
import com.casestudy.models.entity.aggregations.ClickToBasketInsights;
import com.casestudy.event.aggregation.repository.RawEventRepository;
import com.casestudy.event.aggregation.service.processor.EventProcessor;
import com.casestudy.event.aggregation.repository.AggregateRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.casestudy.event.aggregation.constant.MongoQueryConstant.CAMPAIGN_ID;
import static com.casestudy.event.aggregation.constant.MongoQueryConstant.MONGO_CLICK_TO_BASKET_COUNT;
import static com.casestudy.event.aggregation.constant.RedisConstant.CAMPAIGN_KEY;
import static com.casestudy.event.aggregation.constant.RedisConstant.CLICK_TO_BASKET_COUNT;

@Service
@EventType(ClickToBasketEvent.class)
@AllArgsConstructor
@Slf4j
public class AdClickedToCartEventProcessor implements EventProcessor<ClickToBasketEvent> {

    private final RawEventRepository rawEventRepository;
    private final AggregateRepository aggregateRepository;

    /**
     * Processes the ClickToBasketEvent by saving it to the raw events service and incrementing the click-to-basket count
     * in the aggregate repository.
     *
     * @param event the ClickToBasketEvent to process
     */
    @Override
    public void processEvent(ClickToBasketEvent event) {
        log.info("Processing AdClickedToCartEvent: {}", event);
        var clickToBasketEntity = ClickToBasketEventMapper.INSTANCE.clickToBasketEventAvroModelToDBEntity(event);
        rawEventRepository.saveRawEvent(clickToBasketEntity);
        incrementClickToBasketCount(event.getAdvertisement().getCampaignId());
    }

    public void incrementClickToBasketCount(String campaignId) {
        aggregateRepository.incrementCount(CAMPAIGN_ID, campaignId, MONGO_CLICK_TO_BASKET_COUNT, ClickToBasketInsights.class, CLICK_TO_BASKET_COUNT + campaignId);
    }
}
