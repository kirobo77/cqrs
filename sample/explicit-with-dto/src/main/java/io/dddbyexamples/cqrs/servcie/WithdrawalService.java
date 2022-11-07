package io.dddbyexamples.cqrs.servcie;

import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.dddbyexamples.cqrs.controller.WithdrawalDto;
import io.dddbyexamples.cqrs.model.CreditCard;
import io.dddbyexamples.cqrs.model.NotEnoughMoneyException;
import io.dddbyexamples.cqrs.repository.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WithdrawalService {

    private final CreditCardRepository creditCardRepository;
    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void withdraw(UUID cardId, long amount) {
        CreditCard creditCard = creditCardRepository.findById(cardId)
                .orElseThrow(() -> new IllegalStateException("Cannot find card with id " + cardId));
        withdraw(creditCard, amount);
        jdbcTemplate.update("INSERT INTO WITHDRAWAL(ID, CARD_ID, AMOUNT) VALUES (?,?,?)", UUID.randomUUID(), cardId, amount);
    }
    
	public void withdraw(CreditCard creditCard, long amount) {
		if (thereIsMoneyToWithdraw(creditCard, amount)) {
			creditCard.setUsedLimit(creditCard.getUsedLimit() + amount);
			log.info("creditCard = {}", creditCard);
			creditCardRepository.save(creditCard);
		} else {
			throw new NotEnoughMoneyException(creditCard.getId(), amount, availableBalance(creditCard));
		}
	}

	public long availableBalance(CreditCard creditCard) {
		return creditCard.getInitialLimit() - creditCard.getUsedLimit();
	}

	private boolean thereIsMoneyToWithdraw(CreditCard creditCard, long amount) {
		return availableBalance(creditCard) >= amount;
	}

	public List<WithdrawalDto> withdraw(UUID cardId) {
		return jdbcTemplate.query("SELECT * FROM WITHDRAWAL WHERE CARD_ID = ?", new Object[]{cardId}, new BeanPropertyRowMapper<>(WithdrawalDto.class));
    }

}
