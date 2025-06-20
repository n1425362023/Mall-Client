package org.ysu.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.aggregation.ConvertOperators;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ysu.mall.domain.dto.ProductDto;
import org.ysu.mall.domain.entity.Product;
import org.ysu.mall.mapper.ProductMapper;
import org.ysu.mall.service.ProductService;

/**
* @author DELL
* @description 针对表【product】的数据库操作Service实现
* @createDate 2025-06-17 09:52:36
*/
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    private final ProductMapper productMapper;
    //TODO 调用fileService上传图片
    public Boolean addProduct(ProductDto productDto){

    }
}




