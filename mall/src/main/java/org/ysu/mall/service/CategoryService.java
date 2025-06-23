package org.ysu.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ysu.mall.domain.dto.CategoryDto;
import org.ysu.mall.domain.entity.Category;

import java.util.List;

/**
* @author DELL
* @description 针对表【category】的数据库操作Service
* @createDate 2025-06-17 10:22:27
*/
public interface CategoryService extends IService<Category> {
    Category addCategory(CategoryDto categoryDto);

    Boolean deleteCategory(Integer categoryId);

    Category updateCategory(CategoryDto categoryDto);

    List<Category> getAllCategory();

    Category getCategoryById(Integer categoryId);

    List<Category> listAllCategories();
}
