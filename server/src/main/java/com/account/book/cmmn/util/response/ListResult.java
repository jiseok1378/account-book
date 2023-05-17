package com.account.book.cmmn.util.response;

import lombok.Data;

import java.util.List;

@Data
public class ListResult<T> extends CommonResult {
    private List<T> data;
}
