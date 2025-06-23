package org.ysu.mall.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ysu.mall.domain.dto.CategoryDto;
import org.ysu.mall.domain.entity.Category;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.exception.BusinessException;
import org.ysu.mall.mapper.CategoryMapper;
import org.ysu.mall.service.CategoryService;

import java.util.List;

/**
* @author DELL
* @description 针对表【category】的数据库操作Service实现
* @createDate 2025-06-17 10:22:27
*/
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    private CategoryMapper categoryMapper;

    public Category addCategory(CategoryDto categoryDto){
        try{
            Category category = new Category()
                    .setName(categoryDto.getName())
                    .setParentId(categoryDto.getParentId())
                    .setSortOrder(categoryDto.getSortOrder());
            if(!save(category)){
                throw new BusinessException(ResultEnum.CATEGORY_ADD_ERROR,"添加分类失败");
            }
            return category;
        }catch (BusinessException e){
           throw e;
        }catch (Exception e){
            throw new BusinessException(ResultEnum.SYSTEM_ERROR,"添加分类失败");
        }
    }

    public Boolean deleteCategory(Integer categoryId){
        try{
            if(!removeById(categoryId)){
                throw new BusinessException(ResultEnum.CATEGORY_DELETE_ERROR,"删除分类失败");
            }
            return true;
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            throw new BusinessException(ResultEnum.SYSTEM_ERROR,"删除分类失败");
        }
    }

    public Category updateCategory(CategoryDto categoryDto){
        try{
            Category category = lambdaQuery().eq(Category::getCategoryId,categoryDto.getCategoryId()).one();
            if(category == null) {
                throw new BusinessException(ResultEnum.CATEGORY_NOT_FOUND, "分类不存在");
            }
            if (!updateById(category.setName(categoryDto.getName())
                    .setParentId(categoryDto.getParentId())
                    .setSortOrder(categoryDto.getSortOrder()))) {
                throw new BusinessException(ResultEnum.CATEGORY_UPDATE_ERROR, "更新分类失败");
            }
            return category;
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            throw new BusinessException(ResultEnum.SYSTEM_ERROR,"更新分类失败");
        }
    }

    public List<Category> getAllCategory(){
        try{
            return categoryMapper.selectList(Wrappers.<Category>lambdaQuery().orderByAsc(Category::getSortOrder));
        }catch (Exception e){
            throw new BusinessException(ResultEnum.SYSTEM_ERROR,"获取分类失败");
        }
    }

    public Category getCategoryById(Integer categoryId){
        try{
            return categoryMapper.selectById(categoryId);
        }catch (Exception e){
            throw new BusinessException(ResultEnum.SYSTEM_ERROR,"获取分类失败");
        }
    }

    public List<Category> listAllCategories() {
        try {
            return categoryMapper.selectList(Wrappers.<Category>lambdaQuery().orderByAsc(Category::getSortOrder));
        } catch (Exception e) {
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "获取所有分类失败");
        }
    }
}




