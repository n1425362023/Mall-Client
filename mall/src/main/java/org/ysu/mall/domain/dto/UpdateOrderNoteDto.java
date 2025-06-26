package org.ysu.mall.domain.dto;

import lombok.Data;

@Data
public class UpdateOrderNoteDto {
    /**
     * 订单 ID
     */
    private Long id;

    /**
     * 备注内容
     */
    private String note;

    /**
     * 订单状态码，例如 "success", "unpaid" 等
     */
    private String status;
}