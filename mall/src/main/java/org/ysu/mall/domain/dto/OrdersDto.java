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
     * 运费金额
     */
    private BigDecimal freightAmount;

    /**
     * 折扣金额
     */
    private BigDecimal discountAmount;

    /**
     * 支付金额
     */
    private BigDecimal payAmount;
    /**
     *
     */
    private Date createdAt;

    /**
     * 订单备注
     */
    private String note;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人电话
     */
    private String receiverPhone;

    /**
     * 收货人邮编
     */
    private String receiverPostCode;

    /**
     * 收货人省份
     */
    private String receiverProvince;

    /**
     * 收货人城市
     */
    private String receiverCity;

    /**
     * 收货人区域
     */
    private String receiverRegion;

    /**
     * 收货人详细地址
     */
    private String receiverDetailAddress;

    /**
     * 物流公司
     */
    private String deliveryCompany;

    /**
     * 物流单号
     */
    private String deliverySn;
}
