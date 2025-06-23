package org.ysu.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.ysu.mall.domain.entity.OrderItem;
import org.ysu.mall.exception.BusinessException;
import org.ysu.mall.mapper.OrderItemMapper;
import org.ysu.mall.service.OrderItemService;
import org.ysu.mall.enums.ResultEnum;

import java.util.List;

/**
* @author DELL
* @description 针对表【order_item】的数据库操作Service实现
* @createDate 2025-06-17 10:22:27
*/
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem>
    implements OrderItemService {

    @Override
    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
        try {
            return this.lambdaQuery().eq(OrderItem::getOrderId, orderId).list();
        } catch (Exception e) {
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "获取订单项失败");
        }
    }

    @Override
    public boolean addOrderItem(OrderItem orderItem) {
        try {
            return this.save(orderItem);
        } catch (Exception e) {
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "添加订单项失败");
        }
    }

    @Override
    public boolean deleteOrderItem(Long orderItemId) {
        try {
            return this.removeById(orderItemId);
        } catch (Exception e) {
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "删除订单项失败");
        }
    }

    @Override
    public boolean updateOrderItem(OrderItem orderItem) {
        try {
            return this.updateById(orderItem);
        } catch (Exception e) {
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "更新订单项失败");
        }
    }
}
