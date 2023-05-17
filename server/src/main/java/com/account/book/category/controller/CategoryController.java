package com.account.book.category.controller;

import com.account.book.category.dto.CategoryDTO;
import com.account.book.category.dto.MenuCategoryDTO;
import com.account.book.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public Integer addCategory( @RequestBody CategoryDTO categoryDTO ){
        return categoryService.addCategory( categoryDTO );
    }

    @GetMapping
    public List<CategoryDTO> selectCategory( MenuCategoryDTO dto ){
        return categoryService.selectByPairSn( dto );
    }

    @PutMapping
    public int updateCategory( CategoryDTO categoryDTO ){
        return categoryService.updateCategory( categoryDTO );
    }

    @DeleteMapping
    public void deleteCategory( @RequestParam Integer categorySn ){
        categoryService.deleteCategory( categorySn );
    }

    @PostMapping("/menu")
    public Integer registeredMenu( @RequestBody MenuCategoryDTO menuCategoryDTO ){
        return categoryService.registeredMenu( menuCategoryDTO );
    }

}
