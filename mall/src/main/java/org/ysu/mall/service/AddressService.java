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
    Boolean addAddress(AddressDto addressDto);

    Boolean deleteAddress(Integer addressId);

    Address updateAddress(AddressDto addressDto);

    List<Address> getAddressList(Integer addressId);

    List<Address> getAllAddresses();

    Address getAddressById(Integer addressId);
}
