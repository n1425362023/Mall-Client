package org.ysu.mall.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.entity.Payment;
import org.ysu.mall.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/admin/payment")
public class AdminPaymentController {
    @Autowired
    private PaymentService paymentService;

    /**
     * 查询所有支付记录
     */
    @GetMapping
    public ApiResponse<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return ApiResponse.success(payments);
    }

    /**
     * 根据订单ID查询支付记录
     */
    @GetMapping("/byOrder/{orderId}")
    public ApiResponse<Payment> getPaymentByOrderId(@PathVariable Long orderId) {
        Payment payment = paymentService.getByOrderId(orderId);
        return ApiResponse.success(payment);
    }

    /**
     * 根据支付状态筛选
     */
    @GetMapping("/byStatus")
    public ApiResponse<List<Payment>> getPaymentsByStatus(@RequestParam String status) {
        List<Payment> payments = paymentService.getByStatus(Integer.valueOf(status));
        return ApiResponse.success(payments);
    }
}
