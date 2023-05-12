package com.account.book.user.controller;

import com.account.book.user.dao.UserDAO;
import com.account.book.user.service.UserService;
import com.account.book.user.vo.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public void addUser( HttpServletRequest request, @RequestBody UserDTO userDTO ){
        userService.addUser( request, userDTO );
    }
    @GetMapping("/all")
    public List<UserDTO> findAllUser(){
        return userService.findAllUser();
    }
    @GetMapping
    public UserDTO findUserByUserSn(String userId){
        return userService.findUserByUserId( userId ).orElseThrow(() -> new RuntimeException());
    }
}
