package com.account.book.user.service.impl;

import com.account.book.cmmn.util.cmm.CommonUtil;
import com.account.book.user.dao.UserDAO;
import com.account.book.user.service.UserService;
import com.account.book.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Override
    public Integer addUser(HttpServletRequest request, UserDTO userDTO) {
        userDTO.setFirstCreateIp( CommonUtil.getClientIP(request) );
        userDTO.setFirstCreateDate(new Date());
        userDTO.setLastLoginDate(null);
        userDTO.setLastLoginIp(null);
        userDAO.addUser( userDTO );
        return userDTO.getUserSn();
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
