package com.account.book.acbook.controller;

import com.account.book.acbook.dao.AccountBookDAO;
import com.account.book.acbook.dto.AccountBookDTO;
import com.account.book.acbook.service.AccountBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account/book")
public class AccountBookController {

    private final AccountBookService accountBookService;

    @PutMapping
    int updateAccountBook( AccountBookDTO accountBookDTO ){
        return accountBookService.updateAccountBook( accountBookDTO );
    }

    @PostMapping
    int addAccountBook( @RequestBody AccountBookDTO accountBookDTO ){
        return accountBookService.addAccountBook( accountBookDTO );
    }

    @DeleteMapping
    void deleteAccountBook( @RequestParam Integer abSn ){
        accountBookService.deleteAccountBook( abSn );
    }
}
