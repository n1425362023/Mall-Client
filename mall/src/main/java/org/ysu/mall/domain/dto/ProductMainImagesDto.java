package org.ysu.mall.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductMainImagesDto {
    /**
     *
     */

    private Integer id;

    /**
     * 商品ID
     */
    @NotNull(message = "商品ID不能为空")
    private Integer productId;

    /**
     * 主图URL
     */
    @NotBlank(message = "主图URL不能为空")
    private MultipartFile imageUrl;

    /**
     * 排序字段
     */

    private Integer sortOrder;

}
