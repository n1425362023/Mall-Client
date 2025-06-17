package org.ysu.mall.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.entity.Address;
import org.ysu.mall.domain.entity.User;
import org.ysu.mall.service.AddressService;
import org.ysu.mall.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user/user")
public class UserController {
    @Autowired
    private UserService userService;

    // 获取当前用户信息（通常需要从token中获取用户ID）
    @GetMapping("/profile/{id}")
    public ApiResponse<User> getUserProfile(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) {
            return ApiResponse.fail("用户不存在");
        }
        return ApiResponse.success(user);
    }

    // 修改用户信息
    @PutMapping("/profile/{id}")
    public ApiResponse<String> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        boolean updated = userService.updateById(user);
        return updated ? ApiResponse.success("更新成功") : ApiResponse.fail("更新失败");
    }

    // 注销账户
    @DeleteMapping("/profile/{id}")
    public ApiResponse<String> deleteUser(@PathVariable Long id) {
        boolean removed = userService.removeById(id);
        return removed ? ApiResponse.success("账户已删除") : ApiResponse.fail("删除失败");
    }
}
