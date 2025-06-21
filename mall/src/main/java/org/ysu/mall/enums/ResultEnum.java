package org.ysu.mall.enums;

import lombok.Getter;

/**
 * 统一状态码和消息枚举
 */
@Getter
public enum ResultEnum {
    SUCCESS(200, "操作成功"),
    CREATED(201, "资源创建成功"),
    BAD_REQUEST(400, "请求参数错误"),
    UNAUTHORIZED(401, "token无效或已过期"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),
    USER_ADD_ERROR(4002, "用户创建失败"),
    USER_PASSWORD_RESET_FAILED(4003, "用户密码重置失败"),
    USER_DELETE_ERROR(4004, "用户删除失败"),
    USER_FROZEN_SUCCESS(4005, "用户冻结成功"),
    USER_UNFROZEN_SUCCESS(4006, "用户解冻成功"),
    USER_FREEZE_FAILED(4007, "用户冻结失败"),
    USER_UNFREEZE_FAILED(4008, "用户解冻失败"),
    USER_FROZEN(4009, "用户已被冻结"),
    USER_UPDATE_ERROR(4010, "用户更新失败"),
    CAPTCHA_EXPIRED(4011, "验证码已过期"),
    USER_NOT_FOUND(4012, "用户不存在"),
    USERNAME_CONFLICT(4013, "用户名冲突"),
    USER_PASSWORD_ERROR(4014, "密码错误"),

    ADDRESS_ADD_ERROR(4015, "地址添加失败"),
    ADDRESS_DELETE_ERROR(4016, "地址删除失败"),
    ADDRESS_NOT_FOUND(4017, "地址不存在"),
    ADDRESS_UPDATE_ERROR(4018, "地址更新失败"),

    CATEGORY_ADD_ERROR(4019, "分类添加失败"),
    CATEGORY_NOT_FOUND(4020, "分类不存在"),
    CATEGORY_DELETE_ERROR(4021, "分类删除失败"),
    CATEGORY_UPDATE_ERROR(4022, "分类更新失败"),

    PRODUCT_ADD_ERROR(4023, "商品添加失败"),
    PRODUCT_DELETE_ERROR(4024, "商品删除失败"),
    PRODUCT_UPDATE_ERROR(4025, "商品更新失败"),

    SYSTEM_ERROR(500, "服务器内部错误"),
    INTERNAL_ERROR(5001, "系统内部错误"),
    SERVICE_UNAVAILABLE(503, "服务不可用");

    private final int code;
    private final String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}