package org.ysu.mall.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.entity.Payment;
import org.ysu.mall.service.PaymentService;

import java.util.List;

/**
 * 管理员支付记录管理控制器
 */
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/payment")
public class AdminPaymentController {
    @Autowired
    private PaymentService paymentService;

    /**
     * 查询所有支付记录
     * @return
     */
    @GetMapping("/list")
    public ApiResponse<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return ApiResponse.success(payments);
    }

    /**
     * 根据支付ID获取支付记录
     * @param orderId
     * @return
     */
    @GetMapping("/byOrder/{orderId}")
    public ApiResponse<Payment> getPaymentByOrderId(@PathVariable String orderId) {
        Payment payment = paymentService.getByOrderId(orderId);
        return ApiResponse.success(payment);
    }

    /**
     * 根据支付状态获取支付记录
     * @param status
     * @return
     */
    @GetMapping("/byStatus")
    public ApiResponse<List<Payment>> getPaymentsByStatus(@RequestParam String status) {
        List<Payment> payments = paymentService.getByStatus(status);
        return ApiResponse.success(payments);
    }
}
