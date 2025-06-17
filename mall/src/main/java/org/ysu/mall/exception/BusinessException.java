package org.ysu.mall.exception;

import org.ysu.mall.enums.ResultEnum;

public class BusinessException extends RuntimeException {
    private final ResultEnum code;

    // 通过枚举构建异常
    public BusinessException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum;
    }

    // 允许自定义消息
    public BusinessException(ResultEnum resultEnum, String customMessage) {
        super(customMessage);
        this.code = resultEnum;
    }

    public ResultEnum getCode() {
        return code;
    }
}