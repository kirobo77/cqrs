package io.dddbyexamples.cqrs.controller;

import java.util.List;
import java.util.UUID;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.dddbyexamples.cqrs.service.WithdrawalService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/withdrawals")
@RequiredArgsConstructor
class WithdrawalsController {

	private final WithdrawalService withdrawalService;

    @GetMapping
    ResponseEntity<List<Withdrawal>> withdrawals(@PathParam("cardId") String cardId) {
    	 return ResponseEntity.ok().body(withdrawalService.withdraw(UUID.fromString(cardId)));
    }


}

