package com.kt.cqrs.event;

import java.util.UUID;

import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.kt.cqrs.command.repository.entity.CardWithdrawn;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class QueryUpdater {

    private final JdbcTemplate jdbcTemplate;

    @EventListener
    public void addWithdrawalOnCardWithdrawn(CardWithdrawn event) {
        jdbcTemplate.update("INSERT INTO WITHDRAWAL(ID, CARD_ID, AMOUNT) VALUES (?,?,?)", UUID.randomUUID(), event.getCardNo(), event.getAmount());
    }
}
