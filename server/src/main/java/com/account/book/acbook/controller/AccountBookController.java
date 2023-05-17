package com.account.book.acbook.controller;

import com.account.book.acbook.dao.AccountBookDAO;
import com.account.book.acbook.dto.AccountBookDTO;
import com.account.book.acbook.service.AccountBookService;
import com.account.book.cmmn.util.response.CommonResponse;
import com.account.book.cmmn.util.response.CommonResult;
import com.account.book.cmmn.util.response.ResponseService;
import com.account.book.cmmn.util.response.SingleResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.net.Inet4Address;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account/book")
public class AccountBookController {

    private final AccountBookService accountBookService;
    private final ResponseService responseService;

    @PutMapping
    public SingleResult<Integer> updateAccountBook(AccountBookDTO accountBookDTO ){
        return responseService.getSingleResult(accountBookService.updateAccountBook( accountBookDTO ));
    }

    @PostMapping
    public SingleResult<Integer> addAccountBook( @RequestBody AccountBookDTO accountBookDTO ){
        return responseService.getSingleResult(accountBookService.addAccountBook( accountBookDTO ));
    }

    @DeleteMapping
    public CommonResult deleteAccountBook(@RequestParam Integer abSn ){
        accountBookService.deleteAccountBook( abSn );
        return responseService.getSuccessResult();
    }
}
