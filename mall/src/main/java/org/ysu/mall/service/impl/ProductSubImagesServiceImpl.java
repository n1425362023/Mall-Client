package org.ysu.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ysu.mall.domain.dto.ProductSubImagesDto;
import org.ysu.mall.domain.entity.ProductMainImages;
import org.ysu.mall.domain.entity.ProductSubImages;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.exception.BusinessException;
import org.ysu.mall.mapper.ProductSubImagesMapper;
import org.ysu.mall.service.ProductSubImagesService;
import org.ysu.mall.util.FileUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author DELL
* @description 针对表【product_sub_images】的数据库操作Service实现
* @createDate 2025-06-24 21:11:06
*/
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
@Slf4j
public class ProductSubImagesServiceImpl extends ServiceImpl<ProductSubImagesMapper, ProductSubImages>  implements ProductSubImagesService {
    private final ProductSubImagesMapper productSubImagesMapper;
    private final FileUtil fileUtil;

    public Boolean addBatchProductSubImages(List<ProductSubImagesDto> productSubImagesDto) {
        try {
            // 1. 转换 DTO 列表为实体列表
            List<ProductSubImages> productSubImages = productSubImagesDto.stream().map(dto -> {
                try {
                    return new ProductSubImages()
                            .setProductId(dto.getProductId())
                            .setImageUrl(fileUtil.uploadFile(dto.getImageFile()))
                            .setSortOrder(dto.getSortOrder());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());
            if (!saveBatch(productSubImages)) {
                throw new BusinessException(ResultEnum.ProductSubImages_ADD_ERROR);
            }
            return true;
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {

            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "添加商品副图失败");
        }
    }

    public Boolean addProductSubImage(ProductSubImagesDto productSubImagesDto) {
        try {
            ProductSubImages productSubImages = new ProductSubImages()
                    .setProductId(productSubImagesDto.getProductId())
                    .setImageUrl(fileUtil.uploadFile(productSubImagesDto.getImageFile()))
                    .setSortOrder(productSubImagesDto.getSortOrder());
            if (!save(productSubImages)) {
                throw new BusinessException(ResultEnum.ProductSubImages_ADD_ERROR);
            }
            return true;
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error fetching products with conditions: {}", e.getMessage());
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "添加商品副图失败");
        }
    }

    public Boolean deleteBatchProductSubImages(List<Integer> ids){
        try {
            if (!removeBatchByIds(ids)) {
                throw new BusinessException(ResultEnum.ProductSubImages_DELETE_ERROR);
            }
            return true;
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("删除商品副图失败");
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "删除商品副图失败");
        }
    }

    public Boolean deleteProductSubImagesByProductId(Integer productId){
        try {
            LambdaQueryChainWrapper<ProductSubImages> productSubImages= lambdaQuery().eq(ProductSubImages::getProductId, productId);
            return productSubImagesMapper.delete(productSubImages) > 0;
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "删除商品副图失败");
        }
    }

    public Boolean updateProductSubImages(Integer id,Integer sortOrder){
        try {
            ProductSubImages productSubImages = new ProductSubImages()
                    .setId(id)
                    .setSortOrder(sortOrder);
            if (!updateById(productSubImages)) {
                throw new BusinessException(ResultEnum.ProductSubImages_UPDATE_ERROR);
            }
            return true;
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "更新商品副图失败");
        }
    }

    public List<ProductSubImages> getProductSubImagesByProductId(Integer productId){
        try {
            LambdaQueryWrapper<ProductSubImages> productSubImages=  new LambdaQueryWrapper<ProductSubImages>()
                    .eq(ProductSubImages::getProductId, productId)
                    .orderByAsc(ProductSubImages::getSortOrder);
            return productSubImagesMapper.selectList(productSubImages);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "查询商品副图失败");
        }
    }

    public ProductSubImages getProductSubImagesById(Integer id){
        try {
            if(productSubImagesMapper.selectById(id)!=null) {
                return productSubImagesMapper.selectById(id);
            }else{
                throw new BusinessException(ResultEnum.ProductSubImages_NOT_FOUND);
            }
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "查询商品副图失败");
        }
    }
}




