package io.dddbyexamples.cqrs.service;

import io.dddbyexamples.cqrs.model.CardWithdrawn;
import io.dddbyexamples.cqrs.model.CreditCard;
import io.dddbyexamples.cqrs.repository.CreditCardRepository;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.UUID;

@Service
public class WithdrawalService {

    private final CreditCardRepository creditCardRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    WithdrawalService(CreditCardRepository creditCardRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.creditCardRepository = creditCardRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional
    public void withdraw(UUID cardId, BigDecimal amount) {
        CreditCard creditCard = creditCardRepository.findById(cardId)
                .orElseThrow(() -> new IllegalStateException("Cannot find card with id " + cardId));
        CardWithdrawn event = creditCard.withdraw(amount);
        applicationEventPublisher.publishEvent(event);
    }
}
