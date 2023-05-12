package com.account.book.user.dao;

import com.account.book.user.vo.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserDAO {
    void addUser( UserDTO userDTO );
    void deleteUser( Integer userSn );
    List<UserDTO> findAllUser();
    Optional<UserDTO> findUserByUserSn(Integer userSn );
    Optional<UserDTO> findUserByUserId( String userId );
}