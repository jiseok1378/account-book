package com.account.book.category.service;

import com.account.book.category.dto.CategoryDTO;
import com.account.book.category.dto.MenuCategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> selectByPairSn( MenuCategoryDTO dto );
    //    List<CategoryDTO> selectByPairSnAndMenuSn( Integer pairSn, Integer menuSn );
    int addCategory( CategoryDTO categoryDTO );
    void deleteCategory( Integer categorySn );
    int updateCategory( CategoryDTO categoryDTO );
    int registeredMenu( MenuCategoryDTO menuCategoryDTO );
}
