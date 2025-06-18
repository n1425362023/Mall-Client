package org.ysu.mall.domain.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.ysu.mall.enums.Role;

import java.util.Date;

@Data
public class UserDto {
    @NotNull(message = "UserID cannot be empty" )
    private Integer userId;

    /**
     * 用户名
     */
    @NotBlank(message = "Username cannot be empty" )
    private String username;

    /**
     * 加密密码
     */
    @NotBlank(message = "Password cannot be empty" )
    private String password;

    /**
     * 邮箱
     */
    @Email(message = "Email format is incorrect")
    private String email;

    /**
     * 手机号
     */
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "Phone number must be 11 digits")
    private String phone;

    /**
     * 头像URL
     */

    private String avatar;

    /**
     * 用户角色
     */
    @JsonProperty("role")
    private Role role;

    /**
     * 状态(0=禁用,1=正常)
     */

    private Integer status;

}
