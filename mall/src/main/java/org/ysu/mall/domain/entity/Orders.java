package org.ysu.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 * @TableName orders
 */
@TableName(value ="orders")
@Data
@Accessors(chain = true)
public class Orders {
    /**
     * 订单号(时间戳+随机数)
     */
    @TableId
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