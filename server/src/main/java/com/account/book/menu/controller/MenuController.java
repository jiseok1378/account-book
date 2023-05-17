package com.account.book.menu.controller;


import com.account.book.cmmn.util.response.CommonResult;
import com.account.book.cmmn.util.response.ResponseService;
import com.account.book.cmmn.util.response.SingleResult;
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
    private final ResponseService responseService;

    @PostMapping
    public SingleResult<Integer> addMenu(@RequestBody MenuDTO menuDTO ){

        return responseService.getSingleResult(menuService.addMenu( menuDTO ));
    }

    @PutMapping
    public SingleResult<Integer> updateMenu( MenuDTO menuDTO ){

        return responseService.getSingleResult(menuService.updateMenu( menuDTO ));
    }

    @DeleteMapping
    public CommonResult deleteMenu(@RequestParam Integer menuSn ){

        menuService.deleteMenu( menuSn );
        return responseService.getSuccessResult();
    }

    @GetMapping
    public SingleResult<MenuDTO> selectMenu(@RequestParam Integer menuSn ){
        return responseService.getSingleResult(menuService.selectByMenuSn( menuSn ).orElseThrow());
    }
}
