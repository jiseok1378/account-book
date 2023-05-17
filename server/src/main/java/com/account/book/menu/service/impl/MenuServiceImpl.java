package com.account.book.menu.service.impl;

import com.account.book.menu.dao.MenuDAO;
import com.account.book.menu.dto.MenuDTO;
import com.account.book.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuDAO menuDAO;

    @Override
    public int addMenu(MenuDTO menuDTO) {
        menuDAO.addMenu( menuDTO );
        return menuDTO.getMenuSn();
    }

    @Override
    public List<MenuDTO> selectByPairSn(Integer pairSn) {
        return menuDAO.selectByPairSn( pairSn );
    }

    @Override
    public Optional<MenuDTO> selectByMenuSn(Integer menuSn) {
        return menuDAO.selectByMenuSn( menuSn );
    }

    @Override
    public int updateMenu(MenuDTO menuDTO) {
        return menuDAO.updateMenu( menuDTO );
    }

    @Override
    public void deleteMenu(Integer menuSn) {
        menuDAO.deleteMenu( menuSn );
    }
}
