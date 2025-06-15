package com.casestudy.event.aggregation.service.handler.impl;

import com.casestudy.models.avro.ad.AdEvent;
import com.casestudy.models.entity.aggregations.AdInsights;
import com.casestudy.event.aggregation.service.handler.AdEventHandler;
import com.casestudy.event.aggregation.repository.AggregateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import static com.casestudy.event.aggregation.constant.MongoQueryConstant.CAMPAIGN_ID;
import static com.casestudy.event.aggregation.constant.MongoQueryConstant.VIEW_COUNT;
import static com.casestudy.event.aggregation.constant.RedisConstant.AD_VIEW_COUNT;

/**
 * Handles AdClicked events in the chain of responsibility pattern.
 * This handler increments the ad click count in the aggregate repository.
 */
@Slf4j
public class AdViewedEventHandler implements AdEventHandler {

    private AdEventHandler nextHandler;

    @Autowired
    private AggregateRepository aggregateRepository;

    /**
     * Handles the AdViewed event by incrementing the ad view count in the repository.
     *
     * @param event the AdEvent to handle
     */
    @Override
    public void handleEvent(AdEvent event) {
        String campaignId = event.getAdvertisement().getCampaignId();
        if(event.getEventType().equals("Ad_Viewed")) {
            log.info("Processing AdViewed event for campaign: {}", campaignId);
            try {
                incrementAdViewCount(campaignId);
                log.info("Successfully processed AdViewed event for campaign: {}", campaignId);
            } catch (Exception e) {
                log.error("Failed to process AdViewed event for campaign: {}. Error: {}", 
                    campaignId, e.getMessage(), e);
                throw e;
            }
        }
    }

    @Override
    public void setNextChain(AdEventHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    private void incrementAdViewCount(String campaignId) {
        log.debug("Incrementing view count for campaign: {}", campaignId);
        aggregateRepository.incrementCount(CAMPAIGN_ID, campaignId, VIEW_COUNT, AdInsights.class, AD_VIEW_COUNT + campaignId);
    }
}
