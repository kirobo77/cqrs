package io.dddbyexamples.cqrs.model;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import io.dddbyexamples.cqrs.controller.WithdrawalCommand;

@Service
@AllArgsConstructor
public class WithdrawalProcess {

    private final CreditCardRepository repository;

    public void withdraw(WithdrawalCommand command) {
        repository.apply(command.getCard(),creditCard -> creditCard.withdraw(command));
    }
}
