package com.account.book.group.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GroupDTO {
    private Integer groupSn;
    private String groupNm;
    private Integer ownerUserSn;
    private Integer groupStatus;
    private String groupIntro;
}
