package com.kt.cqrs.adapter.out.persistence;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import com.kt.cqrs.domain.ports.CreditCardDao;
import com.kt.cqrs.domain.ports.CreditCardRecord;

import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class CreditCardDaoJdbc implements CreditCardDao {

    private final CreditCardDaoDataJdbc dataJpaDao;

    @Override
    public Optional<CreditCardRecord> load(UUID cardId) {
        return dataJpaDao.findById(cardId);
    }

    @Override
    public void save(CreditCardRecord record) {
        dataJpaDao.save(record);
    }
}
