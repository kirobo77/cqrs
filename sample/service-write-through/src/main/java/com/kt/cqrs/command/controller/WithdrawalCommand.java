package com.kt.cqrs.command.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawalCommand {
    private String card;
    private long amount;

}
