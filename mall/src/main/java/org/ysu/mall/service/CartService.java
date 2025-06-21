package org.ysu.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ysu.mall.domain.dto.CartDto;
import org.ysu.mall.domain.entity.Cart;

import java.util.List;

/**
* @author DELL
* @description 针对表【cart】的数据库操作Service
* @createDate 2025-06-17 10:22:27
*/
public interface CartService extends IService<Cart> {
    Boolean addCart(CartDto cartDto);

    Boolean deleteCart(Integer cartId);

    Boolean updateCart(CartDto cartDto);

    List<Cart> getCartList(Integer userId);
}
