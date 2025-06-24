package org.ysu.mall.enums;


import com.baomidou.mybatisplus.annotation.IEnum;

public enum ProductStatus implements IEnum<Integer> {
    DRAFT(0),      // 预售
    ON_SALE(1),    // 上架
    OFF_SALE(2);   // 下架

    private final int code;

    ProductStatus(int code) {
        this.code = code;
    }

    @Override
    public Integer getValue() {
        return this.code;  // 数据库存储的数字
    }
}