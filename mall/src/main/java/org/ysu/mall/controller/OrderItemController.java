package org.ysu.mall.controller;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.dto.OrderItemDto;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.exception.BusinessException;
import org.ysu.mall.service.OrderItemService;
import org.ysu.mall.service.OrdersService;
import org.ysu.mall.service.ProductService;

/**
 * 用户订单项管理控制器
 */
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/orderItem")
public class OrderItemController {
    private final OrderItemService orderItemService;
    private final ProductService productService;
    private final OrdersService ordersService;
    @PostMapping("/add")
    public ApiResponse<?> addOrderItem(@RequestBody OrderItemDto orderItemDto) {
        try{
            if(productService.getProductById(orderItemDto.getProductId()) == null){
                throw new BusinessException(ResultEnum.PRODUCT_NOT_FOUND);
            }
            if(ordersService.getOrderById(orderItemDto.getOrderId()) == null) {
                throw new BusinessException(ResultEnum.ORDER_NOT_FOUND);
            }
            return orderItemService.addOrderItem(orderItemDto)
                    ?ApiResponse.success(ResultEnum.SUCCESS)
                    :ApiResponse.error(ResultEnum.ORDER_ITEM_ADD_ERROR);
        } catch (BusinessException e) {
            return ApiResponse.error(e.getCode());
        }
    }

    @DeleteMapping("/delete/{orderId}")
    public ApiResponse<?> deleteOrderItem(@PathVariable("orderId") String orderId) {
        try{
            return orderItemService.deleteOrderItemsByOrderId(orderId)
                    ?ApiResponse.success(ResultEnum.SUCCESS)
                    :ApiResponse.error(ResultEnum.ORDER_ITEM_DELETE_ERROR);
        } catch (BusinessException e) {
            return ApiResponse.error(e.getCode());
        }
    }

    @DeleteMapping("/delete/{itemId}")
    public ApiResponse<?> deleteOrderItemById(@PathVariable("itemId") Long itemId) {
        try{
            return orderItemService.deleteOrderItemById(itemId)
                    ?ApiResponse.success(ResultEnum.SUCCESS)
                    :ApiResponse.error(ResultEnum.ORDER_ITEM_DELETE_ERROR);
        } catch (BusinessException e) {
            return ApiResponse.error(e.getCode());
        }
    }

    @PutMapping("/update")
    public ApiResponse<?> updateOrderItem(@RequestBody OrderItemDto orderItemDto) {
        try{
            return orderItemService.updateOrderItem(orderItemDto)
                    ?ApiResponse.success(ResultEnum.SUCCESS)
                    :ApiResponse.error(ResultEnum.ORDER_ITEM_UPDATE_ERROR);
        } catch (BusinessException e) {
            return ApiResponse.error(e.getCode());
        }
    }

    @GetMapping("/get/{orderId}")
    public ApiResponse<?> getOrderItemByOrderId(@PathVariable("orderId") String orderId) {
        try{
            return ApiResponse.success(orderItemService.getOrderItemsByOrderId(orderId));
        } catch (BusinessException e) {
            return ApiResponse.error(e.getCode());
        }
    }

}
