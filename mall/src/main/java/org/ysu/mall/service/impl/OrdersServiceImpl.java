package org.ysu.mall.service.impl;

import cn.hutool.db.sql.Order;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ysu.mall.domain.dto.*;
import org.ysu.mall.domain.entity.Orders;
import org.ysu.mall.domain.entity.Product;
import org.ysu.mall.enums.OrderEnum;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.exception.BusinessException;
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

    private final OrdersMapper ordersMapper;

    public OrdersServiceImpl(OrdersMapper ordersMapper) {
        this.ordersMapper = ordersMapper;
    }

    @Override
    public int delivery(List<OrderDeliveryParam> deliveryParamList) {
        int count = 0;
        for (OrderDeliveryParam param : deliveryParamList) {
            Orders order = this.getById(param.getOrderId());
            if (order != null) {
                order.setDeliveryCompany(param.getDeliveryCompany());
                order.setDeliverySn(param.getDeliverySn());
                order.setStatus(OrderEnum.PENDING_RECEIPT); // 假设状态2表示已发货
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
                order.setStatus(OrderEnum.COMPLETED); // 假设状态3表示已关闭
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
    public int updateNote(Long id, String note, OrderEnum status) {
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
        try {
            LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
            if (ordersDto.getOrderId() != null) {
                queryWrapper.eq(Orders::getOrderId, ordersDto.getOrderId());
            }
            if (ordersDto.getUserId() != null) {
                queryWrapper.eq(Orders::getUserId, ordersDto.getUserId());
            }
            if (ordersDto.getAddressId() != null) {
                queryWrapper.eq(Orders::getAddressId, ordersDto.getAddressId());
            }
            if (ordersDto.getTotalAmount() != null) {
                queryWrapper.eq(Orders::getTotalAmount, ordersDto.getTotalAmount());
            }
            if (ordersDto.getStatus() != null) {
                queryWrapper.eq(Orders::getStatus, ordersDto.getStatus());
            }
            if (ordersDto.getPaymentMethod() != null) {
                queryWrapper.eq(Orders::getPaymentMethod, ordersDto.getPaymentMethod());
            }
            return this.list(queryWrapper);
        } catch (Exception e) {
            log.error("Error fetching orders with conditions: {}", e);
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "根据条件查询订单失败");
        }
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

    @Override
    public List<Orders> listOrdersByStatus(String status) {
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getStatus, status);
        return this.list(queryWrapper);
    }

    public Boolean deleteOrderById(String orderId){
        try{
            if(!removeById(orderId)){
                throw new BusinessException(ResultEnum.PRODUCT_DELETE_ERROR);
            }
            return true;
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            throw new BusinessException(ResultEnum.SYSTEM_ERROR,"商品删除失败");
        }
    }

    public Boolean updateOrder(OrdersDto ordersDto){
        try{
            Orders orders  = new Orders()
                    .setOrderId(ordersDto.getOrderId())
                    .setAddressId(ordersDto.getAddressId())
                    .setStatus(ordersDto.getStatus())
                    .setPaymentMethod(ordersDto.getPaymentMethod())
                    .setDeliveryCompany(ordersDto.getDeliveryCompany())
                    .setDeliverySn(ordersDto.getDeliverySn())
                    .setDeliveryTime(ordersDto.getDeliveryTime())
                    .setNote(ordersDto.getNote())
                    .setFreightAmount(ordersDto.getFreightAmount())
                    .setDiscountAmount(ordersDto.getDiscountAmount())
                    .setPayAmount(ordersDto.getPayAmount())
                    .setPaymentTime(ordersDto.getPaymentTime())
                    .setReceiverCity(ordersDto.getReceiverCity())
                    .setReceiverDetailAddress(ordersDto.getReceiverDetailAddress())
                    .setReceiverProvince(ordersDto.getReceiverProvince())
                    .setReceiverRegion(ordersDto.getReceiverRegion())
                    .setReceiverPostCode(ordersDto.getReceiverPostCode())
                    .setReceiverName(ordersDto.getReceiverName())
                    .setReceiverPhone(ordersDto.getReceiverPhone())
                    .setTotalAmount(ordersDto.getTotalAmount());
            if(!updateById(orders)){
                throw new BusinessException(ResultEnum.PRODUCT_UPDATE_ERROR);
            }
            return true;
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            throw new BusinessException(ResultEnum.SYSTEM_ERROR,"商品更新失败");
        }
    }

    public List<Orders> listOrder(Integer userId){
        try{
            LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Orders::getUserId,userId);
            return this.list(queryWrapper);
        }catch (Exception e){
            log.error("Error fetching orders with conditions: {}", e);
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "根据条件查询订单失败");
        }
    }

    public Orders getOrderById(String orderId){
        try{
            return ordersMapper.selectById(orderId);
        }catch (Exception e){
            log.error("Error fetching orders with conditions: {}", e);
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "根据条件查询订单失败");
        }
    }
}
