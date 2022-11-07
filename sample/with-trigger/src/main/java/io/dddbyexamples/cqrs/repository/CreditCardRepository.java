package io.dddbyexamples.cqrs.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import io.dddbyexamples.cqrs.model.CreditCard;

public interface CreditCardRepository extends CrudRepository<CreditCard, UUID> {

}
