package org.ysu.mall.domain.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PaymentDto {
    private String paymentId; // 支付流水号
    private String orderId; // 订单ID
    private BigDecimal amount; // 支付金额
    private String platform; // 支付平台
    private String status; // 支付状态
    private String transactionId; // 第三方支付ID
    private Date paymentTime; // 支付时间
}
