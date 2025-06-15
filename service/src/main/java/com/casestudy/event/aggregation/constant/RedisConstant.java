package com.casestudy.event.aggregation.constant;

public class RedisConstant {
    public static final String REDIS_KEY_PREFIX = "event-aggregation:";
    public static final String AD_CLICK_COUNT = REDIS_KEY_PREFIX + "ad-click-count:";
    public static final String AD_VIEW_COUNT = REDIS_KEY_PREFIX + "ad-view-count:";
    public static final String CLICK_TO_BASKET_COUNT = REDIS_KEY_PREFIX + "click-to-basket-count:";
    public static final String CAMPAIGN_KEY = REDIS_KEY_PREFIX + "campaign:";
}
