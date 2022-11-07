package io.dddbyexamples.cqrs.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.dddbyexamples.cqrs.controller.Withdrawal;
import io.dddbyexamples.cqrs.repository.WithdrawalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WithdrawalService {

	private final WithdrawalRepository withdrawalRepository;

	public List<Withdrawal> withdraw(UUID cardId) {
		return withdrawalRepository.findByCardId(cardId);
	}

}
