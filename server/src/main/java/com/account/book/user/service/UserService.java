package com.account.book.user.service;

import com.account.book.controller.cmmn.exception.CommonException;
import com.account.book.user.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Integer addUser(HttpServletRequest request, UserDTO userDTO ) throws CommonException.AlreadyExistUser;
    Optional<UserDTO> findUserByUserIdAndPassword(UserDTO userDTO) throws CommonException.UserInfoMismatch;
    void deleteUser( Integer userSn );
    List<UserDTO> findAllUser();
    Optional<UserDTO> findUserByUserSn( Integer userSn );
    Optional<UserDTO> findUserByUserId(String userId );

}
