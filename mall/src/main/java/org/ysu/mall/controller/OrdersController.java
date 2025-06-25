package org.ysu.mall.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.dto.OrdersDto;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.exception.BusinessException;
import org.ysu.mall.service.AddressService;
import org.ysu.mall.service.OrdersService;
import org.ysu.mall.service.UserService;

/**
 * 用户订单管理控制器
 */
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrdersController {
    private final OrdersService ordersService;
    private final UserService userService;
    private final AddressService addressService;

    @GetMapping("/add")
    public ApiResponse<?> addOrder(@RequestBody OrdersDto ordersDto) {
        try {
            if (userService.getUserById(ordersDto.getUserId()) == null) {
                throw new BusinessException(ResultEnum.USER_NOT_FOUND);
            }
            if (addressService.getAddressById(ordersDto.getAddressId()) == null) {
                throw new BusinessException(ResultEnum.ADDRESS_NOT_FOUND);
            }
            return ordersService.addOrder(ordersDto)
                    ? ApiResponse.success(ResultEnum.SUCCESS)
                    : ApiResponse.error(ResultEnum.ORDER_ADD_ERROR);
        } catch (BusinessException e) {
            return ApiResponse.error(e.getCode());
        }
    }

    @DeleteMapping("/delete/{orderId}")
    public ApiResponse<?> deleteOrder(@PathVariable("orderId") String orderId) {
        try {
            return ordersService.deleteOrderById(orderId)
                    ? ApiResponse.success(ResultEnum.SUCCESS)
                    : ApiResponse.error(ResultEnum.ORDER_DELETE_ERROR);
        } catch (BusinessException e) {
            return ApiResponse.error(e.getCode());
        }
    }

    @PutMapping("/update")
    public ApiResponse<?> updateOrder(@RequestBody OrdersDto ordersDto) {
        try {
            return ordersService.updateOrder(ordersDto)
                    ? ApiResponse.success(ResultEnum.SUCCESS)
                    : ApiResponse.error(ResultEnum.ORDER_UPDATE_ERROR);
        } catch (BusinessException e) {
            return ApiResponse.error(e.getCode());
        }
    }
}
