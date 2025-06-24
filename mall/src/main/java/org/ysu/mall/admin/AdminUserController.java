package org.ysu.mall.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.entity.User;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.service.UserService;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/user")
public class AdminUserController {
    @Autowired
    private UserService userService;

    // 查询所有用户
    @GetMapping
    public ApiResponse<?> listUsers() {
        List<User> users = userService.listAll();
        return ApiResponse.success(users);
    }

    // 根据ID获取用户信息
    @GetMapping("/{id}")
    public ApiResponse<?> getUserById(@PathVariable Integer id) {
        User user = userService.getById(id);
        if (user != null) {
            return ApiResponse.success(user);
        } else {
            return ApiResponse.error(ResultEnum.USER_NOT_FOUND);
        }
    }

    // 添加用户
    @PostMapping
    public ApiResponse<?> addUser(@RequestBody User user) {
        boolean created = userService.save(user);
        if (created) {
            return ApiResponse.success();
        } else {
            return ApiResponse.error(ResultEnum.USER_NOT_FOUND);
        }
    }

    // 更新用户
    @PutMapping("/{id}")
    public ApiResponse<?> updateUser(@PathVariable Integer id, @RequestBody User user) {
        user.setUserId(id);
        boolean updated = userService.updateById(user);
        if (updated) {
            return ApiResponse.success();
        } else {
            return ApiResponse.error(ResultEnum.USER_NOT_FOUND);
        }
    }

    // 删除用户
    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteUser(@PathVariable Integer id) {
        boolean deleted = userService.removeById(id);
        if (deleted) {
            return ApiResponse.success();
        } else {
            return ApiResponse.error(ResultEnum.USER_NOT_FOUND);
        }
    }
}
