package com.account.book.menu.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MenuDTO {
    private Integer menuSn;
    private String menuNm;
    private Integer upperMenuSn;
    private Integer groupSn;
}
