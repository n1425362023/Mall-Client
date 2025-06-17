package org.ysu.mall.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.entity.Address;
import org.ysu.mall.domain.entity.Product;
import org.ysu.mall.service.AddressService;
import org.ysu.mall.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/user/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    // 获取所有商品列表
    @GetMapping("/list")
    public ApiResponse<List<Product>> listProducts() {
        List<Product> products = productService.getAllProducts();
        return ApiResponse.success(products);
    }

    // 根据商品ID获取商品详情
    @GetMapping("/{id}")
    public ApiResponse<Product> getProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ApiResponse.fail("商品不存在");
        }
        return ApiResponse.success(product);
    }

    // 模糊查询（比如搜索）
    @GetMapping("/search")
    public ApiResponse<List<Product>> searchProducts(@RequestParam String keyword) {
        List<Product> products = productService.searchProducts(keyword);
        return ApiResponse.success(products);
    }

    // 根据分类ID查询商品
    @GetMapping("/category/{categoryId}")
    public ApiResponse<List<Product>> listByCategory(@PathVariable Long categoryId) {
        List<Product> products = productService.getProductsByCategoryId(categoryId);
        return ApiResponse.success(products);
    }
}
