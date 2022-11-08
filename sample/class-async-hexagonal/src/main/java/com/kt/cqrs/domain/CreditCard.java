package com.kt.cqrs.domain;

import java.util.UUID;

import com.kt.cqrs.adapter.in.api.WithdrawalCommand;
import com.kt.cqrs.domain.ports.CreditCardRecord;

class CreditCard {

    private final UUID cardId;
    private final long usedLimit;
    private final long initialLimit;


    CreditCard(CreditCardRecord record) {
        this.cardId = record.getId();
        this.usedLimit = record.getUsedLimit();
        this.initialLimit = record.getInitialLimit();
    }

    CardWithdrawn withdraw(WithdrawalCommand command) {
        long amount = command.getAmount();
        if (thereIsMoneyToWithdraw(amount)) {
            return new CardWithdrawn(cardId, amount);
        } else {
            throw new NotEnoughMoneyException(cardId, command.getAmount(), availableBalance());
        }
    }

    long availableBalance() {
        return initialLimit - usedLimit;
    }

    boolean thereIsMoneyToWithdraw(long amount) {
        return availableBalance() >= amount;
    }

}
