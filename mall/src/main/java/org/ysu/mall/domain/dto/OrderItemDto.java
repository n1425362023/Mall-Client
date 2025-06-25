package org.ysu.mall.domain.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {

    /**
     *
     */
    @NotNull(message = "Line item ID cannot be empty")
    private Long itemId;

    /**
     *
     */
    @NotBlank(message = "Order ID cannot be empty")
    private String orderId;

    /**
     *
     */
    @NotNull(message = "Product ID cannot be empty")
    private Integer productId;

    /**
     * 商品快照名称
     */
    @NotBlank(message = "Product name cannot be empty")
    private String productName;


    /**
     * 下单时单价
     */
    @NotNull(message = "Unit price cannot be empty")
    private BigDecimal unitPrice;

    /**
     * 购买数量
     */
    @NotNull(message = "Quantity cannot be empty")
    private Integer quantity;

    /**
     * 商品总价
     */
    @NotNull(message = "Total price cannot be empty")
    private BigDecimal totalPrice;
}
