package io.dddbyexamples.cqrs.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import io.dddbyexamples.cqrs.service.WithdrawalService;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/withdrawals")
class WithdrawalsController {

    private final JdbcTemplate jdbcTemplate;
    private final WithdrawalService withdrawalService;

    WithdrawalsController(JdbcTemplate jdbcTemplate, WithdrawalService withdrawalsProcess) {
        this.jdbcTemplate = jdbcTemplate;
        this.withdrawalService = withdrawalsProcess;
    }

    @PostMapping
    ResponseEntity<?> withdraw(@RequestBody WithdrawalCommand withdrawalCommand) {
        withdrawalService.withdraw(withdrawalCommand.getCard(), withdrawalCommand.getAmount());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    ResponseEntity<List<WithdrawalDto>> withdrawals(@PathParam("cardId") String cardId) {
        return ResponseEntity.ok().body(loadWithdrawalsFor(UUID.fromString(cardId)));
    }

    @SuppressWarnings("deprecation")
	private List<WithdrawalDto> loadWithdrawalsFor(@PathVariable UUID cardId) {
        return jdbcTemplate.query("SELECT * FROM WITHDRAWAL WHERE CARD_ID = ?", new Object[]{cardId},
                new BeanPropertyRowMapper<>(WithdrawalDto.class));
    }
}
