package org.ysu.mall.domain.dto;

import com.baomidou.mybatisplus.annotation.TableId;
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
    @NotBlank(message = "The order id cannot be empty")
    private String orderId;

    /**
     *
     */
    @NotNull(message = "The user id cannot be empty")
    private Integer userId;

    /**
     *
     */
    @NotNull(message = "The address id cannot be empty")
    private Integer addressId;

    /**
     * 订单总额
     */
    @NotNull(message = "The total amount cannot be empty")
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
    @NotBlank(message = "The payment time cannot be empty")
    private Date paymentTime;

    /**
     * 发货时间
     */
    @NotBlank(message = "The delivery time cannot be empty")
    private Date deliveryTime;

    /**
     *
     */
    private Date createdAt;
}
