package org.ysu.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.ysu.mall.entity.Category;

/**
* @author DELL
* @description 针对表【category】的数据库操作Mapper
* @createDate 2025-06-17 10:22:27
* @Entity .entity.Category
*/
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}




