package org.ysu.mall.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.dto.*;
import org.ysu.mall.domain.entity.Orders;
import org.ysu.mall.enums.OrderEnum;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.service.OrdersService;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@Tag(name = "AdminOrdersController", description = "管理员订单管理")
@RequestMapping("/admin/orders")
public class AdminOrdersController {
    @Autowired
    private OrdersService ordersService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @Operation(summary = "获取所有订单信息", description = "获取所有订单信息")
    public ApiResponse<List<Orders>> list(@RequestBody OrdersDto ordersDto) {
        List<Orders> orderList = ordersService.listOrders(ordersDto);
        return ApiResponse.success(orderList);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    @Operation(summary = "添加订单", description = "管理员添加订单")
    public ApiResponse<?> addOrder(@RequestBody OrdersDto ordersDto) {
        boolean isAdded = ordersService.addOrder(ordersDto);
        if (isAdded) {
            return ApiResponse.success("订单添加成功");
        }
        return ApiResponse.error(ResultEnum.SYSTEM_ERROR);
    }

    @RequestMapping(value = "/list/returns", method = RequestMethod.GET)
    @ResponseBody
    @Operation(summary = "查看所有退货中的订单", description = "管理员查看所有退货中的订单")
    public ApiResponse<List<Orders>> listReturns() {
        List<Orders> returnOrders = ordersService.listOrdersByStatus(OrderEnum.RETURN.getCode());
        return ApiResponse.success(returnOrders);
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
        int count = ordersService.updateNote(id, note, status != null ? OrderEnum.fromCode(status) : null);
        if (count > 0) {
            return ApiResponse.success(count);
        }
        return ApiResponse.error(org.ysu.mall.enums.ResultEnum.SYSTEM_ERROR);
    }


}
