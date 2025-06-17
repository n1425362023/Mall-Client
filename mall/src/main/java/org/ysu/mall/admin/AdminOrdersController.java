package org.ysu.mall.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.entity.Orders;
import org.ysu.mall.service.OrdersService;

import java.util.List;

@RestController
@RequestMapping("/admin/order")
public class AdminOrdersController {
    @Autowired
    private OrdersService ordersService;

    /**
     * 获取所有订单（可分页、筛选）
     */
    @GetMapping
    public ApiResponse<List<Orders>> listOrders(
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<Orders> orders = ordersService.getOrders(status, page, size);
        return ApiResponse.success(orders);
    }

    /**
     * 获取订单详情
     */
    @GetMapping("/{orderId}")
    public ApiResponse<Orders> getOrderDetail(@PathVariable Long orderId) {
        Orders order = ordersService.getOrderById(orderId);
        return ApiResponse.success(order);
    }

    /**
     * 修改订单状态（如发货、关闭等）
     */
    @PutMapping("/{orderId}/status")
    public ApiResponse<Void> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam String status
    ) {
        ordersService.updateOrderStatus(orderId, status);
        return ApiResponse.success();
    }

    /**
     * 删除订单（逻辑删除）
     */
    @DeleteMapping("/{orderId}")
    public ApiResponse<Void> deleteOrder(@PathVariable Long orderId) {
        ordersService.deleteOrder(orderId);
        return ApiResponse.success();
    }
}
