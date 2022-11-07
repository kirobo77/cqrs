package io.dddbyexamples.cqrs.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawalCommand {
    private UUID card;
    private long amount;

}
