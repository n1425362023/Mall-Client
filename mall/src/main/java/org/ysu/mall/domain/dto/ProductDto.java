package org.ysu.mall.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.ysu.mall.enums.ProductStatus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ProductDto {



    /**
     *
     */
    @NotNull(message = "The product ID cannot be empty")
    private Integer productId;

    /**
     * 商品名称
     */
    @NotBlank(message = "The product name cannot be empty")
    private String productName;

    /**
     * 分类ID
     */
    @NotNull(message = "The category ID cannot be empty")
    private Integer categoryId;

    /**
     * 销售价
     */
    @NotNull(message = "The selling price cannot be empty")
    private BigDecimal price;

    /**
     * 库存
     */
    @NotNull(message = "The stock cannot be empty")
    private Integer stock;

    /**
     * 主图URL
     */
    @NotBlank(message = "The main image URL cannot be empty")
    private String mainImage;

    /**
     * 子图URL集合(JSON格式)
     */
    @NotBlank(message = "The sub image URL cannot be empty")
    private List<String> subImages;

    /**
     * 商品详情
     */
    @NotBlank(message = "The product detail cannot be empty")
    private String detail;

    /**
     * 状态
     */
    @NotNull(message = "The product status cannot be empty")
    private ProductStatus status;

}
