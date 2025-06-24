package org.ysu.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 * @TableName product_main_images
 */
@TableName(value ="product_main_images")
@Data
@Accessors(chain = true)
public class ProductMainImages {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 商品ID
     */
    private Integer productId;

    /**
     * 主图URL
     */
    private String imageUrl;

    /**
     * 排序字段
     */
    private Integer sortOrder;

    /**
     * 
     */
    private Date createdAt;
}