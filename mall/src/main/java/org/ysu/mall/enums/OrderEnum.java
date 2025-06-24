package org.ysu.mall.enums;

import lombok.Getter;

/**
 * 订单状态枚举
 */
@Getter
public enum OrderEnum {
    PENDING_PAYMENT(0, "待付款"),
    PENDING_SHIPMENT(1, "待发货"),
    PENDING_RECEIPT(2, "待收货"),
    RETURN(3, "退货"),
    EXCHANGE(4, "退换货"),
    COMPLETED(5, "完成");

    private final int code;
    private final String description;

    OrderEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static OrderEnum fromCode(int code) {
        for (OrderEnum orderEnum : OrderEnum.values()) {
            if (orderEnum.getCode() == code) {
                return orderEnum;
            }
        }
        throw new IllegalArgumentException("Invalid code for OrderEnum: " + code);
    }
}