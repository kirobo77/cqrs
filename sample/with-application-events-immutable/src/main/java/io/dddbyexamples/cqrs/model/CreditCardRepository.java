package io.dddbyexamples.cqrs.model;

import java.util.UUID;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import io.dddbyexamples.cqrs.DomainEvent;
import io.dddbyexamples.cqrs.model.ports.CreditCardDao;
import io.dddbyexamples.cqrs.model.ports.CreditCardRecord;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
class CreditCardRepository {

    private final CreditCardDao dao;
    private final ApplicationEventPublisher publisher;

    @Transactional
    void apply(UUID cardId, Function<CreditCard, DomainEvent> function) {

        CreditCardRecord record = dao.load(cardId)
            .orElseThrow(() -> new IllegalStateException("Cannot find card with id " + cardId));
        DomainEvent event = function.apply(new CreditCard(record));
        record.apply(event);
        publisher.publishEvent(event);
        dao.save(record);
    }

}
