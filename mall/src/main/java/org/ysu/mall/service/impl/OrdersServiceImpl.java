package org.ysu.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ysu.mall.domain.dto.*;
import org.ysu.mall.domain.entity.Orders;
import org.ysu.mall.mapper.OrdersMapper;
import org.ysu.mall.service.OrdersService;

import java.util.Date;
import java.util.List;

/**
 * 针对表【orders】的数据库操作Service实现
 * @author DELL
 * @createDate 2025-06-17 09:52:36
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Override
    public int delivery(List<OrderDeliveryParam> deliveryParamList) {
        int count = 0;
        for (OrderDeliveryParam param : deliveryParamList) {
            Orders order = this.getById(param.getOrderId());
            if (order != null) {
                order.setDeliveryCompany(param.getDeliveryCompany());
                order.setDeliverySn(param.getDeliverySn());
                order.setStatus(2); // 假设状态2表示已发货
                if (this.updateById(order)) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public int close(List<Long> ids, String note) {
        int count = 0;
        for (Long id : ids) {
            Orders order = this.getById(id);
            if (order != null) {
                order.setStatus(3); // 假设状态3表示已关闭
                order.setNote(note);
                if (this.updateById(order)) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    @Transactional
    public int delete(List<Long> ids) {
        return this.removeBatchByIds(ids) ? ids.size() : 0;
    }

    @Override
    public OmsOrderDetail detail(Long id) {
        Orders order = this.getById(id);
        if (order == null) {
            return null;
        }
        OmsOrderDetail detail = new OmsOrderDetail();
        detail.setOrderId(order.getOrderId());
        detail.setTotalAmount(order.getTotalAmount());
        detail.setStatus(order.getStatus() instanceof Integer ? (Integer) order.getStatus() : null);
        detail.setPaymentMethod(order.getPaymentMethod() instanceof Integer ? (Integer) order.getPaymentMethod() : null);
        detail.setCreateTime(order.getCreatedAt());
        detail.setNote(order.getNote());
        detail.setOrderItemList(null); // 需要实现查询订单项逻辑
        detail.setReceiverInfo(null); // 需要实现查询收货人信息逻辑
        return detail;
    }

    @Override
    public int updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam) {
        Orders order = this.getById(receiverInfoParam.getOrderId());
        if (order != null) {
            order.setReceiverName(receiverInfoParam.getReceiverName());
            order.setReceiverPhone(receiverInfoParam.getReceiverPhone());
            order.setReceiverPostCode(receiverInfoParam.getReceiverPostCode());
            order.setReceiverProvince(receiverInfoParam.getReceiverProvince());
            order.setReceiverCity(receiverInfoParam.getReceiverCity());
            order.setReceiverRegion(receiverInfoParam.getReceiverRegion());
            order.setReceiverDetailAddress(receiverInfoParam.getReceiverDetailAddress());
            return this.updateById(order) ? 1 : 0;
        }
        return 0;
    }

    @Override
    public int updateMoneyInfo(OmsMoneyInfoParam moneyInfoParam) {
        Orders order = this.getById(moneyInfoParam.getOrderId());
        if (order != null) {
            order.setTotalAmount(moneyInfoParam.getTotalAmount());
            order.setFreightAmount(moneyInfoParam.getFreightAmount());
            order.setDiscountAmount(moneyInfoParam.getDiscountAmount());
            order.setPayAmount(moneyInfoParam.getPayAmount());
            return this.updateById(order) ? 1 : 0;
        }
        return 0;
    }

    @Override
    public int updateNote(Long id, String note, Integer status) {
        Orders order = this.getById(id);
        if (order != null) {
            if (note != null && !note.isEmpty()) {
                order.setNote(note);
            }
            order.setStatus(status);
            return this.updateById(order) ? 1 : 0;
        }
        return 0;
    }

    @Override
    public List<Orders> listOrders(OrdersDto ordersDto) {
        return this.lambdaQuery()
            .eq(ordersDto.getOrderId() != null, Orders::getOrderId, ordersDto.getOrderId())
            .eq(ordersDto.getUserId() != null, Orders::getUserId, ordersDto.getUserId())
            .eq(ordersDto.getAddressId() != null, Orders::getAddressId, ordersDto.getAddressId())
            .eq(ordersDto.getTotalAmount() != null, Orders::getTotalAmount, ordersDto.getTotalAmount())
            .eq(ordersDto.getStatus() != null, Orders::getStatus, ordersDto.getStatus())
            .eq(ordersDto.getPaymentMethod() != null, Orders::getPaymentMethod, ordersDto.getPaymentMethod())
            .list();
    }

    @Override
    @Transactional
    public boolean addOrder(OrdersDto ordersDto) {
        Orders order = new Orders();
        order.setOrderId(ordersDto.getOrderId());
        order.setUserId(ordersDto.getUserId());
        order.setAddressId(ordersDto.getAddressId());
        order.setTotalAmount(ordersDto.getTotalAmount());
        order.setStatus(ordersDto.getStatus());
        order.setPaymentMethod(ordersDto.getPaymentMethod());
        order.setCreatedAt(new Date()); // 假设创建时间为当前时间
        return this.save(order);
    }
}
