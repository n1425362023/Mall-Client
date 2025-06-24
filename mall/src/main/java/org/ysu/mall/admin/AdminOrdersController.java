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
     * 查询待发货订单列表
     * @return
     */
    @RequestMapping(value = "/list/returns", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<List<Orders>> listReturns() {
        List<Orders> returnOrders = ordersService.listOrdersByStatus(OrderEnum.RETURN.getCode());
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
        return ApiResponse.error(ResultEnum.SYSTEM_ERROR);
    }

    /**
     * 批量关闭订单
     * @param ids
     * @param note
     * @return
     */
    @RequestMapping(value = "/update/close", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<?> close(@RequestParam("ids") List<Long> ids, @RequestParam String note) {
        int count = ordersService.close(ids, note);
        if (count > 0) {
            return ApiResponse.success(count);
        }
        return ApiResponse.error(ResultEnum.SYSTEM_ERROR);
    }

    /**
     * 批量删除订单
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<?> delete(@RequestParam("ids") List<Long> ids) {
        int count = ordersService.delete(ids);
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
     * @param id
     * @param note
     * @param status
     * @return
     */
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
