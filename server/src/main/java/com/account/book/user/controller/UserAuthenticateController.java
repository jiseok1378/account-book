package com.account.book.user.controller;

import com.account.book.cmmn.util.response.ResponseService;
import com.account.book.cmmn.util.response.SingleResult;
import com.account.book.controller.cmmn.exception.CommonException;
import com.account.book.user.dto.UserDTO;
import com.account.book.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authenticate/sign")
public class UserAuthenticateController {
    private final UserService userService;
    private final ResponseService responseService;

    @PostMapping("/up")
    public SingleResult<Integer> signUp(HttpServletRequest request, @RequestBody UserDTO userDTO ) throws CommonException.AlreadyExistUser {
        return responseService.getSingleResult(userService.addUser( request, userDTO ));
    }

    @PostMapping("/in")
    public SingleResult<UserDTO> signIn(HttpServletRequest request, @RequestBody UserDTO userDTO ) throws CommonException.UserInfoMismatch {
        UserDTO user = userService.findUserByUserIdAndPassword( userDTO ).orElseThrow(CommonException.UserInfoMismatch::new);
        return responseService.getSingleResult( user );
    }
}
