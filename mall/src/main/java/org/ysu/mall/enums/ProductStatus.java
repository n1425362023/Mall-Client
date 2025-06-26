package org.ysu.mall.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum ProductStatus implements IEnum<String> {
    DRAFT("draft"),
    ON_SALE("on_sale"),
    OFF_SALE("off_sale");

    private final String value;

    ProductStatus(String value) {
        this.value = value;
    }

    @Override
    @JsonValue // ✅ 用于 JSON 序列化： enum -> "draft"
    public String getValue() {
        return value;
    }

    @JsonCreator // ✅ 用于 JSON 反序列化： "on_sale" -> enum
    public static ProductStatus fromValue(String value) {
        if (value == null) return null;
        for (ProductStatus status : values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid ProductStatus: " + value);
    }
}