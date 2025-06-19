package org.ysu.mall.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.dto.AddressDto;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.exception.BusinessException;
import org.ysu.mall.service.AddressService;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    @PostMapping("/add")
    public ApiResponse<?> addAddress(@Valid @RequestBody AddressDto addressDto) {
        try{
            return ApiResponse.success(addressService.addAddress(addressDto));
        }catch (BusinessException e){
            return ApiResponse.error(e.getCode());

        }
    }

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

    @PutMapping("/update")
    public ApiResponse<?> updateAddress(@Valid @RequestBody AddressDto addressDto) {
        try {
            return ApiResponse.success(addressService.updateAddress(addressDto));
        } catch (BusinessException e) {
            return ApiResponse.error(e.getCode());
        }
    }

    @GetMapping("/get/{userId}")
    public ApiResponse<?> getAddress(@PathVariable Integer userId) {
        try{
            return ApiResponse.success(addressService.getAddress(userId));
        }catch (BusinessException e){
            return ApiResponse.error(e.getCode());
        }
    }
}
