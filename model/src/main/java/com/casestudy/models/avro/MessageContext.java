package com.casestudy.models.avro;

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
public class MessageContext {
    private String producer;
    private String messageId;
    private String correlationId;
    private Instant timeStamp;
}
