package com.casestudy.event.aggregation.service.processor.impl;

import com.casestudy.event.aggregation.annotation.EventType;
import com.casestudy.event.aggregation.mapper.AdEventMapper;
import com.casestudy.models.avro.ad.AdEvent;
import com.casestudy.event.aggregation.repository.RawEventRepository;
import com.casestudy.event.aggregation.service.handler.AdEventHandler;
import com.casestudy.event.aggregation.service.processor.EventProcessor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.casestudy.event.aggregation.constant.RedisConstant.CAMPAIGN_KEY;

@Service
@EventType(AdEvent.class)
@AllArgsConstructor
@Slf4j
public class AdEventProcessor implements EventProcessor<AdEvent> {

    private final RawEventRepository rawEventRepository;
    private final AdEventHandler adEventHandler;

    /**
     * Processes the AdEvent by saving it to the raw events service and handling it with the AdEventHandler.
     *
     * @param event the AdEvent to process
     */
    @Override
    public void processEvent(AdEvent event) {
        String campaignId = event.getAdvertisement().getCampaignId();
        log.info("Starting to process AdEvent for campaign: {}", campaignId);
        
        try {
            log.debug("Converting AdEvent to entity for campaign: {}", campaignId);
            var adEventEntity = AdEventMapper.INSTANCE.adEventAvroModelToDBEntity(event);
            
            log.debug("Saving raw AdEvent to repository for campaign: {}", campaignId);
            rawEventRepository.saveRawEvent(adEventEntity);
            
            log.debug("Handling AdEvent through handler chain for campaign: {}", campaignId);
            adEventHandler.handleEvent(event);
            
            log.info("Successfully processed AdEvent for campaign: {}", campaignId);
        } catch (Exception e) {
            log.error("Failed to process AdEvent for campaign: {}. Error: {}", 
                campaignId, e.getMessage(), e);
            throw e;
        }
    }
}
