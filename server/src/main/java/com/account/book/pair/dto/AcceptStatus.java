package com.account.book.pair.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AcceptStatus {
    PENDING(0, "대기"), // 대기
    ALLOWED(1, "수락"), // 수락
    DENIED(2, "거부") // 거부
    ;
    private final Integer code;
    private final String codeName;
    public static AcceptStatus getStatus(Integer code){
        switch (code){
            case 1 : return ALLOWED;
            case 2 : return DENIED;
            default: return PENDING;
        }
    }
}
