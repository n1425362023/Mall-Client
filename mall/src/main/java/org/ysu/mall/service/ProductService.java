package org.ysu.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ysu.mall.domain.dto.ProductDto;
import org.ysu.mall.domain.entity.Product;

import java.util.List;

/**
* @author DELL
* @description 针对表【product】的数据库操作Service
* @createDate 2025-06-17 09:52:36
*/
public interface ProductService extends IService<Product> {
    Product addProduct(ProductDto productDto);

    Boolean deleteProduct(Integer productId);

    Boolean updateProduct(ProductDto productDto);

    Product getProductById(Integer productId);

    List<Product> getProductList(Integer categoryId);

    List<Product> listAll(ProductDto productDto);

    List<Product> listByCategoryId(Long categoryId);

    List<Product> listByBrand(String brand);

    int countByCategoryId(Long categoryId);

    int countByBrand(String brand);
}
