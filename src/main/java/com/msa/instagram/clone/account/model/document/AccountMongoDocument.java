package com.msa.instagram.clone.account.model.document;

import com.msa.instagram.clone.account.enums.AccountAggregateField;
import com.msa.instagram.clone.account.event.AccountEvent;
import com.msa.instagram.clone.account.event.create.AccountCreateEvent;
import com.msa.instagram.clone.account.event.update.AccountUpdateEvent;
import com.msa.instagram.clone.common.enums.EventType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Getter @ToString
@NoArgsConstructor
@Document(value = "account")
public class AccountMongoDocument {

    @Id
    private String id;
    private EventType eventType;
    private AccountEvent event;
    private List<AccountAggregateField> aggregateFieldList;
    private long timestamp;

    public AccountMongoDocument(AccountCreateEvent event) {
        this.eventType = EventType.CREATE;
        this.event = event;
        this.timestamp = event.getTimestamp();
    }

    public AccountMongoDocument(AccountUpdateEvent event) {
        this.eventType = EventType.UPDATE;
        this.event = event;
        this.aggregateFieldList = event.getAccountAggregateFields();
        this.timestamp = event.getTimestamp();
    }
}
