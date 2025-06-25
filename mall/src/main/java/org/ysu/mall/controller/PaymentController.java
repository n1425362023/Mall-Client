package org.ysu.mall.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.dto.PaymentDto;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.exception.BusinessException;
import org.ysu.mall.service.OrdersService;
import org.ysu.mall.service.PaymentService;
import org.ysu.mall.service.UserService;

/**
 * 支付控制器
 */
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;
    private final OrdersService ordersService;

    @PostMapping("/add")
    public ApiResponse<?> addPayment(@RequestBody PaymentDto paymentDto){
        try{
            if(ordersService.getOrderById(paymentDto.getOrderId())==null){
                throw new BusinessException(ResultEnum.ORDER_NOT_FOUND);
            }
            return paymentService.addPayment(paymentDto)
                    ? ApiResponse.success(ResultEnum.SUCCESS)
                    : ApiResponse.error(ResultEnum.PAYMENT_ADD_ERROR);
        }
        catch (BusinessException e) {
            return ApiResponse.error(e.getCode());
        }
    }

    @DeleteMapping("/delete/{paymentId}")
    public ApiResponse<?> deletePaymentById(@PathVariable("paymentId") String paymentId){
        try{
            if(paymentService.getPaymentById(paymentId)==null){
                throw new BusinessException(ResultEnum.PAYMENT_NOT_FOUND);
            }
            return paymentService.deletePayment(paymentId)
                    ? ApiResponse.success(ResultEnum.SUCCESS)
                    : ApiResponse.error(ResultEnum.PAYMENT_DELETE_ERROR);
        }
        catch (BusinessException e) {
            return ApiResponse.error(e.getCode());
        }
    }

    @DeleteMapping("/delete/{orderId}")
    public ApiResponse<?> deletePayment(@PathVariable("orderId") String orderId){
        try{
            if(ordersService.getOrderById(orderId)==null){
                throw new BusinessException(ResultEnum.ORDER_NOT_FOUND);
            }
            return paymentService.deletePaymentByOrderId(orderId)
                    ? ApiResponse.success(ResultEnum.SUCCESS)
                    : ApiResponse.error(ResultEnum.PAYMENT_DELETE_ERROR);
        }
        catch (BusinessException e) {
            return ApiResponse.error(e.getCode());
        }
    }

    @PutMapping("/update")
    public ApiResponse<?> updatePayment(@RequestBody PaymentDto paymentDto){
        try{
            if(paymentService.getPaymentById(paymentDto.getPaymentId())==null){
                throw new BusinessException(ResultEnum.PAYMENT_NOT_FOUND);
            }
            return paymentService.updatePayment(paymentDto)
                    ? ApiResponse.success(ResultEnum.SUCCESS)
                    : ApiResponse.error(ResultEnum.PRODUCT_UPDATE_ERROR);
        }
        catch (BusinessException e) {
            return ApiResponse.error(e.getCode());
        }
    }

    @GetMapping("/get/{paymentId}")
    public ApiResponse<?> getPaymentById(@PathVariable("paymentId") String paymentId){
        try{
            if(paymentService.getPaymentById(paymentId)==null){
                throw new BusinessException(ResultEnum.PAYMENT_NOT_FOUND);
            }
            return ApiResponse.success(paymentService.getPaymentById(paymentId));
        }
        catch (BusinessException e) {
            return ApiResponse.error(e.getCode());
        }
    }

    @GetMapping("/get/{orderId}")
    public ApiResponse<?> getPaymentByOrderId(@PathVariable("orderId") String orderId){
        try{
            if(ordersService.getOrderById(orderId)==null){
                throw new BusinessException(ResultEnum.ORDER_NOT_FOUND);
            }
            return ApiResponse.success(paymentService.getPaymentByOrderId(orderId));
        }
        catch (BusinessException e) {
            return ApiResponse.error(e.getCode());
        }
    }
}
