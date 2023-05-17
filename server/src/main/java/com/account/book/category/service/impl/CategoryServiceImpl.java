package com.account.book.category.service.impl;

import com.account.book.category.dao.CategoryDAO;
import com.account.book.category.dto.CategoryDTO;
import com.account.book.category.dto.MenuCategoryDTO;
import com.account.book.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDAO categoryDAO;

    @Override
    public List<CategoryDTO> selectByPairSn( MenuCategoryDTO dto ) {
        if( dto.getMenuSn() != null ){
            return categoryDAO.selectByMenuSnAndCategorySn( dto );
        }
        return categoryDAO.selectByPairSn( dto.getPairSn() );
    }

    @Override
    public int addCategory(CategoryDTO categoryDTO) {
        categoryDAO.addCategory( categoryDTO );
        return categoryDTO.getCategorySn();
    }

    @Override
    public void deleteCategory(Integer categorySn) {
        categoryDAO.deleteCategory( categorySn );
    }

    @Override
    public int updateCategory(CategoryDTO categoryDTO) {
        return categoryDAO.updateCategory( categoryDTO );
    }

    @Override
    public int registeredMenu(MenuCategoryDTO menuCategoryDTO) {
        categoryDAO.registeredMenu( menuCategoryDTO );
        return menuCategoryDTO.getMcrSn();
    }
}
