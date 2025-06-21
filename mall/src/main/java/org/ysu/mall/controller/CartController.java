package org.ysu.mall.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.dto.CartDto;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.exception.BusinessException;
import org.ysu.mall.service.CartService;
import org.ysu.mall.service.ProductService;
import org.ysu.mall.service.UserService;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final ProductService productService;
    private final UserService userService;
    @PostMapping("/add")
    public ApiResponse<?> addCart(@RequestBody @Valid CartDto cartDto) {
        try{
            if(userService.getUserById(cartDto.getUserId())==null){
                return ApiResponse.error(ResultEnum.USER_NOT_FOUND);
            }
            if(productService.getProductById(cartDto.getProductId())==null){
                return ApiResponse.error(ResultEnum.PRODUCT_NOT_FOUND);
            }
            if(cartService.addCart(cartDto)){
                return ApiResponse.success(ResultEnum.SUCCESS);
            }else{
                return ApiResponse.error(ResultEnum.CART_ADD_ERROR);
            }
        }catch (BusinessException e){
            return ApiResponse.error(e.getCode());
        }
    }

    @PostMapping("/delete/{cartId}")
    public ApiResponse<?> deleteCart(@PathVariable @Valid Integer cartId) {
        if(cartService.deleteCart(cartId)){
            return ApiResponse.success(ResultEnum.SUCCESS);
        }else{
            return ApiResponse.error(ResultEnum.CART_DELETE_ERROR);
        }
    }

    @PutMapping("/update")
    public ApiResponse<?> updateCart(@RequestBody @Valid CartDto cartDto) {
        if (userService.getUserById(cartDto.getUserId()) == null) {
            return ApiResponse.error(ResultEnum.USER_NOT_FOUND);
        }
        if (productService.getProductById(cartDto.getProductId()) == null) {
            return ApiResponse.error(ResultEnum.PRODUCT_NOT_FOUND);
        }
        if (cartService.updateCart(cartDto)) {
            return ApiResponse.success(ResultEnum.SUCCESS);
        } else {
            return ApiResponse.error(ResultEnum.CART_UPDATE_ERROR);
        }
    }
    @GetMapping("/list/{userId}")
    public ApiResponse<?> getCartList(@PathVariable @Valid Integer userId) {
        try{
            if(userService.getUserById(userId)==null){
                return ApiResponse.error(ResultEnum.USER_NOT_FOUND);
            }
            return ApiResponse.success(cartService.getCartList(userId));
        }catch (BusinessException e) {
            return ApiResponse.error(e.getCode());
        }
    }
}
