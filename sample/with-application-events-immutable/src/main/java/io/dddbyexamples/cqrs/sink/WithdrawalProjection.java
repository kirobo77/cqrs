package io.dddbyexamples.cqrs.sink;

import java.util.UUID;

import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import io.dddbyexamples.cqrs.model.CardWithdrawn;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
class WithdrawalProjection {

    private final JdbcTemplate jdbcTemplate;

    WithdrawalProjection(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener
    public void addWithdrawalOnCardWithdrawn(CardWithdrawn event) {
    	log.info("subscribeEvent = {}", event);
        jdbcTemplate.update("INSERT INTO WITHDRAWAL(ID, CARD_ID, AMOUNT) VALUES (?,?,?)", UUID.randomUUID(), event.getCardNo(), event.getAmount());
    }
}
