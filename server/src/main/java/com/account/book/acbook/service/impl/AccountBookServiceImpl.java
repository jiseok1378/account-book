package com.account.book.acbook.service.impl;

import com.account.book.acbook.dao.AccountBookDAO;
import com.account.book.acbook.dto.AccountBookDTO;
import com.account.book.acbook.service.AccountBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountBookServiceImpl implements AccountBookService {
    private final AccountBookDAO accountBookDAO;

    @Override
    public Integer updateAccountBook(AccountBookDTO accountBookDTO) {
        return accountBookDAO.updateAccountBook( accountBookDTO );
    }

    @Override
    public Integer addAccountBook(AccountBookDTO accountBookDTO) {
        accountBookDAO.addAccountBook( accountBookDTO );
        return accountBookDTO.getAbSn();
    }

    @Override
    public void deleteAccountBook(Integer abSn) {
        accountBookDAO.deleteAccountBook(abSn);
    }
}
