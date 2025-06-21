package org.ysu.mall.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class CartDto {
    /**
     *
     */

    private Integer cartId;

    /**
     *
     */
    @NotNull(message = "The user id cannot be empty")
    private Integer userId;

    /**
     *
     */
    @NotNull(message = "The product id cannot be empty")
    private Integer productId;

    /**
     * 商品数量
     */
    @NotNull(message = "The product quantity cannot be empty")
    private Integer quantity;

    /**
     * 是否选中
     */
    @NotNull(message = "The selected status cannot be empty")
    private Boolean selected;

}
