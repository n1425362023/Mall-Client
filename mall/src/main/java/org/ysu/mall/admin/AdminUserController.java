package org.ysu.mall.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.entity.User;
import org.ysu.mall.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class AdminUserController {
    @Autowired
    private UserService userService;

    // 查询所有用户
    @GetMapping
    public ApiResponse<List<User>> listUsers() {
        List<User> users = userService.listAll();
        return ApiResponse.success(users);
    }

    // 根据ID获取用户信息
    @GetMapping("/{id}")
    public ApiResponse<User> getUserById(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user != null) {
            return ApiResponse.success(user);
        } else {
            return ApiResponse.fail(404, "用户不存在");
        }
    }

    // 添加用户
    @PostMapping
    public ApiResponse<Void> addUser(@RequestBody User user) {
        boolean created = userService.save(user);
        if (created) {
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(400, "添加用户失败");
        }
    }

    // 更新用户
    @PutMapping("/{id}")
    public ApiResponse<Void> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        boolean updated = userService.updateById(user);
        if (updated) {
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(400, "更新用户失败");
        }
    }

    // 删除用户
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.removeById(id);
        if (deleted) {
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(400, "删除用户失败");
        }
    }
}
