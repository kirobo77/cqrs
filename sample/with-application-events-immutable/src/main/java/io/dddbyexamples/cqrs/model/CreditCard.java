package io.dddbyexamples.cqrs.model;

import java.util.UUID;

import io.dddbyexamples.cqrs.controller.WithdrawalCommand;
import io.dddbyexamples.cqrs.model.ports.CreditCardRecord;

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

    private long availableBalance() {
        return initialLimit - usedLimit;
    }

    private boolean thereIsMoneyToWithdraw(long amount) {
        return availableBalance() >= amount;
    }

}
