package org.ysu.mall.admin;

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

/**
 * 管理员订单管理控制器
 */
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/orders")
public class AdminOrdersController {
    @Autowired
    private OrdersService ordersService;

    /**
     * 查询订单列表
     * @param ordersDto
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<List<Orders>> list(@RequestBody OrdersDto ordersDto) {
        List<Orders> orderList = ordersService.listOrders(ordersDto);
        return ApiResponse.success(orderList);
    }

    /**
     * 根据订单状态查询订单列表
     * @param status
     * @return
     */
    @RequestMapping(value = "/list/status", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<List<Orders>> listByStatus(@RequestParam("status") String status) {
        List<Orders> orderList = ordersService.listOrdersByStatus(status);
        return ApiResponse.success(orderList);
    }

    /**
     * 添加新订单
     * @param ordersDto
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<?> addOrder(@RequestBody OrdersDto ordersDto) {
        boolean isAdded = ordersService.addOrder(ordersDto);
        if (isAdded) {
            return ApiResponse.success("订单添加成功");
        }
        return ApiResponse.error(ResultEnum.SYSTEM_ERROR);
    }

    /**
     * 查询退货订单列表
     * @return
     */
    @RequestMapping(value = "/list/returns", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<?> listReturns() {
        List<Orders> returnOrders = ordersService.listOrdersByStatus(OrderEnum.RETURN.getCode());
        if(returnOrders.isEmpty()) {
            return ApiResponse.error(ResultEnum.SYSTEM_ERROR, "没有退货订单");
        }
        return ApiResponse.success(returnOrders);
    }

    /**
     * 批量发货
     * @param deliveryParamList
     * @return
     */
    @RequestMapping(value = "/update/delivery", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<?> delivery(@RequestBody List<OrderDeliveryParam> deliveryParamList) {
        int count = ordersService.delivery(deliveryParamList);
        if (count > 0) {
            return ApiResponse.success(count);
        }
        return ApiResponse.error(ResultEnum.SYSTEM_ERROR, "没有订单需要发货");
    }

    /**
     * 批量关闭订单
     * @param request
     * @return
     */
    @RequestMapping(value = "/update/close", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<?> close(@RequestBody BatchOrderDto request) {
        int count = ordersService.close(request.getIds(), request.getNote());
        if (count > 0) {
            return ApiResponse.success(count);
        }
        return ApiResponse.error(ResultEnum.SYSTEM_ERROR);
    }

    /**
     * 批量删除订单
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<?> delete(@RequestBody BatchOrderDto request) {
        int count = ordersService.delete(request.getIds());
        if (count > 0) {
            return ApiResponse.success(count);
        }
        return ApiResponse.error(ResultEnum.SYSTEM_ERROR);
    }

    /**
     * 更新收货信息
     * @param receiverInfoParam
     * @return
     */
    @RequestMapping(value = "/update/receiverInfo", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<?> updateReceiverInfo(@RequestBody OmsReceiverInfoParam receiverInfoParam) {
        int count = ordersService.updateReceiverInfo(receiverInfoParam);
        if (count > 0) {
            return ApiResponse.success(count);
        }
        return ApiResponse.error(ResultEnum.SYSTEM_ERROR);
    }

    /**
     * 更新支付信息
     * @param moneyInfoParam
     * @return
     */
    @RequestMapping(value = "/update/moneyInfo", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<?> updateReceiverInfo(@RequestBody OmsMoneyInfoParam moneyInfoParam) {
        int count = ordersService.updateMoneyInfo(moneyInfoParam);
        if (count > 0) {
            return ApiResponse.success(count);
        }
        return ApiResponse.error(ResultEnum.SYSTEM_ERROR);
    }

    /**
     * 更新订单备注
     * @param request
     * @return
     */
    @PostMapping("/update/note")
    @ResponseBody
    public ApiResponse<?> updateNote(@RequestBody UpdateOrderNoteDto request) {
        int count = ordersService.updateNote(
                request.getId(),
                request.getNote(),
                request.getStatus() != null ? OrderEnum.fromCode(request.getStatus()) : null
        );
        if (count > 0) {
            return ApiResponse.success(count);
        }
        return ApiResponse.error(org.ysu.mall.enums.ResultEnum.SYSTEM_ERROR);
    }


}
