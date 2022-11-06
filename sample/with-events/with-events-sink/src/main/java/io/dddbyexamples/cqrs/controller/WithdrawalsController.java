package io.dddbyexamples.cqrs.controller;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.dddbyexamples.cqrs.repository.WithdrawalsRepository;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/withdrawals")
class WithdrawalsController {

    private final WithdrawalsRepository withdrawalsRepository;

    WithdrawalsController(WithdrawalsRepository withdrawalsRepository) {
        this.withdrawalsRepository = withdrawalsRepository;
    }

    @GetMapping
    Flux<WithdrawalDto> withdrawals(@PathParam("cardId") String cardId) {
        return withdrawalsRepository.findAllByCard(cardId);
    }


}

