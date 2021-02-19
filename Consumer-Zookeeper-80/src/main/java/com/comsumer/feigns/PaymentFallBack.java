package com.comsumer.feigns;

import common.data.Result;
import common.entities.Payment;
import org.springframework.stereotype.Component;



@Component
public class PaymentFallBack implements PaymentFeign {
    @Override
    public Result insertPayment(Payment payment) {
        return new Result(400,"timeout").data("PaymentFeign,insertPayment(),payment-9001","超时");
    }

    @Override
    public Result getPaymentById(int id) {
        return new Result(400,"timeout").data("PaymentFeign,getPaymentById(),payment-9001","超时");
    }
}
