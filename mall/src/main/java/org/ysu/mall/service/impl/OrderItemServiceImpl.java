package org.ysu.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.ysu.mall.domain.entity.OrderItem;
import org.ysu.mall.mapper.OrderItemMapper;
import org.ysu.mall.service.OrderItemService;

/**
* @author DELL
* @description 针对表【order_item】的数据库操作Service实现
* @createDate 2025-06-17 10:22:27
*/
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem>
    implements OrderItemService {

}




