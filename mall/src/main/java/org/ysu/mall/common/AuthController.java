package org.ysu.mall.common;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class AuthController {


    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String username, @RequestParam String password) {
        // 这里应使用认证逻辑 + 生成JWT，示例直接返回假 token
        return Map.of("token", "fake-jwt-token-for-" + username);
    }

    @GetMapping("/admin/test")
    public String adminAccess() {
        return "你拥有ADMIN权限，可以访问该接口";
    }

    @GetMapping("/user/test")
    public String userAccess() {
        return "任意登录用户都可以访问此接口";
    }
}