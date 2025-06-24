package org.ysu.mall.service;

import java.util.List;
import org.ysu.mall.domain.entity.Logs;
import org.ysu.mall.domain.entity.Logs;

public interface AdminLogService {
    List<Logs> getAllLogs();
    List<Logs> getLogsByAdminId(Long adminId);
    List<Logs> getLogsByAction(String action);
}
