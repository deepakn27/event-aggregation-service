package com.casestudy.models.avro.cart;

import com.casestudy.models.avro.MessageContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class hardcoded just for sample, In real scenarios these class are generated at compile time using avro
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartEvent {
    private MessageContext messageContext;
    private String eventType;
    private String eventId;
    private String tenantId;
    private String userId;
    private String sessionId;
    private Cart cart;
}
