package com.kt.cqrs.adapter.out.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.kt.cqrs.adapter.in.event.DomainEvent;
import com.kt.cqrs.domain.port.EventPublisher;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EventPublisherAdapter implements EventPublisher {

	private final ApplicationEventPublisher applicationEventPublisher;
	
	@Override
	public void publishEvent(DomainEvent event) {
		applicationEventPublisher.publishEvent(event);
		
	}

}
