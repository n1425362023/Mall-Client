package org.ysu.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.ysu.mall.domain.entity.AdminLog;

@Mapper
public interface LogsMapper extends BaseMapper<AdminLog> {
}
