package com.kt.cqrs.command.repository.entity;

public class NotEnoughMoneyException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotEnoughMoneyException(String cardNo, long wanted, long availableBalance) {
        super(String.format("Card %s not able to withdraw %s. Balance is %s", cardNo, wanted, availableBalance));
    }


}
