package org.ysu.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;
import org.ysu.mall.enums.PaymentStatus;
import org.ysu.mall.enums.PlatformEnum;

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
    @TableId(type = IdType.ASSIGN_ID)
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
    private PlatformEnum platform;

    /**
     * 
     */
    private PaymentStatus status;

    /**
     * 第三方支付ID
     */
    private String transactionId;

    /**
     * 
     */
    private Date createdAt;
}