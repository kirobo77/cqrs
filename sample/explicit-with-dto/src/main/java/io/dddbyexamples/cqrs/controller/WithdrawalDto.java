package io.dddbyexamples.cqrs.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawalDto {
    private UUID cardId;
    private BigDecimal amount;

}
