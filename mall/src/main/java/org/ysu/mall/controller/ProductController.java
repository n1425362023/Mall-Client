package org.ysu.mall.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.dto.ProductDto;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.exception.BusinessException;
import org.ysu.mall.service.ProductService;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
        private final ProductService productService;


        @PostMapping("/add")
        public ApiResponse<?> addProduct(@RequestBody ProductDto productDto) {
            try{
               if(productService.addProduct(productDto)){
                   return  ApiResponse.success(ResultEnum.SUCCESS);
               }else{
                   return ApiResponse.error(ResultEnum.PRODUCT_ADD_ERROR);
               }
            }catch (BusinessException e){
                return ApiResponse.error(e.getCode());

            }
        }
}
