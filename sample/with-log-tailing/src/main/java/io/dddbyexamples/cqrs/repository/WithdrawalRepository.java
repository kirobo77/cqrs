package io.dddbyexamples.cqrs.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import io.dddbyexamples.cqrs.model.Withdrawal;

public interface WithdrawalRepository extends CrudRepository<Withdrawal, String> {

    List<Withdrawal> findByCardId(String cardId);
}
