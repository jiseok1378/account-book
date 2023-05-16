package com.account.book.pair.dto;

import lombok.Data;

@Data
public class PairDTO {
    private Integer pairSn;
    private Integer userSnTo;
    private Integer userSnFrom;
    private String pairMsg;
    private AcceptStatus accept;
}
