package org.ysu.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ysu.mall.domain.dto.OrderItemDto;
import org.ysu.mall.domain.entity.OrderItem;
import org.ysu.mall.exception.BusinessException;
import org.ysu.mall.mapper.OrderItemMapper;
import org.ysu.mall.mapper.OrdersMapper;
import org.ysu.mall.service.OrderItemService;
import org.ysu.mall.enums.ResultEnum;

import java.util.List;

/**
* @author DELL
* @description 针对表【order_item】的数据库操作Service实现
* @createDate 2025-06-17 10:22:27
*/
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
@Slf4j
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

    private final OrdersMapper ordersMapper;

    private final OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItem> getOrderItemsByOrderId(String orderId) {
        try {
            return this.lambdaQuery().eq(OrderItem::getOrderId, orderId).list();
        } catch (Exception e) {
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "获取订单项失败");
        }
    }

    @Override
    public Boolean addOrderItem(OrderItemDto orderItemDto) {
        try {
            OrderItem orderItem = new OrderItem()
                    .setOrderId(orderItemDto.getOrderId())
                    .setProductId(orderItemDto.getProductId())
                    .setQuantity(orderItemDto.getQuantity())
                    .setProductName(orderItemDto.getProductName());
            if(!save(orderItem)){
                throw new BusinessException(ResultEnum.ORDER_ITEM_ADD_ERROR, "添加订单项失败");
            }
            return true;
        } catch (Exception e) {
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "添加订单项失败");
        }
    }

    @Override
    public Boolean deleteOrderItemById(Long orderItemId) {
        try {
            return this.removeById(orderItemId);
        } catch (Exception e) {
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "删除订单项失败");
        }
    }

    @Override
    public Boolean deleteOrderItemsByOrderId(String orderId){
        try {
            LambdaQueryWrapper<OrderItem> queryWrapper = new LambdaQueryWrapper<OrderItem>()
                    .eq(OrderItem::getOrderId, orderId);
            return orderItemMapper.delete(queryWrapper) > 0;
        } catch (Exception e) {
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "删除订单项失败");
        }
    }
    @Override
    public Boolean updateOrderItem(OrderItemDto orderItemDto) {
        try {
            OrderItem orderItem = new OrderItem()
                    .setItemId(orderItemDto.getItemId())
                    .setOrderId(orderItemDto.getOrderId())
                    .setProductId(orderItemDto.getProductId())
                    .setQuantity(orderItemDto.getQuantity())
                    .setProductName(orderItemDto.getProductName());
            return this.updateById(orderItem);
        } catch (Exception e) {
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "更新订单项失败");
        }
    }
}
