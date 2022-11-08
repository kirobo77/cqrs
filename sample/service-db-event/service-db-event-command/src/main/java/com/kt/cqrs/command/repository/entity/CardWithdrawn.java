package com.kt.cqrs.command.repository.entity;


import java.util.Date;
import java.util.UUID;

import com.kt.cqrs.command.event.DomainEvent;

public class CardWithdrawn implements DomainEvent {

    private UUID cardNo;
    private long amount;
    private Date timestamp = new Date();

    public CardWithdrawn(UUID cardNo, long amount) {
        this.cardNo = cardNo;
        this.amount = amount;
    }

    CardWithdrawn() {

    }

    public UUID getCardNo() {
        return cardNo;
    }

    public long getAmount() {
        return amount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String getType() {
        return "card-withdrawn";
    }
}
