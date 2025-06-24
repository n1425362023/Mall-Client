package org.ysu.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 * @TableName order_item
 */
@TableName(value ="order_item")
@Data
@Accessors(chain = true)
public class OrderItem {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long itemId;

    /**
     * 
     */
    private String orderId;

    /**
     * 
     */
    private Integer productId;

    /**
     * 商品快照名称
     */
    private String productName;

    /**
     * 商品快照图片
     */
    private String productImage;

    /**
     * 下单时单价
     */
    private BigDecimal unitPrice;

    /**
     * 购买数量
     */
    private Integer quantity;

    /**
     * 商品总价
     */
    private BigDecimal totalPrice;
}