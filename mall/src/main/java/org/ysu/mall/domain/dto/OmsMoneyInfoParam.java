package org.ysu.mall.domain.dto;

import lombok.Data;
import java.math.BigDecimal;

/**
 * 订单金额信息参数
 */
@Data
public class OmsMoneyInfoParam {
    private Long orderId; // 订单ID
    private BigDecimal totalAmount; // 订单总金额
    private BigDecimal freightAmount; // 运费金额
    private BigDecimal discountAmount; // 优惠金额
    private BigDecimal payAmount; // 实付金额
}

