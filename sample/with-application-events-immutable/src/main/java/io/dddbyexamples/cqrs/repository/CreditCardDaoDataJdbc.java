package io.dddbyexamples.cqrs.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import io.dddbyexamples.cqrs.model.ports.CreditCardRecord;

public interface CreditCardDaoDataJdbc extends CrudRepository<CreditCardRecord, UUID> {

}
