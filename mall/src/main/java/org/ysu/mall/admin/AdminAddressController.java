package org.ysu.mall.admin;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.entity.Address;
import org.ysu.mall.service.AddressService;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@Tag(name = "AdminAddressController", description = "收货地址管理")
@RequestMapping("/admin/companyAddress")
public class AdminAddressController {
    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<List<Address>> list() {
        List<Address> companyAddressList = addressService.getAllAddresses();
        return ApiResponse.success(companyAddressList);
    }
}
