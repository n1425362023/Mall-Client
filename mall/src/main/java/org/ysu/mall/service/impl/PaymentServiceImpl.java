package org.ysu.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.ysu.mall.domain.entity.Payment;
import org.ysu.mall.mapper.PaymentMapper;
import org.ysu.mall.service.PaymentService;

import java.util.List;

/**
* @author DELL
* @description 针对表【payment】的数据库操作Service实现
* @createDate 2025-06-17 09:52:36
*/
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment>
    implements PaymentService {

    @Override
    public List<Payment> getAllPayments() {
        return this.list();
    }

    @Override
    public Payment getByOrderId(Long orderId) {
        return this.lambdaQuery().eq(Payment::getOrderId, orderId).one();
    }

    @Override
    public List<Payment> getByStatus(Integer status) {
        return this.lambdaQuery().eq(Payment::getStatus, status).list();
    }
}
