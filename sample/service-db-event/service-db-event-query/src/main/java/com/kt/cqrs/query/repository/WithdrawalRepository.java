package com.kt.cqrs.query.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.kt.cqrs.query.controller.Withdrawal;

public interface WithdrawalRepository extends CrudRepository<Withdrawal, UUID> {

	 List<Withdrawal> findByCardId(UUID cardId);
}
