package com.kt.cqrs.command.repository.entity;

import java.time.Instant;
import java.util.UUID;

public class CardWithdrawn {

    private UUID cardNo;
    private long amount;
    private Instant timestamp = Instant.now();

    public CardWithdrawn(UUID cardNo, long amount) {
        this.cardNo = cardNo;
        this.amount = amount;
    }


    public UUID getCardNo() {
        return cardNo;
    }

    public long getAmount() {
        return amount;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

}
