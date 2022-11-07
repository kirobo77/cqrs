package io.dddbyexamples.cqrs.model;

import org.springframework.stereotype.Service;

import io.dddbyexamples.cqrs.controller.WithdrawalCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class WithdrawalService {

    private final CreditCardRepository repository;

    public void withdraw(WithdrawalCommand command) {
    	log.info("withdraw = {}", command);
        repository.apply(command.getCard(),creditCard -> creditCard.withdraw(command));
    }
}
