package com.account.book.group.message.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupMessageDTO {
    private Integer messageSn;
    private String messageCn;
    private Integer groupSn;
    private Integer fromUserSn;
    private Integer toUserSn;
    private Integer messageStatus;
    private Integer memberCnt;
    private String searchGroupNm;
}
