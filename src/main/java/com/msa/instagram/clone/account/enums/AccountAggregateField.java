package com.msa.instagram.clone.account.enums;

import com.msa.instagram.clone.account.aggregate.AccountAggregate;
import com.msa.instagram.clone.account.event.AccountUpdateEvent;
import com.msa.instagram.clone.common.support.AggregateField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;

import java.util.function.Consumer;
import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public enum AccountAggregateField implements AggregateField<AccountAggregate, AccountUpdateEvent> {

    USER_NAME(accountUpdateEvent -> accountUpdateEvent.getUserName(), pair -> pair.getFirst().setUserName(String.valueOf(pair.getSecond()))),
    PASSWORD(accountUpdateEvent -> accountUpdateEvent.getPassword(), pair -> pair.getFirst().setPassword(String.valueOf(pair.getSecond()))),
    NICK_NAME(accountUpdateEvent -> accountUpdateEvent.getNickname(), pair -> pair.getFirst().setNickname(String.valueOf(pair.getSecond()))),
    WEB_SITE(accountUpdateEvent -> accountUpdateEvent.getWebsite(), pair -> pair.getFirst().setWebsite(String.valueOf(pair.getSecond()))),
    INTRO(accountUpdateEvent -> accountUpdateEvent.getIntro(), pair -> pair.getFirst().setIntro(String.valueOf(pair.getSecond()))),
    EMAIL(accountUpdateEvent -> accountUpdateEvent.getEmail(), pair -> pair.getFirst().setEmail(String.valueOf(pair.getSecond()))),
    TELEPHONE(accountUpdateEvent -> accountUpdateEvent.getTelephone(), pair -> pair.getFirst().setTelephone(String.valueOf(pair.getSecond()))),
    GENDER(accountUpdateEvent -> accountUpdateEvent.getGender(), pair -> pair.getFirst().setGender(Gender.valueOf(String.valueOf(pair.getSecond())))),
    PROFILE_URL(accountUpdateEvent -> accountUpdateEvent.getProfileUrl(), pair -> pair.getFirst().setProfileUrl(String.valueOf(pair.getSecond())))
    ;

    private final Function<AccountUpdateEvent, Object> getExpress;
    private final Consumer<Pair<AccountAggregate, Object>> setExpress;

    @Override
    public void updateAggregate(AccountAggregate aggregate, AccountUpdateEvent event) {
        setExpress.accept(Pair.of(aggregate, getExpress.apply(event)));
    }
}
