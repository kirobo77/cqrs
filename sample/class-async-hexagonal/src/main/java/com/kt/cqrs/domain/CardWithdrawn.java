package com.kt.cqrs.domain;

import java.util.UUID;

import com.kt.cqrs.event.DomainEvent;

import lombok.Value;

@Value
public class CardWithdrawn implements DomainEvent {

    private final UUID cardNo;
    private final long amount;

}
