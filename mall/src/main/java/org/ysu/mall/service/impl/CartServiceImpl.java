package org.ysu.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ysu.mall.domain.dto.CartDto;
import org.ysu.mall.domain.entity.Cart;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.exception.BusinessException;
import org.ysu.mall.mapper.CartMapper;
import org.ysu.mall.service.CartService;

import java.util.List;

/**
* @author DELL
* @description 针对表【cart】的数据库操作Service实现
* @createDate 2025-06-17 10:22:27
*/
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
@Slf4j
public class

CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {
    private final  CartMapper cartMapper;

    public Boolean addCart(CartDto cartDto){
        try{
            Cart cat = new Cart()
                    .setUserId(cartDto.getUserId())
                    .setProductId(cartDto.getProductId())
                    .setQuantity(cartDto.getQuantity())
                    .setSelected(cartDto.getSelected());
            if(!save(cat)){
                throw new BusinessException(ResultEnum.CART_ADD_ERROR);
            }
            return true;
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            throw new BusinessException(ResultEnum.SYSTEM_ERROR,"添加购物车失败");
        }
    }

    public Boolean deleteCart(Integer cartId){
        try{
            if(!removeById(cartId)){
                throw new BusinessException(ResultEnum.CART_DELETE_ERROR);
            }
            return true;
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            throw new BusinessException(ResultEnum.SYSTEM_ERROR,"删除购物车失败");
        }
    }

    public Boolean updateCart(CartDto cartDto){
        try{
            Cart cat = new Cart()
                    .setUserId(cartDto.getUserId())
                    .setProductId(cartDto.getProductId())
                    .setQuantity(cartDto.getQuantity())
                    .setSelected(cartDto.getSelected());
            if(!updateById(cat)){
                throw new BusinessException(ResultEnum.CART_UPDATE_ERROR);
            }
            return true;
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            throw new BusinessException(ResultEnum.SYSTEM_ERROR,"更新购物车失败");
        }
    }

    public List<Cart> getCartList(Integer userId) {
        try {
            LambdaQueryWrapper<Cart> cart=  new LambdaQueryWrapper<Cart>()
                    .eq(Cart::getUserId, userId);
            return cartMapper.selectList(cart);
        } catch (Exception e) {
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "获取购物车列表失败");
        }
    }

    public List<Cart> listAllCarts() {
        try {
            return cartMapper.selectList(new LambdaQueryWrapper<>());
        } catch (Exception e) {
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "获取所有购物车失败");
        }
    }
}




