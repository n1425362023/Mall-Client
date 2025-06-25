package org.ysu.mall.admin;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.entity.Category;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.service.CategoryService;
import java.util.List;

/**
 * 管理员分类管理控制器
 */
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/category")
public class AdminCategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 获取所有分类列表
     * @return
     */
    @GetMapping("/list")
    public ApiResponse<List<Category>> listCategories() {
        List<Category> categories = categoryService.listAllCategories();
        return ApiResponse.success(categories);
    }

    /**
     * 根据ID获取分类信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ApiResponse<?> getCategory(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        return category != null ? ApiResponse.success(category) : ApiResponse.error(ResultEnum.CATEGORY_NOT_FOUND);
    }

    /**
     * 添加新分类
     * @param category
     * @return
     */
    @PostMapping("/add")
    public ApiResponse<?> addCategory(@RequestBody Category category) {
        boolean success = categoryService.save(category);
        return success ? ApiResponse.success("添加成功") : ApiResponse.error(ResultEnum.CATEGORY_ADD_ERROR);
    }

    /**
     * 更新分类信息
     * @param category
     * @return
     */
    @PostMapping("/update")
    public ApiResponse<?> updateCategory(@RequestBody Category category) {
        boolean success = categoryService.updateById(category);
        return success ? ApiResponse.success("更新成功") : ApiResponse.error(ResultEnum.CATEGORY_UPDATE_ERROR);
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public ApiResponse<?> deleteCategory(@PathVariable Long id) {
        boolean success = categoryService.removeById(id);
        return success ? ApiResponse.success("删除成功") : ApiResponse.error(ResultEnum.CATEGORY_DELETE_ERROR);
    }
}
