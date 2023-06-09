package com.account.book.group.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum GlobalGroupRole{
    CREATOR("ROLE_000", "개설자"),
    ADMIN("ROLE_100", "관리자"),
    COMMON("ROLE_200", "일반 사용자");
    private final String roleId;
    private final String roleNm;

    public static GroupRoleDTO globalRoleToDto(GlobalGroupRole role){
        GroupRoleDTO dto = new GroupRoleDTO();
        dto.setRoleId(role.getRoleId());
        dto.setRoleNm(role.getRoleNm());
        return dto;
    }
}
