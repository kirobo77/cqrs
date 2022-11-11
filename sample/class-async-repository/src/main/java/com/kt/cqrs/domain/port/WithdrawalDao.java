package com.kt.cqrs.domain.port;

import java.util.List;
import java.util.UUID;

public interface WithdrawalDao {

    List<Withdrawal> list(UUID cardId);

}
