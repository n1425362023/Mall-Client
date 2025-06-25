package org.ysu.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ysu.mall.domain.dto.OrderItemDto;
import org.ysu.mall.domain.entity.OrderItem;

import java.util.List;

/**
* @author DELL
* @description 针对表【order_item】的数据库操作Service
* @createDate 2025-06-17 10:22:27
*/
public interface OrderItemService extends IService<OrderItem> {
    List<OrderItem> getOrderItemsByOrderId(String orderId);

    Boolean addOrderItem(OrderItemDto orderItemDto);

    Boolean deleteOrderItemById(Long orderItemId);

    Boolean deleteOrderItemsByOrderId(String orderId);

    Boolean updateOrderItem(OrderItemDto orderItemDto);
}
