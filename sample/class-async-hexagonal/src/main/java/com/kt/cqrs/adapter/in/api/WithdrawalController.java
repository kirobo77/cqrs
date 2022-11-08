package com.kt.cqrs.adapter.in.api;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kt.cqrs.domain.WithdrawalService;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@RestController
@RequestMapping("/withdrawal")
class WithdrawalController {

    private final WithdrawalService withdrawalService;
    private final WithdrawalReader finder;

    @PostMapping
    ResponseEntity<?> withdraw(@RequestBody WithdrawalCommand withdrawalCommand) {
        withdrawalService.withdraw(withdrawalCommand);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    ResponseEntity<List<WithdrawalReadModel>> withdrawals(@PathParam("cardId") String cardId) {
        return ResponseEntity.ok().body(finder.loadWithdrawalsFor(UUID.fromString(cardId)));
    }

}
