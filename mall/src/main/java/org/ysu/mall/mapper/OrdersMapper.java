package org.ysu.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.ysu.mall.domain.entity.Orders;

/**
* @author DELL
* @description 针对表【orders】的数据库操作Mapper
* @createDate 2025-06-17 09:52:36
* @Entity .entity.Orders
*/
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

}





