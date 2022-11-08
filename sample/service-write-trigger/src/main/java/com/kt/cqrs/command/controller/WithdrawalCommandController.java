package com.kt.cqrs.command.controller;

import java.util.List;
import java.util.UUID;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.cqrs.command.service.WithdrawalCommandService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/withdrawal")
@RequiredArgsConstructor
class WithdrawalCommandController {

    private final WithdrawalCommandService withdrawalCommandService;

    @PostMapping
    ResponseEntity<?> withdraw(@RequestBody WithdrawalCommand withdrawalCommand) {
    	withdrawalCommandService.withdraw(withdrawalCommand.getCard(), withdrawalCommand.getAmount());
        return ResponseEntity.ok().build();
    }

}

