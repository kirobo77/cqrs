package com.kt.cqrs.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.cqrs.domain.port.CardWithdrawn;
import com.kt.cqrs.domain.port.CreditCard;
import com.kt.cqrs.domain.port.CreditCardDao;
import com.kt.cqrs.domain.port.EventPublisher;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class WithdrawalCommandService {

    private final CreditCardDao dao;
    private final EventPublisher eventPublisher;

    @Transactional
    public void withdraw(UUID cardId, long amount) {
    	CreditCard creditCard = dao.load(cardId)
                .orElseThrow(() -> new IllegalStateException("Cannot find card with id " + cardId));
    	CardWithdrawn event = withdraw(creditCard, amount);
    	eventPublisher.publishEvent(event);
    }
    
	public CardWithdrawn withdraw(CreditCard creditCard, long amount) {
		if (thereIsMoneyToWithdraw(creditCard, amount)) {
			creditCard.setUsedLimit(creditCard.getUsedLimit() + amount);
			log.info("creditCard = {}", creditCard);
			dao.save(creditCard);
		} else {
			throw new NotEnoughMoneyException(creditCard.getId(), amount, availableBalance(creditCard));
		}
		
		return new CardWithdrawn(creditCard.getId(), amount);
	}

	public long availableBalance(CreditCard creditCard) {
		return creditCard.getInitialLimit() - creditCard.getUsedLimit();
	}

	private boolean thereIsMoneyToWithdraw(CreditCard creditCard, long amount) {
		return availableBalance(creditCard) >= amount;
	}
}
