package org.ysu.mall.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.Getter;

/**
 * 订单状态枚举
 */
@Getter
public enum OrderEnum implements IEnum<String> {
    PENDING_PAYMENT("unpaid", "待付款"),       // 对应数据库字符串 'unpaid'
    PENDING_SHIPMENT("paid", "待发货"),        // 'paid'
    PENDING_RECEIPT("shipped", "待收货"),      // 'shipped'
    EXCHANGE("cancelled", "退换货"),           // 'cancelled'
    RETURN("cancelled", "退货"),              // 若有单独字段再区分
    COMPLETED("completed", "完成");           // 'completed'

    private final String code;
    private final String description;

    OrderEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String getValue() {
        return this.code;
    }

    public static OrderEnum fromCode(String code) {
        for (OrderEnum orderEnum : OrderEnum.values()) {
            if (orderEnum.getCode().equals(code)) {
                return orderEnum;
            }
        }
        throw new IllegalArgumentException("Invalid code for OrderEnum: " + code);
    }
}