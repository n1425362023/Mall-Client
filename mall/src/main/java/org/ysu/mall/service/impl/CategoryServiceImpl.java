package org.ysu.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.ysu.mall.entity.Category;
import org.ysu.mall.mapper.CategoryMapper;
import org.ysu.mall.service.CategoryService;

/**
* @author DELL
* @description 针对表【category】的数据库操作Service实现
* @createDate 2025-06-17 10:22:27
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService {

}




