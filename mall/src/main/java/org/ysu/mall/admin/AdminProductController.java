package org.ysu.mall.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.entity.Product;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/admin/product")
public class AdminProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ApiResponse<?> listAllProducts() {
        List<Product> products = productService.listAll();
        return ApiResponse.success(products);
    }

    @GetMapping("/{id}")
    public ApiResponse<?> getProduct(@PathVariable Long id) {
        Product product = productService.getById(id);
        if (product != null) {
            return ApiResponse.success(product);
        } else {
            return ApiResponse.error(ResultEnum.NOT_FOUND);
        }
    }

    @PostMapping
    public ApiResponse<?> addProduct(@RequestBody Product product) {
        boolean created = productService.save(product);
        if (created) {
            return ApiResponse.success();
        } else {
            return ApiResponse.error(ResultEnum.PRODUCT_ADD_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ApiResponse<?> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        product.setProductId(id);
        boolean updated = productService.updateById(product);
        if (updated) {
            return ApiResponse.success();
        } else {
            return ApiResponse.error(ResultEnum.PRODUCT_UPDATE_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.removeById(id);
        if (deleted) {
            return ApiResponse.success();
        } else {
            return ApiResponse.error(ResultEnum.PRODUCT_DELETE_ERROR);
        }
    }
}
