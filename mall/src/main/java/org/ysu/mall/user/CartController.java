package org.ysu.mall.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.entity.Address;
import org.ysu.mall.domain.entity.Cart;
import org.ysu.mall.service.AddressService;
import org.ysu.mall.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/user/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    // 获取指定用户的购物车列表
    @GetMapping("/user/{userId}")
    public ApiResponse<List<Cart>> getCartByUserId(@PathVariable Long userId) {
        List<Cart> cartList = cartService.getByUserId(userId);
        return ApiResponse.success(cartList);
    }

    // 添加商品到购物车
    @PostMapping
    public ApiResponse<Void> addToCart(@RequestBody Cart cart) {
        boolean added = cartService.addCartItem(cart);
        if (added) {
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(400, "添加购物车失败");
        }
    }

    // 更新购物车中某商品的数量或信息
    @PutMapping("/{id}")
    public ApiResponse<Void> updateCartItem(@PathVariable Long id, @RequestBody Cart cart) {
        cart.setId(id);
        boolean updated = cartService.updateCartItem(cart);
        if (updated) {
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(400, "更新购物车失败");
        }
    }

    // 从购物车中删除商品
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCartItem(@PathVariable Long id) {
        boolean deleted = cartService.removeCartItem(id);
        if (deleted) {
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(400, "删除购物车商品失败");
        }
    }

    // 清空用户购物车
    @DeleteMapping("/user/{userId}")
    public ApiResponse<Void> clearCart(@PathVariable Long userId) {
        boolean cleared = cartService.clearUserCart(userId);
        if (cleared) {
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(400, "清空购物车失败");
        }
    }
}
