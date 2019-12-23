package com.msa.instagram.clone.account.model.document;

import com.msa.instagram.clone.account.event.create.AccountCreateEvent;
import com.msa.instagram.clone.account.event.AccountEvent;
import com.msa.instagram.clone.common.enums.EventType;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Getter @ToString
@NoArgsConstructor
@Document(value = "account")
public class AccountDocument {

    @Id
    private String id;
    private EventType eventType;
    private AccountEvent event;
    private long timestamp;

    public AccountDocument(AccountCreateEvent event) {
        this.eventType = EventType.CREATE;
        this.event = event;
        this.timestamp = event.getTimestamp();
    }
}
