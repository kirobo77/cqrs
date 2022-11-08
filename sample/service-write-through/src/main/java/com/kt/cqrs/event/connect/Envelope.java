
package com.kt.cqrs.event.connect;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "schema",
    "payload"
})
@Data
public class Envelope {

    @JsonProperty("schema")
    public Schema schema;
    @JsonProperty("payload")
    public Payload payload;

}
