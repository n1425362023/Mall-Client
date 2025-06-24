package org.ysu.mall.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ProductSubImagesDto {
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
     * 子图URL
     */

    private List<MultipartFile> imageUrl;

    /**
     * 排序字段
     */

    private Integer sortOrder;
}
