package org.ysu.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Cart
 *
 * 购物车实体类，用于存储用户的购物车信息。
 */
@TableName(value ="cart")
@Data
@Accessors(chain = true)
public class Cart {
    /**
     * 购物车ID
     */
    @TableId(type = IdType.AUTO)
    private Integer cartId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 商品ID
     */
    private Integer productId;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 是否选中
     */
    private Boolean selected;

    /**
     * 创建时间
     */
    private Date createdAt;
}