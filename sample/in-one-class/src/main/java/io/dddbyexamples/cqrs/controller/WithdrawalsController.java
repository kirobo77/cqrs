package io.dddbyexamples.cqrs.controller;

import io.dddbyexamples.cqrs.model.Withdrawal;
import io.dddbyexamples.cqrs.repository.CreditCardRepository;
import io.dddbyexamples.cqrs.service.WithdrawalProcess;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/withdrawals")
class WithdrawalsController {

    private final CreditCardRepository creditCardRepository;
    private final WithdrawalProcess withdrawalProcess;

    WithdrawalsController(CreditCardRepository creditCardRepository, WithdrawalProcess withdrawalsProcess) {
        this.creditCardRepository = creditCardRepository;
        this.withdrawalProcess = withdrawalsProcess;
    }

    @PostMapping
    ResponseEntity withdraw(@RequestBody WithdrawalCommand withdrawalCommand) {
        withdrawalProcess.withdraw(withdrawalCommand.getCard(), withdrawalCommand.getAmount());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    ResponseEntity<List<Withdrawal>> withdrawals(@PathParam("cardId") String cardId) {
        return ResponseEntity.ok().body(creditCardRepository.getOne(UUID.fromString(cardId)).getWithdrawals());
    }
}

