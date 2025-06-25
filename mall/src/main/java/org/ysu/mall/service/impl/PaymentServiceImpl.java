package org.ysu.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.ysu.mall.domain.dto.PaymentDto;
import org.ysu.mall.domain.entity.Payment;
import org.ysu.mall.domain.entity.ProductSubImages;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.exception.BusinessException;
import org.ysu.mall.mapper.PaymentMapper;
import org.ysu.mall.service.PaymentService;

import java.util.List;

/**
* @author DELL
* @description 针对表【payment】的数据库操作Service实现
* @createDate 2025-06-17 09:52:36
*/
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {

    private final PaymentMapper paymentMapper;

    public PaymentServiceImpl(PaymentMapper paymentMapper) {
        this.paymentMapper = paymentMapper;
    }

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

    public Payment getPaymentById(String paymentId){
        try{
            return paymentMapper.selectById(paymentId);
        }catch (Exception e){
            throw new BusinessException(ResultEnum.SYSTEM_ERROR,"获取支付信息失败");
        }
    }

    public List<Payment> getPaymentByOrderId(String orderId){
        try{
            LambdaQueryWrapper<Payment> productSubImages=  new LambdaQueryWrapper<Payment>()
                    .eq(Payment::getOrderId, orderId);
            return paymentMapper.selectList(productSubImages);
        }catch (Exception e){
            throw new BusinessException(ResultEnum.SYSTEM_ERROR,"获取支付信息失败");
        }
    }
    public Boolean addPayment(PaymentDto paymentDto){
        try{
            Payment payment = new Payment()
                    .setOrderId(paymentDto.getOrderId())
                    .setAmount(paymentDto.getAmount())
                    .setPlatform(paymentDto.getPlatform())
                    .setStatus(paymentDto.getStatus());
            if(!save(payment)){
                throw new BusinessException(ResultEnum.PAYMENT_ADD_ERROR,"添加支付信息失败");
            }
            return true;
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            throw new BusinessException(ResultEnum.SYSTEM_ERROR,"添加支付信息失败");
        }
    }

    public Boolean deletePayment(String paymentId){
        try{
            if(!removeById(paymentId)){
                throw new BusinessException(ResultEnum.PAYMENT_DELETE_ERROR,"删除支付信息失败");
            }
            return true;
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            throw new BusinessException(ResultEnum.SYSTEM_ERROR,"删除支付信息失败");
        }
    }
    public Boolean deletePaymentByOrderId(String orderId){
        try{
            LambdaQueryChainWrapper<Payment> payment= lambdaQuery().eq(Payment::getOrderId, orderId);
            return paymentMapper.delete(payment) > 0;
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            throw new BusinessException(ResultEnum.SYSTEM_ERROR,"删除支付信息失败");
        }
    }
    public Boolean updatePayment(PaymentDto paymentDto){
        try{
            Payment payment = new Payment()
                    .setOrderId(paymentDto.getOrderId())
                    .setAmount(paymentDto.getAmount())
                    .setPlatform(paymentDto.getPlatform())
                    .setTransactionId(paymentDto.getTransactionId())
                    .setStatus(paymentDto.getStatus());
            if(!updateById(payment)){
                throw new BusinessException(ResultEnum.PAYMENT_UPDATE_ERROR,"更新支付信息失败");
            }
            return true;
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            throw new BusinessException(ResultEnum.SYSTEM_ERROR,"更新支付信息失败");
        }
    }
}
