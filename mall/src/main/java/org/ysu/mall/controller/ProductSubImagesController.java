package org.ysu.mall.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.dto.ProductSubImagesDto;
import org.ysu.mall.domain.entity.ProductSubImages;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.exception.BusinessException;
import org.ysu.mall.service.ProductSubImagesService;
import org.ysu.mall.service.ProductService;
import org.ysu.mall.util.FileUtil;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/ProductSubImages")
public class ProductSubImagesController {
        private final ProductSubImagesService productSubImagesService;
        private final ProductService productService;
        private final FileUtil fileUtil;

        @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ApiResponse<?> addProductSubImages(
//                @RequestPart("productId") Integer productId,
//                @RequestPart("images") List<MultipartFile> imageFiles,
//                @RequestPart(value = "sortOrders", required = false) List<Integer> sortOrders
               ProductSubImagesDto productSubImagesDto
        ) {
                try {
//                        List<ProductSubImagesDto> productSubImagesDTOs = new ArrayList<>();
//                        for (int i = 0; i < imageFiles.size(); i++) {
//                                ProductSubImagesDto dto = new ProductSubImagesDto();
//                                dto.setProductId(productId);
//                                dto.setImageFile(imageFiles.get(i));
//                                dto.setSortOrder(sortOrders != null && i < sortOrders.size() ? sortOrders.get(i) : 0);
//                                productSubImagesDTOs.add(dto);
//                        }

                        return productSubImagesService.addProductSubImage(productSubImagesDto)
                                ? ApiResponse.success(ResultEnum.SUCCESS)
                                : ApiResponse.error(ResultEnum.ProductSubImages_ADD_ERROR);
                } catch (BusinessException e) {
                        return ApiResponse.error(e.getCode());
                }
        }
        @DeleteMapping("/delete")
        public ApiResponse<?> deleteProductSubImages(@RequestBody List<Integer> ids) throws Exception {
                try {
                        for (Integer id : ids) {
                                if (productSubImagesService.getProductSubImagesById(id) == null) {
                                        throw new BusinessException(ResultEnum.ProductSubImages_NOT_FOUND);
                                } else {
                                        fileUtil.deleteFileByUrl(productSubImagesService.getProductSubImagesById(id).getImageUrl());
                                }
                        }
                        return productSubImagesService.deleteBatchProductSubImages(ids)
                                ? ApiResponse.success(ResultEnum.SUCCESS)
                                : ApiResponse.error(ResultEnum.ProductSubImages_DELETE_ERROR);
                } catch (BusinessException e) {
                        return ApiResponse.error(e.getCode());
                }
        }
        @DeleteMapping("/delete/{productId}")
        public ApiResponse<?> deleteProductSubImagesByProductId(@PathVariable Integer productId)  throws Exception {
                try {
                        if(productService.getProductById(productId) == null){
                                throw new BusinessException(ResultEnum.PRODUCT_NOT_FOUND);
                        }else{
                                List<ProductSubImages> productSubImagesList = productSubImagesService.getProductSubImagesByProductId(productId);
                                for (ProductSubImages productSubImages : productSubImagesList) {
                                        fileUtil.deleteFileByUrl(productSubImages.getImageUrl());
                                }
                        }
                        return productSubImagesService.deleteProductSubImagesByProductId(productId)
                                ? ApiResponse.success(ResultEnum.SUCCESS)
                                : ApiResponse.error(ResultEnum.ProductSubImages_DELETE_ERROR);
                }catch (BusinessException e) {
                        return ApiResponse.error(e.getCode());
                }
        }

        @PutMapping("/update")
        public ApiResponse<?> updateProductSubImages(@RequestParam Integer id,@RequestParam Integer sortOrder) {
                try{
                        if(productService.getProductById(id) == null) {
                                throw new BusinessException(ResultEnum.PRODUCT_NOT_FOUND);
                        }
                        return productSubImagesService.updateProductSubImages(id,sortOrder)
                                ? ApiResponse.success(ResultEnum.SUCCESS)
                                : ApiResponse.error(ResultEnum.ProductSubImages_UPDATE_ERROR);
                }catch (BusinessException e) {
                        return ApiResponse.error(e.getCode());
                }
        }


        @GetMapping("/get/{productId}")
        public ApiResponse<?> getProductSubImagesByProductId(@PathVariable Integer productId) {
                try{
                        if(productService.getProductById(productId)==null){
                                throw new BusinessException(ResultEnum.PRODUCT_NOT_FOUND);
                        }
                        return ApiResponse.success(productSubImagesService.getProductSubImagesByProductId(productId));
                }catch (BusinessException e) {
                        return ApiResponse.error(e.getCode());
                }
        }
}
