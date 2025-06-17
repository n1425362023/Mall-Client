package org.ysu.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.ysu.mall.domain.entity.Orders;
import org.ysu.mall.mapper.OrdersMapper;
import org.ysu.mall.service.OrdersService;

/**
* @author DELL
* @description 针对表【orders】的数据库操作Service实现
* @createDate 2025-06-17 09:52:36
*/
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
    implements OrdersService {

}




