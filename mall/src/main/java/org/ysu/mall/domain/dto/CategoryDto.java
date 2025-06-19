package org.ysu.mall.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDto {
    /**
     *
     */
    private Integer categoryId;

    /**
     * 分类名称
     */
    @NotBlank(message = "Category name cannot be empty")
    private String name;

    /**
     * 父分类ID(0=顶级)
     */
    private Integer parentId;

    /**
     * 排序权重
     */
    private Integer sortOrder;
}
