package io.dddbyexamples.cqrs.repository;

import io.dddbyexamples.cqrs.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CreditCardRepository extends JpaRepository<CreditCard, UUID> {

}
