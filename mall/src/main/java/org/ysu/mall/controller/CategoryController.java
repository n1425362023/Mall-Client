package org.ysu.mall.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.dto.CategoryDto;
import org.ysu.mall.domain.entity.Category;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.exception.BusinessException;
import org.ysu.mall.service.CategoryService;

import java.util.List;

/**
 * 分类管理控制器
 */
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    /**
     * 添加新分类
     * @param categoryDto
     * @return
     */
    @PostMapping("/add")
    public ApiResponse<?> addCategory(@Valid @RequestBody CategoryDto categoryDto) {
        try{
            return ApiResponse.success(categoryService.addCategory(categoryDto));
        }catch (BusinessException e){
            return ApiResponse.error(e.getCode());
        }
    }

    /**
     * 删除分类
     * @param categoryId
     * @return
     */
    @DeleteMapping("/delete/{categoryId}")
    public ApiResponse<?> deleteCategory(@PathVariable Integer categoryId) {
        try{
            if(categoryService.deleteCategory(categoryId)){
                return ApiResponse.success(ResultEnum.SUCCESS);
            }else{
                return ApiResponse.error(ResultEnum.CATEGORY_NOT_FOUND);
            }
        }catch (BusinessException e){
            return ApiResponse.error(e.getCode());
        }
    }

    /**
     * 更新分类信息
     * @param categoryDto
     * @return
     */
    @PutMapping("/update")
    public ApiResponse<?> updateCategory(@Valid @RequestBody CategoryDto categoryDto) {
        try{
            return ApiResponse.success(categoryService.updateCategory(categoryDto));
        }catch (BusinessException e){
            return ApiResponse.error(e.getCode());
        }
    }

    /**
     * 获取所有分类列表
     * @return
     */
    @GetMapping("/getAll")
    public ApiResponse<?> getAllCategory() {
        try {
            List<Category> list = categoryService.getAllCategory();
            return ApiResponse.success(list);
        } catch (BusinessException e) {
            return ApiResponse.error(e.getCode());
        }
    }
}
