package org.ysu.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public Product addProduct(ProductDto productDto){
        try{
            Product product = new Product().
                    setProductName(productDto.getProductName())
                    .setPrice(productDto.getPrice())
                    .setStock(productDto.getStock())
                    .setDetail(productDto.getDetail())
                    .setStatus(productDto.getStatus());
            if(!save(product)){
                throw new BusinessException(ResultEnum.PRODUCT_ADD_ERROR);
            }
            return product;
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            throw new BusinessException(ResultEnum.SYSTEM_ERROR,"商品添加失败");
        }
    }

    public Boolean deleteProduct(Integer productId){
        try{
            Product product = productMapper.selectById(productId);
            if(!removeById(product)){
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
                    .setProductId(productDto.getProductId())
                    .setProductName(productDto.getProductName())
                    .setPrice(productDto.getPrice())
                    .setStock(productDto.getStock())
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
    public List<Product> listAll(ProductDto productDto) {
        try {
            LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
            if (productDto.getProductName() != null && !productDto.getProductName().isEmpty()) {
                queryWrapper.like(Product::getProductName, productDto.getProductName());
            }
            if (productDto.getPrice() != null) {
                queryWrapper.eq(Product::getPrice, productDto.getPrice());
            }
            if (productDto.getStock() != null) {
                queryWrapper.eq(Product::getStock, productDto.getStock());
            }
            if (productDto.getStatus() != null) {
                queryWrapper.eq(Product::getStatus, productDto.getStatus());
            }
            return productMapper.selectList(queryWrapper);
        } catch (Exception e) {
            log.error("Error fetching products with conditions: {}", e.getMessage());
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "根据条件查询商品失败");
        }
    }

    @Override
    public List<Product> listByCategoryId(Long categoryId) {
        try {
            LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Product::getCategoryId, categoryId);
            return productMapper.selectList(queryWrapper);
        } catch (Exception e) {
            log.error("Error fetching products by categoryId {}: {}", categoryId, e.getMessage());
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "根据分类ID查询商品失败");
        }
    }

    @Override
    public List<Product> listByBrand(String brand) {
        try {
            LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Product::getBrand, brand);
            return productMapper.selectList(queryWrapper);
        } catch (Exception e) {
            log.error("Error fetching products by brand {}: {}", brand, e.getMessage());
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "根据品牌查询商品失败");
        }
    }

    @Override
    public int countByCategoryId(Long categoryId) {
        try {
            LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Product::getCategoryId, categoryId);
            return Math.toIntExact(productMapper.selectCount(queryWrapper));
        } catch (Exception e) {
            log.error("Error counting products by categoryId {}: {}", categoryId, e.getMessage());
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "统计分类ID商品数量失败");
        }
    }

    @Override
    public int countByBrand(String brand) {
        try {
            LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Product::getBrand, brand);
            return Math.toIntExact(productMapper.selectCount(queryWrapper));
        } catch (Exception e) {
            log.error("Error counting products by brand {}: {}", brand, e.getMessage());
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "统计品牌商品数量失败");
        }
    }
}
