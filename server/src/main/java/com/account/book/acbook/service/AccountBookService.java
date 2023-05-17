package com.account.book.acbook.service;

import com.account.book.acbook.dto.AccountBookDTO;

public interface AccountBookService {
    Integer updateAccountBook( AccountBookDTO accountBookDTO );
    Integer addAccountBook( AccountBookDTO accountBookDTO );
    void deleteAccountBook( Integer abSn );
}
