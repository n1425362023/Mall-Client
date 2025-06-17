package org.ysu.mall.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.entity.Address;
import org.ysu.mall.domain.entity.Orders;
import org.ysu.mall.service.AddressService;
import org.ysu.mall.service.OrdersService;

import java.util.List;

@RestController
@RequestMapping("/user/order")
public class OrdersController {
    @Autowired
    private OrdersService orderService;

    // 查询所有订单
    @GetMapping("/list")
    public ApiResponse<List<Orders>> listOrders() {
        List<Orders> orders = orderService.getAllOrders();
        return ApiResponse.success(orders);
    }

    // 根据订单ID获取订单详情
    @GetMapping("/{id}")
    public ApiResponse<Orders> getOrder(@PathVariable Long id) {
        Orders order = orderService.getOrderById(id);
        if (order == null) {
            return ApiResponse.fail("订单不存在");
        }
        return ApiResponse.success(order);
    }

    // 创建订单
    @PostMapping("/create")
    public ApiResponse<Void> createOrder(@RequestBody Orders order) {
        boolean result = orderService.createOrder(order);
        if (result) {
            return ApiResponse.success();
        } else {
            return ApiResponse.fail("创建订单失败");
        }
    }

    // 更新订单
    @PutMapping("/update")
    public ApiResponse<Void> updateOrder(@RequestBody Orders order) {
        boolean result = orderService.updateOrder(order);
        if (result) {
            return ApiResponse.success();
        } else {
            return ApiResponse.fail("更新订单失败");
        }
    }

    // 删除订单
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteOrder(@PathVariable Long id) {
        boolean result = orderService.deleteOrder(id);
        if (result) {
            return ApiResponse.success();
        } else {
            return ApiResponse.fail("删除订单失败");
        }
    }
}
