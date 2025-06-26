package org.ysu.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ysu.mall.domain.dto.ProductMainImagesDto;
import org.ysu.mall.domain.entity.ProductMainImages;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.exception.BusinessException;
import org.ysu.mall.mapper.ProductMainImagesMapper;
import org.ysu.mall.service.ProductMainImagesService;
import org.ysu.mall.util.FileUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author DELL
* @description 针对表【product_main_images】的数据库操作Service实现
* @createDate 2025-06-24 21:11:06
*/
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
@Slf4j
public class ProductMainImagesServiceImpl extends ServiceImpl<ProductMainImagesMapper, ProductMainImages> implements ProductMainImagesService {
    private final ProductMainImagesMapper productMainImagesMapper;
    private final FileUtil fileUtil;

    public Boolean addBatchProductMainImages(List<ProductMainImagesDto> productMainImagesDto) {
        try {
            // 1. 转换 DTO 列表为实体列表
            List<ProductMainImages> productMainImages = productMainImagesDto.stream().map(dto -> {
                try {
                    return new ProductMainImages()
                            .setProductId(dto.getProductId())
                            .setImageUrl(fileUtil.uploadFile(dto.getImageFile()))
                            .setSortOrder(dto.getSortOrder());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());
            if (!saveBatch(productMainImages)) {
                throw new BusinessException(ResultEnum.ProductMainImages_ADD_ERROR);
            }
            return true;
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("添加商品主图失败");
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "添加商品主图失败");
        }
    }

    public Boolean deleteBatchProductMainImages(List<Integer> ids){
        try {
            if (!removeBatchByIds(ids)) {
                throw new BusinessException(ResultEnum.ProductMainImages_DELETE_ERROR);
            }
            return true;
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "删除商品主图失败");
        }
    }

    public Boolean deleteProductMainImagesByProductId(Integer productId){
        try {
            LambdaQueryChainWrapper<ProductMainImages> productMainImages= lambdaQuery().eq(ProductMainImages::getProductId, productId);
            return productMainImagesMapper.delete(productMainImages) > 0;
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "删除商品主图失败");
        }
    }

    public Boolean updateProductMainImages(Integer id,Integer sortOrder){
        try {
            ProductMainImages productMainImages = new ProductMainImages()
                    .setId(id)
                    .setSortOrder(sortOrder);
            if (!updateById(productMainImages)) {
                throw new BusinessException(ResultEnum.ProductMainImages_UPDATE_ERROR);
            }
            return true;
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "更新商品主图失败");
        }
    }

    public List<ProductMainImages> getProductMainImagesByProductId(Integer productId){
        try {
            LambdaQueryWrapper<ProductMainImages> productMainImages=  new LambdaQueryWrapper<ProductMainImages>()
                    .eq(ProductMainImages::getProductId, productId)
                    .orderByAsc(ProductMainImages::getSortOrder);
            return productMainImagesMapper.selectList(productMainImages);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "查询商品主图失败");
        }
    }

    public ProductMainImages getProductMainImagesById(Integer id){
        try {
            if(productMainImagesMapper.selectById(id)!=null) {
                return productMainImagesMapper.selectById(id);
            }else{
                throw new BusinessException(ResultEnum.ProductMainImages_NOT_FOUND);
            }
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "查询商品主图失败");
        }
    }
}




