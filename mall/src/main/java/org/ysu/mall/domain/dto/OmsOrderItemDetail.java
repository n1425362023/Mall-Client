package org.ysu.mall.domain.dto;

import lombok.Data;
import java.math.BigDecimal;

/**
 * 订单项详情DTO
 */
@Data
public class OmsOrderItemDetail {
    private Long id; // 订单项ID
    private String orderId; // 所属订单ID
    private Long productId; // 商品ID
    private String productName; // 商品名称
    private String productPic; // 商品图片
    private BigDecimal productPrice; // 商品单价
    private Integer productQuantity; // 商品数量
    private String productAttr; // 商品销售属性（如颜色、尺码等）
}

