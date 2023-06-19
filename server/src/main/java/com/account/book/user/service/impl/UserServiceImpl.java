package com.account.book.user.service.impl;

import com.account.book.cmmn.util.cmm.CommonUtil;
import com.account.book.controller.cmmn.exception.CommonException;
import com.account.book.user.dao.UserDAO;
import com.account.book.user.service.UserService;
import com.account.book.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Integer addUser(HttpServletRequest request, UserDTO userDTO) throws CommonException.AlreadyExistUser {

        if( userDAO.findUserByUserId( userDTO.getUserId() ).isPresent() ){
            throw new CommonException.AlreadyExistUser();
        }

        userDTO.setFirstCreateIp( CommonUtil.getClientIP(request) );

        /* Password Encrypt : S */
        String userOrigPw = userDTO.getUserPw();

        userDTO.setUserPw( passwordEncoder.encode(userOrigPw) );
        /* Password Encrypt : E */

        userDTO.setFirstCreateDate(new Date());
        userDTO.setLastLoginDate(null);
        userDTO.setLastLoginIp(null);
        userDAO.addUser( userDTO );
        return userDTO.getUserSn();
    }

    @Override
    public Optional<UserDTO> findUserByUserIdAndPassword( UserDTO userDTO ) throws CommonException.UserInfoMismatch {
        UserDTO user = userDAO.findUserByUserId( userDTO.getUserId() ).orElseThrow(CommonException.UserInfoMismatch::new);
        if( passwordEncoder.matches( userDTO.getUserPw(), user.getUserPw() ) ){
            return Optional.of( user );
        }
        else{
            return Optional.empty();
        }

    }

    @Override
    public void deleteUser( Integer userSn ) {
        userDAO.deleteUser( userSn );
    }

    @Override
    public List<UserDTO> findAllUser() {
        return userDAO.findAllUser();
    }

    @Override
    public Optional<UserDTO> findUserByUserSn(Integer userSn ) {
        return userDAO.findUserByUserSn( userSn );
    }

    @Override
    public Optional<UserDTO> findUserByUserId( String userId ) {
        return userDAO.findUserByUserId( userId );
    }
}
