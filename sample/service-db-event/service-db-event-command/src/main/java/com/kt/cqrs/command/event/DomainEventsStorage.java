package com.kt.cqrs.command.event;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

interface DomainEventsStorage extends CrudRepository<StoredDomainEvent, String> {
    List<StoredDomainEvent> findAllBySentOrderByEventTimestampDesc(boolean sent);
}