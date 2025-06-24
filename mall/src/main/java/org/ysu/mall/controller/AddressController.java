package org.ysu.mall.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.dto.AddressDto;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.exception.BusinessException;
import org.ysu.mall.service.AddressService;

/**
 * 地址管理控制器
 */
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    /**
     * 添加新地址
     * @param addressDto
     * @return
     */
    @PostMapping("/add")
    public ApiResponse<?> addAddress(@Valid @RequestBody AddressDto addressDto) {
        try{
            if(addressService.addAddress(addressDto)){
                return ApiResponse.success(ResultEnum.SUCCESS);
            }else{
                return ApiResponse.error(ResultEnum.ADDRESS_NOT_FOUND);
            }
        }catch (BusinessException e){
            return ApiResponse.error(e.getCode());
        }
    }

    /**
     * 删除地址
     * @param addressId
     * @return
     */
    @DeleteMapping("/delete/{addressId}")
    public ApiResponse<?> deleteAddress(@PathVariable Integer addressId) {
        try {
            if (addressService.deleteAddress(addressId)) {
                return ApiResponse.success(ResultEnum.SUCCESS);
            }else{
                return ApiResponse.error(ResultEnum.ADDRESS_NOT_FOUND);
            }
        } catch (BusinessException e) {
            return ApiResponse.error(e.getCode());
        }
    }

    /**
     * 更新地址
     * @param addressDto
     * @return
     */
    @PutMapping("/update")
    public ApiResponse<?> updateAddress(@Valid @RequestBody AddressDto addressDto) {
        try {
            return ApiResponse.success(addressService.updateAddress(addressDto));
        } catch (BusinessException e) {
            return ApiResponse.error(e.getCode());
        }
    }

    /**
     * 获取用户地址列表
     * @param userId
     * @return
     */
    @GetMapping("/get/{userId}")
    public ApiResponse<?> getAddress(@PathVariable Integer userId) {
        try{
            return ApiResponse.success(addressService.getAddressList(userId));
        }catch (BusinessException e){
            return ApiResponse.error(e.getCode());
        }
    }
}
