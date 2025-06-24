package org.ysu.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.ysu.mall.domain.entity.Logs;

/**
 * AdminLogMapper
 *
 * 针对表【admin_log】的数据库操作Mapper接口。
 */
@Mapper
public interface AdminLogMapper extends BaseMapper<Logs> {
}
