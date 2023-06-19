package com.account.book.user.controller;

import com.account.book.cmmn.util.response.*;
import com.account.book.user.service.UserService;
import com.account.book.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final ResponseService responseService;

    @GetMapping("/all")
    public ListResult<UserDTO> findAllUser(){
        return responseService.getListResult(userService.findAllUser());
    }
    @GetMapping
    public SingleResult<UserDTO> findUserByUserSn( @RequestParam String userId ){
        return responseService.getSingleResult(userService.findUserByUserId( userId ).orElseThrow(RuntimeException::new));
    }

    @DeleteMapping
    public CommonResult deleteUserByUserSn(@RequestParam Integer userSn ){
        userService.deleteUser( userSn );
        return responseService.getSuccessResult();
    }
}
