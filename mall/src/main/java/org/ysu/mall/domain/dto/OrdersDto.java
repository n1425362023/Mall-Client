package org.ysu.mall.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrdersDto {
    /**
     * 订单号(时间戳+随机数)
     */
    private String orderId;

    /**
     *
     */
    private Integer userId;

    /**
     *
     */
    private Integer addressId;

    /**
     * 订单总额
     */
    private BigDecimal totalAmount;

    /**
     *
     */

    private Object status;

    /**
     * 支付方式
     */
    private Object paymentMethod;

    /**
     * 支付时间
     */
    private Date paymentTime;

    /**
     * 发货时间
     */
    private Date deliveryTime;

    /**
     *
     */
    private Date createdAt;
}
