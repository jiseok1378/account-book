package com.account.book.group.member.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupRoleDTO{
    private String roleId;
    private String roleNm;
    private Integer groupSn;
}
