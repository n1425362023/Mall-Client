package org.ysu.mall.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @NotNull(message = "主图不能为空")

    private transient MultipartFile imageFile;

    /**
     * 排序字段
     */

    private Integer sortOrder;

}
