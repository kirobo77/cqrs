package com.kt.cqrs.adapter.in.api;

import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WithdrawalReader {

    private final JdbcTemplate jdbcTemplate;

    @SuppressWarnings("deprecation")
	List<WithdrawalReadModel> loadWithdrawalsFor(@PathVariable UUID cardId) {
        return jdbcTemplate.query("SELECT * FROM WITHDRAWAL WHERE CARD_ID = ?", new Object[]{cardId},
                new BeanPropertyRowMapper<>(WithdrawalReadModel.class));
    }
}

