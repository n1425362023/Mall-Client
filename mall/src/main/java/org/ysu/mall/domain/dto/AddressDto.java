package org.ysu.mall.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;

@Data
public class AddressDto {
    /**
     *
     */
    @NotNull(message = "Address ID cannot be empty")
    private Integer addressId;

    /**
     *
     */
    @NotNull(message = "User ID cannot be empty")
    private Integer userId;

    /**
     * 收货人
     */
    @NotBlank(message = "Receiver cannot be empty")
    private String receiver;

    /**
     * 手机号
     */
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "Phone number must be 11 digits")
    private String phone;

    /**
     * 省市区
     */
    @NotBlank(message = "Region cannot be empty")
    private String region;

    /**
     * 详细地址
     */
    @NotBlank(message = "Detail address cannot be empty")
    private String detail;

    /**
     * 默认地址
     */
    @NotNull(message = "Default address cannot be empty")
    private Integer isDefault;


}
