package org.ysu.mall.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.entity.Address;
import org.ysu.mall.domain.entity.Category;
import org.ysu.mall.service.AddressService;
import org.ysu.mall.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/user/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // 获取所有分类
    @GetMapping("/list")
    public ApiResponse<List<Category>> listCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ApiResponse.success(categories);
    }

    // 根据id获取分类详情
    @GetMapping("/{id}")
    public ApiResponse<Category> getCategory(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            return ApiResponse.fail("分类不存在");
        }
        return ApiResponse.success(category);
    }

    // 新增分类
    @PostMapping("/add")
    public ApiResponse<Void> addCategory(@RequestBody Category category) {
        boolean result = categoryService.addCategory(category);
        if (result) {
            return ApiResponse.success();
        } else {
            return ApiResponse.fail("添加失败");
        }
    }

    // 更新分类
    @PutMapping("/update")
    public ApiResponse<Void> updateCategory(@RequestBody Category category) {
        boolean result = categoryService.updateCategory(category);
        if (result) {
            return ApiResponse.success();
        } else {
            return ApiResponse.fail("更新失败");
        }
    }

    // 删除分类
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteCategory(@PathVariable Long id) {
        boolean result = categoryService.deleteCategory(id);
        if (result) {
            return ApiResponse.success();
        } else {
            return ApiResponse.fail("删除失败");
        }
    }

}
