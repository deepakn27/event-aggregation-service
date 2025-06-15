package com.casestudy.event.aggregation.service.handler.impl;

import com.casestudy.models.avro.ad.AdEvent;
import com.casestudy.models.entity.aggregations.AdInsights;
import com.casestudy.event.aggregation.service.handler.AdEventHandler;
import com.casestudy.event.aggregation.repository.AggregateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import static com.casestudy.event.aggregation.constant.MongoQueryConstant.CAMPAIGN_ID;
import static com.casestudy.event.aggregation.constant.MongoQueryConstant.CLICK_COUNT;
import static com.casestudy.event.aggregation.constant.RedisConstant.AD_CLICK_COUNT;

/**
 * Handles AdClicked events in the chain of responsibility pattern.
 * This handler increments the ad click count in the aggregate repository.
 */
@Slf4j
public class AdClickedEventHandler implements AdEventHandler {

    private AdEventHandler nextHandler;

    @Autowired
    private AggregateRepository aggregateRepository;

    /**
     * Handles the AdClicked event by incrementing the ad click count in the repository.
     *
     * @param event the AdEvent to handle
     */
    @Override
    public void handleEvent(AdEvent event) {
        String campaignId = event.getAdvertisement().getCampaignId();
        if(event.getEventType().equals("Ad_Clicked")) {
            log.info("Processing AdClicked event for campaign: {}", campaignId);
            try {
                incrementAdClickCount(campaignId);
                log.info("Successfully processed AdClicked event for campaign: {}", campaignId);
            } catch (Exception e) {
                log.error("Failed to process AdClicked event for campaign: {}. Error: {}", 
                    campaignId, e.getMessage(), e);
                throw e;
            }
            return;
        }
        log.debug("AdClicked handler passing event to next handler for campaign: {}", campaignId);
        nextHandler.handleEvent(event);
    }

    @Override
    public void setNextChain(AdEventHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    private void incrementAdClickCount(String campaignId) {
        log.debug("Incrementing click count for campaign: {}", campaignId);
        aggregateRepository.incrementCount(CAMPAIGN_ID, campaignId, CLICK_COUNT, AdInsights.class, AD_CLICK_COUNT + campaignId);
    }
}
