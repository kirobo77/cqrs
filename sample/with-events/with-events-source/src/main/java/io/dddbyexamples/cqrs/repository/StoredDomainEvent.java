package io.dddbyexamples.cqrs.repository;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("STORED_DOMAIN_EVENT")
class StoredDomainEvent {

    @Id String id;
    private String content;
    private boolean sent;
    private Instant eventTimestamp;
    private String eventType;

    StoredDomainEvent(String content, String eventType) {
        this.content = content;
        this.id = UUID.randomUUID().toString();
        this.eventType = eventType;
    }

    void sent() {
        sent = true;
    }

    String getContent() {
        return content;
    }

    public String getEventType() {
        return eventType;
    }
}