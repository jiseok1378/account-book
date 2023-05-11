package com.account.book.test.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
public class TestVO implements Serializable {
    private final String t1;
    private final String t2;
}
