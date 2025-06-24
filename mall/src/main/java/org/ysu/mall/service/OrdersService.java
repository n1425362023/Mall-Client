package org.ysu.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

import org.ysu.mall.domain.dto.*;
import org.ysu.mall.domain.entity.Orders;

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
    // 获取订单详情
    OmsOrderDetail detail(Long id);
    // 更新收货人信息
    int updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam);
    // 更新金额信息
    int updateMoneyInfo(OmsMoneyInfoParam moneyInfoParam);
    // 更新订单备注
    int updateNote(Long id, String note, Integer status);
    // 获取订单列表
    List<Orders> listOrders(OrdersDto ordersDto);
    /**
     * 添加订单
     *
     * @param ordersDto 订单数据传输对象
     * @return 是否添加成功
     */
    boolean addOrder(OrdersDto ordersDto);
}
