package io.dddbyexamples.cqrs.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
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
@Table("withdrawal")
public class Withdrawal implements Persistable<String>{

    @Transient
    private boolean isNew = false;
    
    @Id
    private String id;
    private long amount;
    private String cardId;
    
    public static Withdrawal newWithdrawal(String id, long amount, String cardId) {
    	Withdrawal withdrawal = new Withdrawal(true, id, amount, cardId);
        return withdrawal;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

}
