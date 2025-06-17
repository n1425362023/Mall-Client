package org.ysu.mall.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.entity.Product;
import org.ysu.mall.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/admin/product")
public class AdminProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ApiResponse<List<Product>> listAllProducts() {
        List<Product> products = productService.listAll();
        return ApiResponse.success(products);
    }

    @GetMapping("/{id}")
    public ApiResponse<Product> getProduct(@PathVariable Long id) {
        Product product = productService.getById(id);
        if (product != null) {
            return ApiResponse.success(product);
        } else {
            return ApiResponse.fail(404, "商品不存在");
        }
    }

    @PostMapping
    public ApiResponse<Void> addProduct(@RequestBody Product product) {
        boolean created = productService.save(product);
        if (created) {
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(400, "添加商品失败");
        }
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        boolean updated = productService.updateById(product);
        if (updated) {
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(400, "更新商品失败");
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.removeById(id);
        if (deleted) {
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(400, "删除商品失败");
        }
    }
}
