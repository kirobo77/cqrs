package io.dddbyexamples.cqrs.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import io.dddbyexamples.cqrs.controller.Withdrawal;

public interface WithdrawalRepository extends CrudRepository<Withdrawal, UUID> {

	 List<Withdrawal> findByCardId(UUID cardId);
}
