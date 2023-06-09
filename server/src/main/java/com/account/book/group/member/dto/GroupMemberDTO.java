package com.account.book.group.member.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GroupMemberDTO {
    private Integer memberSn;
    private Integer groupSn;
    private Integer userSn;
    private String groupRole;

}
