package com.kt.cqrs.domain.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kt.cqrs.domain.port.Withdrawal;
import com.kt.cqrs.domain.port.WithdrawalDao;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WithdrawalQueryService {

	 private final WithdrawalDao withdrawalDao;
	 
	public List<Withdrawal> withdrawal(UUID cardId) {
		return withdrawalDao.list(cardId);
	}
}
