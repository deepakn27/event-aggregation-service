package com.casestudy.models.avro.ad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Class hardcoded just for sample, In real scenarios these class are generated at compile time using avro
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Advertisement {
    private String adId;
    private String campaignId;
    private String productId;
    private Instant timestamp;
    private String adPlacement;
}
