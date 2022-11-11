package com.kt.cqrs.domain.port;

import com.kt.cqrs.adapter.in.event.DomainEvent;

public interface EventPublisher {
	void publishEvent(DomainEvent event);
}
