package org.ysu.mall.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.ysu.mall.enums.ProductStatus;

import java.math.BigDecimal;

@Data
public class ProductDto {



    /**
     *
     */
    private Integer productId;

    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空")
    private String productName;

    /**
     * 分类ID
     */
    @NotNull(message = "分类ID不能为空")
    private Integer categoryId;

    /**
     * 销售价
     */
    @NotNull(message = "售价不能为空")
    private BigDecimal price;

    /**
     * 库存
     */
    @NotNull(message = "库存不能为空")
    private Integer stock;

    /**
     * 商品详情
     */
    @NotBlank(message = "商品详情不能为空")
    private String detail;

    /**
     * 状态
     */
    @NotNull(message = "状态不能为空")
    private ProductStatus status;

}
