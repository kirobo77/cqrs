package com.kt.cqrs.query.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kt.cqrs.query.repository.entity.Withdrawal;

public interface WithdrawalRepository extends CrudRepository<Withdrawal, String> {

    List<Withdrawal> findByCardId(String cardId);
}
