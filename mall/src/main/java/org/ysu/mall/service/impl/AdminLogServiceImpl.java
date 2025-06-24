package org.ysu.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.ysu.mall.domain.entity.Logs;
import org.ysu.mall.mapper.AdminLogMapper;
import org.ysu.mall.service.AdminLogService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ysu
 * @description 针对表【admin_log(管理员操作日志)】的数据库操作Service实现
 * @createDate 2023-03-17 15:57:54
 */
@Service
public class AdminLogServiceImpl extends ServiceImpl<AdminLogMapper, Logs>
        implements AdminLogService {

    @Override
    public List<Logs> getAllLogs() {
        return this.list().stream().map(adminLog -> {
            Logs log = new Logs();
            log.setId(adminLog.getId());
            log.setAdminId(adminLog.getAdminId());
            log.setAction(adminLog.getAction());
            log.setTimestamp(adminLog.getTimestamp());
            return log;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Logs> getLogsByAdminId(Long adminId) {
        return this.lambdaQuery().eq(Logs::getAdminId, adminId).list().stream().map(adminLog -> {
            Logs log = new Logs();
            log.setId(adminLog.getId());
            log.setAdminId(adminLog.getAdminId());
            log.setAction(adminLog.getAction());
            log.setTimestamp(adminLog.getTimestamp());
            return log;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Logs> getLogsByAction(String action) {
        return this.lambdaQuery().eq(Logs::getAction, action).list().stream().map(adminLog -> {
            Logs log = new Logs();
            log.setId(adminLog.getId());
            log.setAdminId(adminLog.getAdminId());
            log.setAction(adminLog.getAction());
            log.setTimestamp(adminLog.getTimestamp());
            return log;
        }).collect(Collectors.toList());
    }
}
