package com.casestudy.models.avro.clicktobasket;

import com.casestudy.models.avro.MessageContext;
import com.casestudy.models.avro.ad.Advertisement;
import com.casestudy.models.avro.cart.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class hardcoded just for sample, In real scenarios these class are generated at compile time using avro
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClickToBasketEvent {
    private MessageContext messageContext;
    private String eventType;
    private String eventId;
    private String tenantId;
    private String userId;
    private String sessionId;
    private Advertisement advertisement;
    private Cart cart;
}
