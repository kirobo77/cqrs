package io.dddbyexamples.cqrs.persistence;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import io.dddbyexamples.cqrs.model.Withdrawal;

public interface WithdrawalRepository extends CrudRepository<Withdrawal, UUID> {

    List<Withdrawal> findByCardId(UUID cardId);
}
