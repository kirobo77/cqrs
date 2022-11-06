package io.dddbyexamples.cqrs.service;

import io.dddbyexamples.cqrs.model.CardWithdrawn;
import io.dddbyexamples.cqrs.model.CreditCard;
import io.dddbyexamples.cqrs.model.DomainEventPublisher;
import io.dddbyexamples.cqrs.repository.CreditCardRepository;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.UUID;

@Service
public class WithdrawalService {

    private final CreditCardRepository creditCardRepository;
    private final DomainEventPublisher domainEventPublisher;

    WithdrawalService(CreditCardRepository creditCardRepository, DomainEventPublisher domainEventPublisher) {
        this.creditCardRepository = creditCardRepository;
        this.domainEventPublisher = domainEventPublisher;
    }

    @Transactional
    public void withdraw(UUID cardId, BigDecimal amount) {
        CreditCard creditCard = creditCardRepository.findById(cardId)
                .orElseThrow(() -> new IllegalStateException("Cannot find card with id " + cardId));
        creditCard.withdraw(amount);
        domainEventPublisher.publish(new CardWithdrawn(cardId, amount));
    }
}
