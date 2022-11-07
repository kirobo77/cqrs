package io.dddbyexamples.cqrs.model.ports;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import io.dddbyexamples.cqrs.event.DomainEvent;
import io.dddbyexamples.cqrs.model.CardWithdrawn;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "CREDIT_CARD")
public class CreditCardRecord {

    @Id
    private UUID id;
    private long initialLimit;
    private long usedLimit;


    public void apply(DomainEvent event) {
        if (event instanceof CardWithdrawn) {
            this.usedLimit = usedLimit + ((CardWithdrawn) event).getAmount();
        } else {
            throw new IllegalStateException("Event: " + event.getClass().getSimpleName() + "not handled");
        }
    }
}
