package com.account.book.category.dao;

import com.account.book.category.dto.CategoryDTO;
import com.account.book.category.dto.MenuCategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryDAO {
    List<CategoryDTO> selectByPairSn( Integer pairSn );
//    List<CategoryDTO> selectByPairSnAndMenuSn( Integer pairSn, Integer menuSn );
    int addCategory( CategoryDTO categoryDTO );
    void deleteCategory( Integer categorySn );
    int updateCategory( CategoryDTO categoryDTO );
    int registeredMenu( MenuCategoryDTO menuCategoryDTO );
    List<CategoryDTO> selectByMenuSnAndCategorySn( MenuCategoryDTO menuCategoryDTO );
}
