package org.ysu.mall.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.entity.Address;
import org.ysu.mall.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("/user/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    // 获取当前用户的所有地址列表
    @GetMapping("/user/{userId}")
    public ApiResponse<List<Address>> listByUserId(@PathVariable Long userId) {
        List<Address> addresses = addressService.getByUserId(userId);
        return ApiResponse.success(addresses);
    }

    // 根据ID查询地址
    @GetMapping("/{id}")
    public ApiResponse<Address> getById(@PathVariable Long id) {
        Address address = addressService.getById(id);
        if (address != null) {
            return ApiResponse.success(address);
        } else {
            return ApiResponse.fail(404, "地址不存在");
        }
    }

    // 新增地址
    @PostMapping
    public ApiResponse<Void> addAddress(@RequestBody Address address) {
        boolean saved = addressService.save(address);
        if (saved) {
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(400, "添加地址失败");
        }
    }

    // 更新地址
    @PutMapping("/{id}")
    public ApiResponse<Void> updateAddress(@PathVariable Long id, @RequestBody Address address) {
        address.setId(id);
        boolean updated = addressService.updateById(address);
        if (updated) {
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(400, "更新地址失败");
        }
    }

    // 删除地址
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteAddress(@PathVariable Long id) {
        boolean deleted = addressService.removeById(id);
        if (deleted) {
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(400, "删除地址失败");
        }
    }
}
