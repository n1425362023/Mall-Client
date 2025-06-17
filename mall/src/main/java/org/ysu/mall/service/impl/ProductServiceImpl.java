package org.ysu.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.ysu.mall.domain.entity.Product;
import org.ysu.mall.mapper.ProductMapper;
import org.ysu.mall.service.ProductService;

/**
* @author DELL
* @description 针对表【product】的数据库操作Service实现
* @createDate 2025-06-17 09:52:36
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService {

}




