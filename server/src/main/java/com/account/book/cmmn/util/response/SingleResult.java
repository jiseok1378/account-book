package com.account.book.cmmn.util.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SingleResult<T> extends CommonResult{
    private T data;
}
