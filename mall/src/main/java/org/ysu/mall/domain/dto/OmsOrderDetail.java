package org.ysu.mall.domain.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单详情DTO
 */
@Data
public class OmsOrderDetail {
    private String orderId;
    private Integer userId;
    private Integer addressId;
    private BigDecimal totalAmount;
    private Integer status;
    private Integer paymentMethod;
    private Date createTime;
    private Date payTime;
    private Date deliveryTime;
    private Date receiveTime;
    private String note;
    // 订单项详情列表
    private List<OmsOrderItemDetail> orderItemList;
    // 收货人信息
    private OmsReceiverInfoParam receiverInfo;
}

