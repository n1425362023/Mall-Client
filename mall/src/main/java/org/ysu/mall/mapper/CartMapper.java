package org.ysu.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.ysu.mall.entity.Cart;

/**
* @author DELL
* @description 针对表【cart】的数据库操作Mapper
* @createDate 2025-06-17 10:22:27
* @Entity .entity.Cart
*/
@Mapper
public interface CartMapper extends BaseMapper<Cart> {

}




