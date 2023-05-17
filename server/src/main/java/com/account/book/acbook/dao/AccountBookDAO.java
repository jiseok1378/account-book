package com.account.book.acbook.dao;

import com.account.book.acbook.dto.AccountBookDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountBookDAO {
    int updateAccountBook( AccountBookDTO accountBookDTO );
    int addAccountBook( AccountBookDTO accountBookDTO );
    void deleteAccountBook( Integer abSn );
}
