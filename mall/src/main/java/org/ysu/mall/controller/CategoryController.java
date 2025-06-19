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

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/add")
    public ApiResponse<?> addCategory(@Valid @RequestBody CategoryDto categoryDto) {
        try{
            return ApiResponse.success(categoryService.addCategory(categoryDto));
        }catch (BusinessException e){
            return ApiResponse.error(e.getCode());
        }
    }

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
    @PutMapping("/update")
    public ApiResponse<?> updateCategory(@Valid @RequestBody CategoryDto categoryDto) {
        try{
            return ApiResponse.success(categoryService.updateCategory(categoryDto));
        }catch (BusinessException e){
            return ApiResponse.error(e.getCode());
        }
    }

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
