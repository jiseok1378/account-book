package com.account.book.cmmn.util.response;

import lombok.Data;

@Data
public class CommonResult {

    private boolean success;
    private int code;
    private String msg;
}
