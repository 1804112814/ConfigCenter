package com.comsumer.feigns;

import common.data.Result;
import common.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Component
@FeignClient(value = "payment-9001",fallback = PaymentFallBack.class)
public interface PaymentFeign {
    @PostMapping("/payment/insertPayment")
    Result insertPayment(@RequestBody Payment payment);

    @GetMapping("/payment/getPaymentById/{id}")
    Result getPaymentById(@PathVariable("id") int id);
}
