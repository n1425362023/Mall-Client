package org.ysu.mall.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.dto.UserDto;
import org.ysu.mall.domain.entity.ProductMainImages;
import org.ysu.mall.domain.entity.User;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.exception.BusinessException;
import org.ysu.mall.service.UserService;
import org.ysu.mall.util.FileUtil;
import org.ysu.mall.util.JwtUtil;
import org.ysu.mall.validationGroups.LoginGroup;
import org.ysu.mall.validationGroups.ResetPasswoedGroup;

import java.util.List;

/**
 * 用户管理控制器
 */
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final FileUtil fileUtil;

    /**
     * 用户注册
     * @param userDto
     * @return
     */
    @PostMapping("/register")
    public ApiResponse<?> register(@Valid @RequestBody UserDto userDto) {
        try{
            return userService.register(userDto)
                    ? ApiResponse.success(ResultEnum.SUCCESS)
                    :ApiResponse.error(ResultEnum.USER_ADD_ERROR);
        }catch (BusinessException e){
            return ApiResponse.error(e.getCode());

        }
    }

    /**
     * 用户登录
     * @param userDto
     * @return
     */
    @PostMapping("/login")
    public ApiResponse<?> login(@Validated({LoginGroup.class}) @RequestBody UserDto userDto) {
        try{
            User user = userService.login(userDto.getUsername(), userDto.getPassword());
            user.setToken(JwtUtil.generateToken(user.getUserId(),user.getUsername()));
            return ApiResponse.success(user.getToken());
        }catch (BusinessException e){
            return ApiResponse.error(e.getCode());
        }
    }

    /**
     * 更新用户信息
     * @param userDto
     * @return
     */
    @PutMapping("/update")
    public ApiResponse<?> update(@Valid @RequestBody UserDto userDto) {
        try{
            User user = userService.update(userDto);
            return ApiResponse.success(user);
        }catch (BusinessException e){
            return ApiResponse.error(e.getCode());
        }
    }

    /**
     * 重置用户密码
     * @param userDto
     * @return
     */
    @PutMapping("/resetPassword")
    public ApiResponse<?> resetPassword(@Validated({ResetPasswoedGroup.class}) @RequestBody UserDto userDto) {
        try{
            return userService.resetPassword(userDto.getUserId(), userDto.getPassword())
                    ?ApiResponse.success(ResultEnum.SUCCESS)
                    :ApiResponse.error(ResultEnum.USER_PASSWORD_RESET_FAILED);
        }catch (BusinessException e){
            return ApiResponse.error(e.getCode());
        }
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @DeleteMapping("/delete")
    public ApiResponse<?> delete(@RequestParam Integer userId) throws Exception {
        try{
            if(userService.getUserById(userId) == null){
                throw new BusinessException(ResultEnum.USER_NOT_FOUND);
            }else{
              User user= userService.getUserById(userId);
              fileUtil.deleteFileByUrl(user.getAvatar());
            }
            return userService.delete(userId)
                    ?ApiResponse.success(ResultEnum.SUCCESS)
                    :ApiResponse.error(ResultEnum.USER_DELETE_ERROR);
        }catch (BusinessException e){
            return ApiResponse.error(e.getCode());
        }
    }
}
