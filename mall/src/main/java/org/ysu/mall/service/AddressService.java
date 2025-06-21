package org.ysu.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ysu.mall.domain.dto.AddressDto;
import org.ysu.mall.domain.entity.Address;

import java.util.List;

/**
* @author DELL
* @description 针对表【address】的数据库操作Service
* @createDate 2025-06-17 10:22:27
*/
public interface AddressService extends IService<Address> {
    public Boolean addAddress(AddressDto addressDto);

    public Boolean deleteAddress(Integer addressId);

    public Address updateAddress(AddressDto addressDto);

    public List<Address> getAddressList(Integer addressId);
}
