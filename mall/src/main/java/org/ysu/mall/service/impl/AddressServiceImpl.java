package org.ysu.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ysu.mall.domain.dto.AddressDto;
import org.ysu.mall.domain.entity.Address;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.exception.BusinessException;
import org.ysu.mall.mapper.AddressMapper;
import org.ysu.mall.service.AddressService;

import java.util.List;

/**
* @author DELL
* @description 针对表【address】的数据库操作Service实现
* @createDate 2025-06-17 10:22:27
*/
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
@Slf4j
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    private  final  AddressMapper addressMapper;


    public Boolean addAddress(AddressDto addressDto) {
        try{
            Address address = new Address()
                            .setUserId(addressDto.getUserId())
                            .setReceiver(addressDto.getReceiver())
                            .setPhone(addressDto.getPhone())
                            .setRegion(addressDto.getRegion())
                            .setDetail(addressDto.getDetail())
                            .setIsDefault(addressDto.getIsDefault());
            if(!save(address)){
                throw new BusinessException(ResultEnum.ADDRESS_ADD_ERROR);
            }
            return true;
        }catch (BusinessException e){
            throw e;
        }catch (Exception e) {
            throw new BusinessException(ResultEnum.SYSTEM_ERROR,"添加地址失败");
        }
    }


    public Boolean deleteAddress(Integer addressId){
        try{
            if(!removeById(addressId)){
                throw new BusinessException(ResultEnum.ADDRESS_DELETE_ERROR);
            }
            return true;
        }catch (BusinessException e){
            throw e;
        }catch (Exception e) {
            throw new BusinessException(ResultEnum.SYSTEM_ERROR,"删除地址失败");
        }
    }


    public Address updateAddress(AddressDto addressDto){
        try{
            Address address = lambdaQuery().eq(Address::getAddressId,addressDto.getAddressId()).one();
            if(address == null) {
                throw new BusinessException(ResultEnum.ADDRESS_NOT_FOUND);
            }

            if(!updateById(address.setReceiver(addressDto.getReceiver())
                                .setPhone(addressDto.getPhone())
                                .setRegion(addressDto.getRegion())
                                .setDetail(addressDto.getDetail())
                                .setIsDefault(addressDto.getIsDefault()))){
                throw new BusinessException(ResultEnum.ADDRESS_UPDATE_ERROR);
            }
            return address;
        }catch (BusinessException e){
            throw e;
        }catch (Exception e) {
            throw new BusinessException(ResultEnum.SYSTEM_ERROR,"更新地址失败");
        }

    }

    public List<Address> getAddressList(Integer userId){
        try{
            LambdaQueryWrapper<Address> address=  new LambdaQueryWrapper<Address>()
                    .eq(Address::getUserId, userId);
            return addressMapper.selectList(address);
        }catch (Exception e){
            throw new BusinessException(ResultEnum.SYSTEM_ERROR,"获取地址失败");
        }
    }

}




