package io.dddbyexamples.cqrs.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WithdrawalsReader {

    private final JdbcTemplate jdbcTemplate;

    List<WithdrawalsReadModel> loadWithdrawalsFor(@PathVariable UUID cardId) {
        return jdbcTemplate.query("SELECT * FROM WITHDRAWAL WHERE CARD_ID = ?", new Object[]{cardId},
                new BeanPropertyRowMapper<>(WithdrawalsReadModel.class));
    }
}
