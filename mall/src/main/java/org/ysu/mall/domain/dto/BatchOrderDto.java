package org.ysu.mall.domain.dto;

import lombok.Data;
import java.util.List;

@Data
public class BatchOrderDto {
    /**
     * 要删除的订单 ID 列表
     */
    private List<Long> ids;

    private String note;
}