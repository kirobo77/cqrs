package io.dddbyexamples.cqrs.model;

import org.springframework.stereotype.Service;

import io.dddbyexamples.cqrs.controller.WithdrawalCommand;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WithdrawalService {

    private final CreditCardRepository repository;

    public void withdraw(WithdrawalCommand command) {
        repository.apply(command.getCard(),creditCard -> creditCard.withdraw(command));
    }
}
