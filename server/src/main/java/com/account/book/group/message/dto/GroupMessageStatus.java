package com.account.book.group.message.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum GroupMessageStatus {
    PENDING(0,"대기"),
    ACCEPT(1, "수락"),
    DENIED(2, "거절"),
    UNKNOWN(-1, "UNKNOWN");
    private final Integer code;
    private final String codeNm;
    public static GroupMessageStatus getStatus(Integer code){
        switch (code){
            case 0: return PENDING;
            case 1: return ACCEPT;
            case 2: return DENIED;
            default: return UNKNOWN;
        }
    }
}
