package org.ysu.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ysu.mall.domain.dto.PaymentDto;
import org.ysu.mall.domain.entity.Payment;

import java.util.List;

/**
* @author DELL
* @description 针对表【payment】的数据库操作Service
* @createDate 2025-06-17 09:52:36
*/
public interface PaymentService extends IService<Payment> {
    List<Payment> getAllPayments();

    Payment getByOrderId(String orderId);

    List<Payment> getByStatus(String status);

    Payment getPaymentById(String paymentId);

    List<Payment> getPaymentByOrderId(String orderId);

    Boolean addPayment(PaymentDto paymentDto);

    Boolean deletePayment(String paymentId);

    Boolean deletePaymentByOrderId(String orderId);

    Boolean updatePayment(PaymentDto paymentDto);
}
