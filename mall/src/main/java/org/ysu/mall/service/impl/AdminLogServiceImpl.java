package org.ysu.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.ysu.mall.domain.entity.AdminLog;
import org.ysu.mall.mapper.AdminLogMapper;
import org.ysu.mall.service.AdminLogService;

import java.util.List;

@Service
public class AdminLogServiceImpl extends ServiceImpl<AdminLogMapper, AdminLog> implements AdminLogService {

    @Override
    public List<AdminLog> getAllLogs() {
        return this.list();
    }

    @Override
    public List<AdminLog> getLogsByAdminId(Long adminId) {
        return this.lambdaQuery().eq(AdminLog::getAdminId, adminId).list();
    }

    @Override
    public List<AdminLog> getLogsByAction(String action) {
        return this.lambdaQuery().eq(AdminLog::getAction, action).list();
    }
}
