package org.ysu.mall.domain.dto;

import lombok.Data;

/**
 * 订单发货参数
 */
@Data
public class OrderDeliveryParam {
    private Long orderId; // 订单ID
    private String deliveryCompany; // 物流公司
    private String deliverySn; // 物流单号
}

