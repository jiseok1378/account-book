package com.account.book.menu.controller;


import com.account.book.menu.dto.MenuDTO;
import com.account.book.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public Integer addMenu( @RequestBody MenuDTO menuDTO ){
        return menuService.addMenu( menuDTO );
    }

    @PutMapping
    public Integer updateMenu( MenuDTO menuDTO ){
        return menuService.updateMenu( menuDTO );
    }

    @DeleteMapping
    public void deleteMenu( @RequestParam Integer menuSn ){
        menuService.deleteMenu( menuSn );
    }

    @GetMapping
    public MenuDTO selectMenu(@RequestParam Integer menuSn ){
        return menuService.selectByMenuSn( menuSn ).orElseThrow();
    }
}
