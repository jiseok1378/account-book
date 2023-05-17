package com.account.book.acbook.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountBookDTO {
    private Integer abSn;
    private Integer categorySn;
    private Integer price;
    private String place;
    private String otherData;
}
