package org.ysu.mall.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.dto.ProductDto;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.exception.BusinessException;
import org.ysu.mall.service.CategoryService;
import org.ysu.mall.service.ProductService;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
        private final ProductService productService;
        private final CategoryService categoryService;

        @PostMapping("/add")
        public ApiResponse<?> addProduct(@RequestBody ProductDto productDto) {
            try{
                if(categoryService.getCategoryById(productDto.getCategoryId())==null){
                    throw new BusinessException(ResultEnum.CATEGORY_NOT_FOUND);
                }
                return productService.addProduct(productDto)
                        ? ApiResponse.success(ResultEnum.SUCCESS)
                        : ApiResponse.error(ResultEnum.PRODUCT_ADD_ERROR);
            }catch (BusinessException e) {
                return ApiResponse.error(e.getCode());
            }
        }

        @DeleteMapping("/delete/{productId}")
        public ApiResponse<?> deleteProduct(@PathVariable("productId") Integer productId) {
            try{
                return productService.deleteProduct(productId)
                        ? ApiResponse.success(ResultEnum.SUCCESS)
                        : ApiResponse.error(ResultEnum.PRODUCT_DELETE_ERROR);
            }catch (BusinessException e) {
                return ApiResponse.error(e.getCode());
            }
        }

        @PostMapping("/update")
        public ApiResponse<?> updateProduct(@RequestBody @Valid ProductDto productDto) {
            try{
                if(categoryService.getCategoryById(productDto.getCategoryId())==null){
                    throw new BusinessException(ResultEnum.CATEGORY_NOT_FOUND);
                }
                return productService.updateProduct(productDto)
                        ? ApiResponse.success(ResultEnum.SUCCESS)
                        : ApiResponse.error(ResultEnum.PRODUCT_UPDATE_ERROR);
            }catch (BusinessException e) {
                return ApiResponse.error(e.getCode());
            }
        }

        @GetMapping("/get/{productId}")
        public ApiResponse<?> getProduct(@PathVariable("productId") Integer productId) {
            try{
                return ApiResponse.success(productService.getProductById(productId));
            }catch (BusinessException e) {
                return ApiResponse.error(e.getCode());
            }
        }

        @GetMapping("/list/{categoryId}")
        public ApiResponse<?> getProductList(@PathVariable("categoryId") Integer categoryId) {
            try{
                if(categoryService.getCategoryById(categoryId)==null){
                    throw new BusinessException(ResultEnum.CATEGORY_NOT_FOUND);
                }
                return ApiResponse.success(productService.getProductList(categoryId));
            }catch (BusinessException e) {
                return ApiResponse.error(e.getCode());
            }
        }

}
