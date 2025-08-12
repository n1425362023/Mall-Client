package org.ysu.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ysu.mall.domain.dto.ProductSubImagesDto;
import org.ysu.mall.domain.entity.ProductSubImages;

import java.util.List;

/**
* @author DELL
* @description 针对表【product_sub_images】的数据库操作Service
* @createDate 2025-06-24 21:11:06
*/
public interface ProductSubImagesService extends IService<ProductSubImages>  {
    Boolean addBatchProductSubImages(List<ProductSubImagesDto> productSubImagesDto);

    Boolean addProductSubImage(ProductSubImagesDto productSubImagesDto);

    Boolean deleteBatchProductSubImages(List<Integer> ids);

    Boolean deleteProductSubImagesByProductId(Integer productId);

    Boolean updateProductSubImages(Integer id,Integer sortOrder);

    List<ProductSubImages> getProductSubImagesByProductId(Integer productId);

    ProductSubImages getProductSubImagesById(Integer id);
}
