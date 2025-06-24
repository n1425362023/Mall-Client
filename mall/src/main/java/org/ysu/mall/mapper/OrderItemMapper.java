package org.ysu.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.ysu.mall.domain.entity.OrderItem;

/**
* @author DELL
* @description 针对表【order_item】的数据库操作Mapper
* @createDate 2025-06-17 10:22:27
* @Entity .entity.OrderItem
*/
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

}





