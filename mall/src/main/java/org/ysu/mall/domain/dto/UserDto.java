package org.ysu.mall.domain.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import org.ysu.mall.enums.Role;
import org.ysu.mall.validationGroups.LoginGroup;
import org.ysu.mall.validationGroups.ResetPasswoedGroup;

@Data
public class UserDto {
    @NotNull(message = "UserID cannot be empty" ,groups = {ResetPasswoedGroup.class})
    private Integer userId;

    /**
     * 用户名
     */
    @NotBlank(message = "Username cannot be empty" ,groups = {LoginGroup.class})
    private String username;

    /**
     * 加密密码
     */
    @NotBlank(message = "Password cannot be empty" ,groups = {LoginGroup.class,ResetPasswoedGroup.class})
    private String password;


    /**
     * 手机号
     */
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "Phone number must be 11 digits")
    private String phone;

    /**
     * 头像URL
     */

    private MultipartFile avatar;

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
