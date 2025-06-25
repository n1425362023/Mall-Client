package org.ysu.mall.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.ysu.mall.enums.PaymentStatus;
import org.ysu.mall.enums.PlatformEnum;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PaymentDto {

    private String paymentId; // 支付流水号
    @NotBlank(message = "订单ID不能为空")
    private String orderId; // 订单ID
    @NotBlank(message = "支付金额不能为空")
    private BigDecimal amount; // 支付金额
    @JsonProperty("platform")
    @NotBlank(message = "支付方式不能为空")
    private PlatformEnum platform; // 支付平台
    @JsonProperty("status")
    @NotBlank(message = "支付状态不能为空")
    private PaymentStatus status; // 支付状态
    @NotBlank(message = "第三方支付ID不能为空")
    private String transactionId; // 第三方支付ID
    @NotBlank(message = "支付时间不能为空")
    private Date paymentTime; // 支付时间
}
