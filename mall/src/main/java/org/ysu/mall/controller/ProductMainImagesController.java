package org.ysu.mall.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.dto.ProductMainImagesDto;
import org.ysu.mall.domain.entity.ProductMainImages;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.exception.BusinessException;
import org.ysu.mall.service.ProductMainImagesService;
import org.ysu.mall.service.ProductService;
import org.ysu.mall.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/ProductMainImages")
public class ProductMainImagesController {
    private final ProductMainImagesService productMainImagesService;
    private final ProductService productService;
    private final FileUtil fileUtil;

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ApiResponse<?> addProductMainImages(@RequestPart("productId") List<Integer> productId,
                                                   @RequestPart("images") List<MultipartFile> imageFiles,
                                                   @RequestPart(value = "sortOrders", required = false) List<Integer> sortOrders) {
        try {
            List<ProductMainImagesDto> productMainImagesDTOs = new ArrayList<>();
            for (int i = 0; i < imageFiles.size(); i++) {
                ProductMainImagesDto dto = new ProductMainImagesDto();
                dto.setProductId(sortOrders != null && i < sortOrders.size() ? sortOrders.get(i) : 0);
                dto.setImageFile(imageFiles.get(i));
                dto.setSortOrder(sortOrders != null && i < sortOrders.size() ? sortOrders.get(i) : 0);
                productMainImagesDTOs.add(dto);
            }
            return productMainImagesService.addBatchProductMainImages(productMainImagesDTOs)
                    ? ApiResponse.success(ResultEnum.SUCCESS)
                    : ApiResponse.error(ResultEnum.ProductMainImages_ADD_ERROR);
        } catch (BusinessException e) {
            return ApiResponse.error(e.getCode());
        }
    }
        @DeleteMapping("/delete")
        public ApiResponse<?> deleteProductMainImages(@RequestBody List<Integer> ids) throws Exception {
            try {
                for (Integer id : ids) {
                    if (productMainImagesService.getProductMainImagesById(id) == null) {
                        throw new BusinessException(ResultEnum.ProductMainImages_NOT_FOUND);
                    } else {
                        fileUtil.deleteFileByUrl(productMainImagesService.getProductMainImagesById(id).getImageUrl());
                    }
                }
                return productMainImagesService.deleteBatchProductMainImages(ids)
                        ? ApiResponse.success(ResultEnum.SUCCESS)
                        : ApiResponse.error(ResultEnum.ProductMainImages_DELETE_ERROR);
            } catch (BusinessException e) {
                return ApiResponse.error(e.getCode());
            }
        }
        @DeleteMapping("/delete/{productId}")
        public ApiResponse<?> deleteProductMainImagesByProductId(@PathVariable Integer productId) throws Exception {
            try {
                if(productService.getProductById(productId) == null){
                    throw new BusinessException(ResultEnum.PRODUCT_NOT_FOUND);
                }else{
                    List<ProductMainImages> productMainImagesList = productMainImagesService.getProductMainImagesByProductId(productId);
                    for (ProductMainImages productMainImages : productMainImagesList) {
                        fileUtil.deleteFileByUrl(productMainImages.getImageUrl());
                    }
                }
                return productMainImagesService.deleteProductMainImagesByProductId(productId)
                        ? ApiResponse.success(ResultEnum.SUCCESS)
                        : ApiResponse.error(ResultEnum.ProductMainImages_DELETE_ERROR);
            }catch (BusinessException e) {
                return ApiResponse.error(e.getCode());
            }
        }

        @PutMapping("/update")
        public ApiResponse<?> updateProductMainImages(@RequestParam Integer id,@RequestParam Integer sortOrder) {
            try{
                if(productService.getProductById(id) == null) {
                    throw new BusinessException(ResultEnum.PRODUCT_NOT_FOUND);
                }
                return productMainImagesService.updateProductMainImages(id,sortOrder)
                        ? ApiResponse.success(ResultEnum.SUCCESS)
                        : ApiResponse.error(ResultEnum.ProductMainImages_UPDATE_ERROR);
            }catch (BusinessException e) {
                return ApiResponse.error(e.getCode());
            }
        }


        @GetMapping("/get/{productId}")
        public ApiResponse<?> getProductMainImagesByProductId(@PathVariable Integer productId) {
            try{
                if(productService.getProductById(productId)==null){
                    throw new BusinessException(ResultEnum.PRODUCT_NOT_FOUND);
                }
                return ApiResponse.success(productMainImagesService.getProductMainImagesByProductId(productId));
            }catch (BusinessException e) {
                return ApiResponse.error(e.getCode());
            }
        }
}
