package io.dddbyexamples.cqrs.model;

import java.util.UUID;

import io.dddbyexamples.cqrs.event.DomainEvent;
import lombok.Value;

@Value
public class CardWithdrawn implements DomainEvent {

    private final UUID cardNo;
    private final long amount;

}
