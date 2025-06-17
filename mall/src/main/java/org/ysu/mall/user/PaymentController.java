package org.ysu.mall.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.entity.Address;
import org.ysu.mall.domain.entity.Payment;
import org.ysu.mall.service.AddressService;
import org.ysu.mall.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/user/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    // 查询所有支付记录
    @GetMapping("/list")
    public ApiResponse<List<Payment>> listPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return ApiResponse.success(payments);
    }

    // 根据支付ID获取支付详情
    @GetMapping("/{id}")
    public ApiResponse<Payment> getPayment(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment == null) {
            return ApiResponse.fail("支付记录不存在");
        }
        return ApiResponse.success(payment);
    }

    // 创建支付记录
    @PostMapping("/create")
    public ApiResponse<Void> createPayment(@RequestBody Payment payment) {
        boolean result = paymentService.createPayment(payment);
        if (result) {
            return ApiResponse.success();
        } else {
            return ApiResponse.fail("创建支付记录失败");
        }
    }

    // 更新支付记录
    @PutMapping("/update")
    public ApiResponse<Void> updatePayment(@RequestBody Payment payment) {
        boolean result = paymentService.updatePayment(payment);
        if (result) {
            return ApiResponse.success();
        } else {
            return ApiResponse.fail("更新支付记录失败");
        }
    }

    // 删除支付记录
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deletePayment(@PathVariable Long id) {
        boolean result = paymentService.deletePayment(id);
        if (result) {
            return ApiResponse.success();
        } else {
            return ApiResponse.fail("删除支付记录失败");
        }
    }
}
