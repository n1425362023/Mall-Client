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
    public Category addCategory(CategoryDto categoryDto);

    public Boolean deleteCategory(Integer categoryId);

    public Category updateCategory(CategoryDto categoryDto);

    public List<Category> getAllCategory();

    public Category getCategoryById(Integer categoryId);
}
