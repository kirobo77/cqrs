package io.dddbyexamples.cqrs.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.dddbyexamples.cqrs.model.Withdrawal;
import io.dddbyexamples.cqrs.repository.WithdrawalRepository;
import io.dddbyexamples.cqrs.service.WithdrawalService;

@RestController
@RequestMapping("/withdrawals")
class WithdrawalsController {

    private final WithdrawalRepository withdrawalRepository;
    private final WithdrawalService withdrawalProcess;

    WithdrawalsController(WithdrawalRepository withdrawalRepository, WithdrawalService withdrawalsProcess) {
        this.withdrawalRepository = withdrawalRepository;
        this.withdrawalProcess = withdrawalsProcess;
    }

    @PostMapping
    ResponseEntity withdraw(@RequestBody WithdrawalCommand withdrawalCommand) {
        withdrawalProcess.withdraw(withdrawalCommand.getCard(), withdrawalCommand.getAmount());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    ResponseEntity<List<Withdrawal>> withdrawals(@PathParam("cardId") String cardId) {
        return ResponseEntity.ok().body(withdrawalRepository.findByCardId(cardId));
    }
}

