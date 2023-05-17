package com.account.book.menu.service;


import com.account.book.menu.dto.MenuDTO;

import java.util.List;
import java.util.Optional;

public interface MenuService {
    int addMenu( MenuDTO menuDTO );
    List<MenuDTO> selectByPairSn(Integer pairSn );
    Optional<MenuDTO> selectByMenuSn(Integer menuSn );
    int updateMenu( MenuDTO menuDTO );
    void deleteMenu( Integer menuSn );
}
