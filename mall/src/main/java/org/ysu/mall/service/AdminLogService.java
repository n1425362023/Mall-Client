package org.ysu.mall.service;

import java.util.List;
import org.ysu.mall.domain.entity.AdminLog;

public interface AdminLogService {
    List<AdminLog> getAllLogs();
    List<AdminLog> getLogsByAdminId(Long adminId);
    List<AdminLog> getLogsByAction(String action);
}
