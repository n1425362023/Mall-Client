package org.ysu.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ysu.mall.domain.dto.ProductMainImagesDto;
import org.ysu.mall.domain.entity.ProductMainImages;

import java.util.List;

/**
* @author DELL
* @description 针对表【product_main_images】的数据库操作Service
* @createDate 2025-06-24 21:11:06
*/
public interface ProductMainImagesService extends IService<ProductMainImages> {
    Boolean addBatchProductMainImages(List<ProductMainImagesDto> productMainImagesDto);

    Boolean deleteBatchProductMainImages(List<Integer> ids);

    Boolean deleteProductMainImagesByProductId(Integer productId);

    Boolean updateProductMainImages(Integer id,Integer sortOrder);

    List<ProductMainImages> getProductMainImagesByProductId(Integer productId);

    ProductMainImages getProductMainImagesById(Integer id);
}
