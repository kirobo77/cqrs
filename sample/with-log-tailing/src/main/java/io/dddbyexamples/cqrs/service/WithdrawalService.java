package io.dddbyexamples.cqrs.service;

import io.dddbyexamples.cqrs.model.CreditCard;
import io.dddbyexamples.cqrs.repository.CreditCardRepository;
import io.dddbyexamples.cqrs.repository.WithdrawalRepository;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class WithdrawalService {

    private final CreditCardRepository creditCardRepository;
    private final WithdrawalRepository withdrawalRepository;

    WithdrawalService(CreditCardRepository creditCardRepository, WithdrawalRepository withdrawalRepository) {
        this.creditCardRepository = creditCardRepository;
        this.withdrawalRepository = withdrawalRepository;
    }

    @Transactional
    public void withdraw(String cardId, BigDecimal amount) {
        CreditCard creditCard = creditCardRepository.findById(cardId)
                .orElseThrow(() -> new IllegalStateException("Cannot find card with id " + cardId));
        creditCard.withdraw(amount);
    }

}
