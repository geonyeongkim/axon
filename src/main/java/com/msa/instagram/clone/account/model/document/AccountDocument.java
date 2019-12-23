package com.msa.instagram.clone.account.model.document;

import com.msa.instagram.clone.account.event.AccountCreateEvent;
import com.msa.instagram.clone.common.enums.EventType;
import com.msa.instagram.clone.common.utils.JacksonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Document(value = "account")
public class AccountDocument {

    @Id
    private String id;
    private EventType eventType;
    private String value;
    private long timestamp;

    public AccountDocument(AccountCreateEvent event) {
        this.eventType = EventType.CREATE;
        this.value = JacksonUtil.writeValueAsString(event);
        this.timestamp = event.getTimestamp();
    }
}
