package com.account.book.category.controller;

import com.account.book.category.dto.CategoryDTO;
import com.account.book.category.dto.MenuCategoryDTO;
import com.account.book.category.service.CategoryService;
import com.account.book.cmmn.util.response.CommonResult;
import com.account.book.cmmn.util.response.ListResult;
import com.account.book.cmmn.util.response.ResponseService;
import com.account.book.cmmn.util.response.SingleResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final ResponseService responseService;

    @PostMapping
    public SingleResult<Integer> addCategory(@RequestBody CategoryDTO categoryDTO ){
        return responseService.getSingleResult(categoryService.addCategory( categoryDTO ));
    }

    @GetMapping
    public ListResult<CategoryDTO> selectCategory(MenuCategoryDTO dto ){
        return responseService.getListResult(categoryService.selectByGroupSn( dto ));
    }

    @PutMapping
    public SingleResult<Integer> updateCategory( CategoryDTO categoryDTO ){
        return responseService.getSingleResult(categoryService.updateCategory( categoryDTO ));
    }

    @DeleteMapping
    public CommonResult deleteCategory(@RequestParam Integer categorySn ){
        categoryService.deleteCategory( categorySn );
        return responseService.getSuccessResult();
    }

    @PostMapping("/menu")
    public SingleResult<Integer> registeredMenu( @RequestBody MenuCategoryDTO menuCategoryDTO ){
        return responseService.getSingleResult(categoryService.registeredMenu( menuCategoryDTO ));
    }

}
