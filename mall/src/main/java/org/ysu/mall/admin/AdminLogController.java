package org.ysu.mall.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.entity.Logs;
import org.ysu.mall.service.AdminLogService;

import java.util.List;

/**
 * 管理员操作日志控制器
 */
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/log")
public class AdminLogController {
    @Autowired
    private AdminLogService adminLogService;

    /**
     * 获取所有操作日志
     * @return
     */
    @GetMapping
    public ApiResponse<List<Logs>> getAllLogs() {
        List<Logs> logs = adminLogService.getAllLogs();
        return ApiResponse.success(logs);
    }

    /**
     * 根据管理员ID查询操作日志
     * @param adminId
     * @return
     */
    @GetMapping("/byAdmin/{adminId}")
    public ApiResponse<List<Logs>> getLogsByAdmin(@PathVariable Long adminId) {
        List<Logs> logs = adminLogService.getLogsByAdminId(adminId);
        return ApiResponse.success(logs);
    }

    /**
     * 根据操作类型查询日志
     * @param action
     * @return
     */
    @GetMapping("/byAction")
    public ApiResponse<List<Logs>> getLogsByAction(@RequestParam String action) {
        List<Logs> logs = adminLogService.getLogsByAction(action);
        return ApiResponse.success(logs);
    }
}
