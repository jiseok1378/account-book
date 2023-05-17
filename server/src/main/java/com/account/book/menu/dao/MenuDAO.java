package com.account.book.menu.dao;

import com.account.book.menu.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MenuDAO {
    int addMenu( MenuDTO menuDTO );
    List<MenuDTO> selectByPairSn( Integer pairSn );
    Optional<MenuDTO> selectByMenuSn( Integer menuSn );
    int updateMenu( MenuDTO menuDTO );
    void deleteMenu( Integer menuSn );
}
