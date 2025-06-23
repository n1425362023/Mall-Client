package org.ysu.mall.admin;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.dto.OmsMoneyInfoParam;
import org.ysu.mall.domain.dto.OmsOrderDetail;
import org.ysu.mall.domain.dto.OmsReceiverInfoParam;
import org.ysu.mall.domain.dto.OrderDeliveryParam;
import org.ysu.mall.domain.entity.Orders;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.service.OrdersService;

import java.util.List;

@RestController
@Tag(name = "AdminOrdersController", description = "订单管理")
@RequestMapping("/order")
public class AdminOrdersController {
    @Autowired
    private OrdersService ordersService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<List<Orders>> list() {
        List<Orders> orderList = ordersService.list();
        return ApiResponse.success(orderList);
    }

    @RequestMapping(value = "/update/delivery", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<?> delivery(@RequestBody List<OrderDeliveryParam> deliveryParamList) {
        int count = ordersService.delivery(deliveryParamList);
        if (count > 0) {
            return ApiResponse.success(count);
        }
        return ApiResponse.error(ResultEnum.SYSTEM_ERROR);
    }


    @RequestMapping(value = "/update/close", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<?> close(@RequestParam("ids") List<Long> ids, @RequestParam String note) {
        int count = ordersService.close(ids, note);
        if (count > 0) {
            return ApiResponse.success(count);
        }
        return ApiResponse.error(ResultEnum.SYSTEM_ERROR);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<?> delete(@RequestParam("ids") List<Long> ids) {
        int count = ordersService.delete(ids);
        if (count > 0) {
            return ApiResponse.success(count);
        }
        return ApiResponse.error(ResultEnum.SYSTEM_ERROR);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<?> detail(@PathVariable Long id) {
        OmsOrderDetail orderDetailResult = ordersService.detail(id);
        return ApiResponse.success(orderDetailResult);
    }

    @RequestMapping(value = "/update/receiverInfo", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<?> updateReceiverInfo(@RequestBody OmsReceiverInfoParam receiverInfoParam) {
        int count = ordersService.updateReceiverInfo(receiverInfoParam);
        if (count > 0) {
            return ApiResponse.success(count);
        }
        return ApiResponse.error(ResultEnum.SYSTEM_ERROR);
    }

    @RequestMapping(value = "/update/moneyInfo", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<?> updateReceiverInfo(@RequestBody OmsMoneyInfoParam moneyInfoParam) {
        int count = ordersService.updateMoneyInfo(moneyInfoParam);
        if (count > 0) {
            return ApiResponse.success(count);
        }
        return ApiResponse.error(ResultEnum.SYSTEM_ERROR);
    }

    @RequestMapping(value = "/update/note", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<?> updateNote(@RequestParam("id") Long id,
                                   @RequestParam("note") String note,
                                   @RequestParam("status") Integer status) {
        int count = ordersService.updateNote(id, note, status);
        if (count > 0) {
            return ApiResponse.success(count);
        }
        return ApiResponse.error(org.ysu.mall.enums.ResultEnum.SYSTEM_ERROR);
    }
}

