package org.ysu.mall.domain.dto;

import lombok.Data;

/**
 * 收货人信息参数
 */
@Data
public class OmsReceiverInfoParam {
    private Long orderId; // 订单ID
    private String receiverName; // 收货人姓名
    private String receiverPhone; // 收货人电话
    private String receiverPostCode; // 邮政编码
    private String receiverProvince; // 省份
    private String receiverCity; // 城市
    private String receiverRegion; // 区/县
    private String receiverDetailAddress; // 详细地址
}

