package io.dddbyexamples.cqrs.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.dddbyexamples.cqrs.model.DomainEvent;
import io.dddbyexamples.cqrs.model.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaDomainEventPublisher implements DomainEventPublisher {

    private final DomainEventsStorage domainEventStorage;
    private final ObjectMapper objectMapper;
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void publish(DomainEvent domainEvent) {
        try {
            domainEventStorage.save(new StoredDomainEvent(objectMapper.writeValueAsString(domainEvent), domainEvent.getType()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    @Scheduled(fixedRate = 2000)
    @Transactional
    public void publishExternally() {
        domainEventStorage
                .findAllBySentOrderByEventTimestampDesc(false)
                .forEach(event -> {
                            kafkaTemplate.send("example-kafka-test", event.getContent());
                            event.sent();
                        }

                );
    }
    
    
    
}
