package org.ysu.mall.entity;

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
 * @TableName payment
 */
@TableName(value ="payment")
@Data
@Accessors(chain = true)
public class Payment {
    /**
     * 支付流水号
     */
    @TableId
    private String paymentId;

    /**
     * 
     */
    private String orderId;

    /**
     * 
     */
    private BigDecimal amount;

    /**
     * 
     */
    private Object platform;

    /**
     * 
     */
    private Object status;

    /**
     * 第三方支付ID
     */
    private String transactionId;

    /**
     * 
     */
    private Date createdAt;
}