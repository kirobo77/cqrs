package com.kt.cqrs.domain;

import java.util.UUID;
import java.util.function.Function;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kt.cqrs.domain.ports.CreditCardDao;
import com.kt.cqrs.domain.ports.CreditCardRecord;
import com.kt.cqrs.event.DomainEvent;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
        log.info("publishEvent = {}", event);
        
        dao.save(record);
    }

}
