package org.ysu.mall.common;

import lombok.Getter;
import org.ysu.mall.enums.ResultEnum;

@Getter
public class ApiResponse<T> {
    // ...原有字段和构造方法...
    private final int code;
    private final String message;
    private final T data;

    private ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    // 使用枚举的成功响应
    public static <T> ApiResponse<T>
    success(ResultEnum result, T data) {
        return new ApiResponse<>(result.getCode(), result.getMessage(), data);
    }


    // 直接返回数据
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage(), data);
    }
    //自定义成功信息
    public static <T> ApiResponse<T> success(ResultEnum result, String message) {
        return new ApiResponse<>(result.getCode(), message,null);
    }

    // 通过枚举获取错误信息
    public static ApiResponse<?> error(ResultEnum result) {
        return new ApiResponse<>(result.getCode(), result.getMessage(), null);
    }

    // 自定义错误信息
    public static ApiResponse<?> error(ResultEnum result, String message) {
        return new ApiResponse<>(result.getCode(), message, null);
    }
}
