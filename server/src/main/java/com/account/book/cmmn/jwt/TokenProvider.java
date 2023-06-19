package com.account.book.cmmn.jwt;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider implements InitializingBean {

    private final String secret;
    private final long token

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
