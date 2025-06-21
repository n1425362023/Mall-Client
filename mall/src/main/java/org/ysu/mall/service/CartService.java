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
    public Boolean addCart (CartDto cartDto);

    public Boolean deleteCart (Integer cartId);

    public Boolean updateCart (CartDto cartDto);

    public List<Cart> getCartList (Integer userId);
}
