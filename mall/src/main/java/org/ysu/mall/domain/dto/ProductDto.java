package org.ysu.mall.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import org.ysu.mall.enums.ProductStatus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ProductDto {



    /**
     *
     */
    private Integer productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 分类ID
     */
    private Integer categoryId;

    /**
     * 销售价
     */
    private BigDecimal price;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 主图URL
     */
    private String mainImages;

    /**
     * 子图URL集合(JSON格式)
     */
    private String subImages;

    /**
     * 商品详情
     */
    private String detail;

    /**
     * 状态
     */
    private ProductStatus status;

}
