package org.ysu.mall.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test_damon")
@Tag(name="test_damon", description = "这只是一个用于测试的接口")
public class TestDamon {

    @PostMapping("/login")
    @Operation(summary = "登录以后返回token")
    @Parameters({
            @Parameter(name = "username", description = "用户名", required = true),
            @Parameter(name = "password", description = "密码", required = true)
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "登录成功，返回token"),
            @ApiResponse(responseCode = "401", description = "暂未登录或token已经过期"),
            @ApiResponse(responseCode = "403", description = "没有相关权限")
    })
    public ResponseEntity<String> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 假设验证逻辑成功
        String token = "mocked-token";
        return ResponseEntity.ok(token);
    }

}
