package io.dddbyexamples.cqrs.controller;

import java.util.List;
import java.util.UUID;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.dddbyexamples.cqrs.service.WithdrawalService;

@RestController
@RequestMapping("/withdrawals")
class WithdrawalsController {

    private final JdbcTemplate jdbcTemplate;
    private final WithdrawalService withdrawalProcess;

    WithdrawalsController(JdbcTemplate jdbcTemplate, WithdrawalService withdrawalsProcess) {
        this.jdbcTemplate = jdbcTemplate;
        this.withdrawalProcess = withdrawalsProcess;
    }

    @PostMapping
    ResponseEntity withdraw(@RequestBody WithdrawalCommand withdrawalCommand) {
        withdrawalProcess.withdraw(withdrawalCommand.getCard(), withdrawalCommand.getAmount());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    ResponseEntity<List<WithdrawalDto>> withdrawals(@PathParam("cardId") String cardId) {
        return ResponseEntity.ok().body(loadWithdrawalsFor(UUID.fromString(cardId)));
    }

    private List<WithdrawalDto> loadWithdrawalsFor(@PathVariable UUID cardId) {
        return jdbcTemplate.query("SELECT * FROM WITHDRAWAL WHERE CARD_ID = ?", new Object[]{cardId},
                new BeanPropertyRowMapper<>(WithdrawalDto.class));
    }
}

