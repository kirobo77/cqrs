package com.kt.cqrs.domain.ports;

import java.util.Optional;
import java.util.UUID;

public interface CreditCardDao {

    Optional<CreditCardRecord> load(UUID cardId);

    void save(CreditCardRecord record);
}
