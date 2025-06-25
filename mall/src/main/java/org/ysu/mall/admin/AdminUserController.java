package org.ysu.mall.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.entity.User;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.service.UserService;

import java.util.List;

/**
 * 管理员用户管理控制器
 */
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/user")
public class AdminUserController {
    @Autowired
    private UserService userService;

    /**
     * 查询用户列表
     * @param name
     * @return
     */
    @GetMapping("/list")
    public ApiResponse<?> listUsers(@RequestParam(required = false) String name) {
        List<User> users = userService.listAll(name);
        return ApiResponse.success(users);
    }

    /**
     * 根据用户ID获取用户信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ApiResponse<?> getUserById(@PathVariable Integer id) {
        User user = userService.getById(id);
        if (user != null) {
            return ApiResponse.success(user);
        } else {
            return ApiResponse.error(ResultEnum.USER_NOT_FOUND);
        }
    }

    /**
     * 获取所有用户角色
     * @return
     */
    @GetMapping("/roles")
    public ApiResponse<?> listDistinctRoles() {
        List<String> roles = userService.listDistinctRoles();
        return ApiResponse.success(roles);
    }

    /**
     * 添加新用户
     * @param user
     * @return
     */
    @PostMapping
    public ApiResponse<?> addUser(@RequestBody User user) {
        boolean created = userService.save(user);
        if (created) {
            return ApiResponse.success();
        } else {
            return ApiResponse.error(ResultEnum.USER_NOT_FOUND);
        }
    }

    /**
     * 更新用户信息
     * @param id
     * @param user
     * @return
     */
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

    /**
     * 删除用户
     * @param id
     * @return
     */
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
