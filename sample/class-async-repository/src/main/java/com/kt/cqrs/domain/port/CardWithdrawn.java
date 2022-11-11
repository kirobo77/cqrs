package com.kt.cqrs.domain.port;

import java.util.UUID;

import com.kt.cqrs.adapter.in.event.DomainEvent;

import lombok.Value;

@Value
public class CardWithdrawn implements DomainEvent {

    private final UUID cardNo;
    private final long amount;

}
