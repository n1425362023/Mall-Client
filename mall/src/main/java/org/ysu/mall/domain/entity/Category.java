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
 * @TableName category
 */
@TableName(value ="category")
@Data
@Accessors(chain = true)
public class Category {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer categoryId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 父分类ID(0=顶级)
     */
    private Integer parentId;

    /**
     * 排序权重
     */
    private Integer sortOrder;

    /**
     * 
     */
    private Date createdAt;
}