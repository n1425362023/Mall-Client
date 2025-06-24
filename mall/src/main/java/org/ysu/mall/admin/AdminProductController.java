package org.ysu.mall.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.dto.ProductDto;
import org.ysu.mall.domain.entity.Product;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.service.ProductService;

import java.util.List;

/**
 * 管理员商品管理控制器
 */
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/product")
@Tag(name = "AdminOrdersController", description = "管理员订单管理")
public class AdminProductController {
    @Autowired
    private ProductService productService;

    /**
     * 获取所有商品信息
     * @param productDto
     * @return
     */
    @GetMapping("/list")
    @Operation(summary = "获取所有商品信息", description = "获取所有商品信息")
    public ApiResponse<?> listAllProducts(@RequestBody ProductDto productDto) {
        List<Product> products = productService.listAll(productDto);
        return ApiResponse.success(products);
    }

    /**
     * 添加新商品
     * @param product
     * @return
     */
    @PostMapping("/add")
    @Operation(summary = "添加商品", description = "管理员添加商品")
    public ApiResponse<?> addProduct(@RequestBody Product product) {
        boolean created = productService.save(product);
        if (created) {
            return ApiResponse.success();
        } else {
            return ApiResponse.error(ResultEnum.PRODUCT_ADD_ERROR);
        }
    }

    /**
     * 根据商品分类ID查询商品列表
     * @param categoryId
     * @return
     */
    @GetMapping("/listByCategory/{categoryId}")
    @Operation(summary = "商品分类", description = "商品分类")
    public ApiResponse<?> listProductsByCategory(@PathVariable Long categoryId) {
        List<Product> products = productService.listByCategoryId(categoryId);
        return ApiResponse.success(products);
    }

    /**
     * 根据品牌分类查询商品
     * @param brand
     * @return
     */
    @GetMapping("/listByBrand/{brand}")
    @Operation(summary = "根据品牌分类", description = "根据品牌分类查询商品")
    public ApiResponse<?> listProductsByBrand(@PathVariable String brand) {
        List<Product> products = productService.listByBrand(brand);
        return ApiResponse.success(products);
    }

    /**
     * 根据商品ID获取商品信息
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public ApiResponse<?> getProduct(@PathVariable Long id) {
        Product product = productService.getById(id);
        if (product != null) {
            return ApiResponse.success(product);
        } else {
            return ApiResponse.error(ResultEnum.NOT_FOUND);
        }
    }

    /**
     * 统计分类商品数量
     * @param categoryId
     * @return
     */
    @GetMapping("/countByCategory/{categoryId}")
    @Operation(summary = "统计分类商品数量", description = "统计分类商品数量")
    public ApiResponse<?> countProductsByCategory(@PathVariable Long categoryId) {
        int count = productService.countByCategoryId(categoryId);
        return ApiResponse.success(count);
    }

    /**
     * 统计品牌商品数量
     * @param brand
     * @return
     */
    @GetMapping("/countByBrand/{brand}")
    @Operation(summary = "统计品牌商品数量", description = "统计品牌商品数量")
    public ApiResponse<?> countProductsByBrand(@PathVariable String brand) {
        int count = productService.countByBrand(brand);
        return ApiResponse.success(count);
    }

    /**
     * 更新商品信息
     * @param id
     * @param product
     * @return
     */
    @PutMapping("/update/{id}")
    public ApiResponse<?> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        product.setProductId(id);
        boolean updated = productService.updateById(product);
        if (updated) {
            return ApiResponse.success();
        } else {
            return ApiResponse.error(ResultEnum.PRODUCT_UPDATE_ERROR);
        }
    }

    /**
     * 删除商品
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ApiResponse<?> deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.removeById(id);
        if (deleted) {
            return ApiResponse.success();
        } else {
            return ApiResponse.error(ResultEnum.PRODUCT_DELETE_ERROR);
        }
    }
}
