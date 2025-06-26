package org.ysu.mall.service;

import cn.hutool.db.sql.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.ysu.mall.domain.dto.*;
import org.ysu.mall.domain.entity.Orders;
import org.ysu.mall.enums.OrderEnum;

/**
* @author DELL
* @description 针对表【orders】的数据库操作Service
* @createDate 2025-06-17 09:52:36
*/
public interface OrdersService extends IService<Orders> {
    // 批量发货
    int delivery(List<OrderDeliveryParam> deliveryParamList);
    // 批量关闭订单
    int close(List<Long> ids, String note);
    // 批量删除订单
    int delete(List<Long> ids);
    // 更新收货人信息
    int updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam);
    // 更新金额信息
    int updateMoneyInfo(OmsMoneyInfoParam moneyInfoParam);
    // 更新订单备注
    int updateNote(Long id, String note, OrderEnum status);
    // 获取订单列表
    List<Orders> listOrders(OrdersDto ordersDto);

    boolean addOrder(OrdersDto ordersDto);

    List<Orders> listOrdersByStatus(String code);

    Boolean deleteOrderById(String orderId);

    Boolean updateOrder(OrdersDto ordersDto);

    List<Orders> listOrder(Integer userId);

    Orders getOrderById(String orderId);
}
