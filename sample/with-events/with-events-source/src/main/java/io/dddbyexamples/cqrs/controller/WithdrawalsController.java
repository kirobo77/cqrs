package io.dddbyexamples.cqrs.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.dddbyexamples.cqrs.service.WithdrawalService;

@RestController
@RequestMapping("/withdrawals")
class WithdrawalsController {

    private final WithdrawalService withdrawalProcess;

    WithdrawalsController(WithdrawalService withdrawalsProcess) {
        this.withdrawalProcess = withdrawalsProcess;
    }

    @PostMapping
    ResponseEntity<?> withdraw(@RequestBody WithdrawalCommand withdrawalCommand) {
        withdrawalProcess.withdraw(withdrawalCommand.getCard(), withdrawalCommand.getAmount());
        return ResponseEntity.ok().build();
    }

}

