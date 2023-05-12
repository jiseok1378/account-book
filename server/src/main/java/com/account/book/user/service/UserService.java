package com.account.book.user.service;

import com.account.book.user.vo.UserDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface UserService {
    void addUser(HttpServletRequest request, UserDTO userDTO );
    void deleteUser( Integer userSn );
    List<UserDTO> findAllUser();
    Optional<UserDTO> findUserByUserSn( Integer userSn );
    Optional<UserDTO> findUserByUserId(String userId );

}
