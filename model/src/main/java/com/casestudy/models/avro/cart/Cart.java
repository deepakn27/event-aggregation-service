package com.casestudy.models.avro.cart;

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
public class Cart {
    private String cartId;
    private String userId;
    private String productId;
    private Instant timestamp;
    private int quantity;
}
