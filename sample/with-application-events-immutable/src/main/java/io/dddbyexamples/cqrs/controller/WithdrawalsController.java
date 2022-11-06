package io.dddbyexamples.cqrs.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.dddbyexamples.cqrs.model.WithdrawalService;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@RestController
@RequestMapping("/withdrawals")
class WithdrawalsController {

    private final WithdrawalService withdrawalService;
    private final WithdrawalsReader finder;

    @PostMapping
    ResponseEntity<?> withdraw(@RequestBody WithdrawalCommand withdrawalCommand) {
        withdrawalService.withdraw(withdrawalCommand);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    ResponseEntity<List<WithdrawalsReadModel>> withdrawals(@PathParam("cardId") String cardId) {
        return ResponseEntity.ok().body(finder.loadWithdrawalsFor(UUID.fromString(cardId)));
    }

}
