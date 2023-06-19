package com.account.book.controller.cmmn.advice;

import com.account.book.cmmn.util.response.CommonResult;
import com.account.book.cmmn.util.response.ResponseService;
import com.account.book.controller.cmmn.exception.CommonException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class CommonAdvice {
    private final ResponseService responseService;

    @ExceptionHandler(CommonException.AlreadyExistUser.class)
    public CommonResult alreadyExist(){
        return responseService.getFailResult();
    }

}
