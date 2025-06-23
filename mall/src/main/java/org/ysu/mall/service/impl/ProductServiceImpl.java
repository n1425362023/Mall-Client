package org.ysu.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ConvertOperators;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ysu.mall.domain.dto.ProductDto;
import org.ysu.mall.domain.entity.Product;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.exception.BusinessException;
import org.ysu.mall.mapper.ProductMapper;
import org.ysu.mall.service.ProductService;
import org.ysu.mall.util.FileUtil;

import java.util.List;

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
    private final FileUtil fileUtil;

    public Boolean addProduct(ProductDto productDto){
        try{
            Product product = new Product().
                    setProductName(productDto.getProductName())
                    .setPrice(productDto.getPrice())
                    .setStock(productDto.getStock())
                    .setMainImages(fileUtil.uploadFiles(productDto.getMainImages()))
                    .setSubImages(fileUtil.uploadFiles(productDto.getSubImages()))
                    .setDetail(productDto.getDetail())
                    .setStatus(productDto.getStatus());
            if(!save(product)){
                throw new BusinessException(ResultEnum.PRODUCT_ADD_ERROR);
            }
            return true;
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            throw new BusinessException(ResultEnum.SYSTEM_ERROR,"商品添加失败");
        }
    }

    public Boolean deleteProduct(Integer productId){
        try{
            fileUtil.deleteFilesByUrls(productMapper.selectById(productId).getMainImages());
            fileUtil.deleteFilesByUrls(productMapper.selectById(productId).getSubImages());
            if(!removeById(productId)){
                throw new BusinessException(ResultEnum.PRODUCT_DELETE_ERROR);
            }
            return true;
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            throw new BusinessException(ResultEnum.SYSTEM_ERROR,"商品删除失败");
        }
    }

    public Boolean updateProduct(ProductDto productDto){
        try{
            Product product = new Product()
                    .setProductName(productDto.getProductName())
                    .setPrice(productDto.getPrice())
                    .setStock(productDto.getStock())
                    .setMainImages(fileUtil.uploadFiles(productDto.getMainImages()))
                    .setSubImages(fileUtil.uploadFiles(productDto.getSubImages()))
                    .setDetail(productDto.getDetail())
                    .setStatus(productDto.getStatus());
            if(!updateById(product)){
                throw new BusinessException(ResultEnum.PRODUCT_UPDATE_ERROR);
            }
            return true;
        }catch (BusinessException e){
            throw e;
        }catch (Exception e) {
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "商品更新失败");
        }
    }

    public Product getProductById(Integer productId){
        try{
            return productMapper.selectById(productId);
        }catch (Exception e){
            throw new BusinessException(ResultEnum.SYSTEM_ERROR,"商品查询失败");
        }
    }

    public List<Product> getProductList(Integer categoryId){
        try{
            LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Product::getCategoryId,categoryId);
            return productMapper.selectList(queryWrapper);
        }catch (Exception e){
            throw new BusinessException(ResultEnum.SYSTEM_ERROR,"商品查询失败");
        }
    }

    @Override
    public List<Product> listAll() {
        try {
            return productMapper.selectList(new LambdaQueryWrapper<>());
        } catch (Exception e) {
            log.error("Error fetching all products: {}", e.getMessage());
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "获取所有商品失败");
        }
    }
}
