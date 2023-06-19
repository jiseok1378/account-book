package com.account.book.controller.cmmn.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CommonException {

    @ResponseStatus(value = HttpStatus.CONFLICT)
    public static class AlreadyExistUser extends Exception{}

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public static class UserInfoMismatch extends Exception{}

}
