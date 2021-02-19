package com.comsumer.controller;

import com.comsumer.feigns.PaymentFeign;
import common.data.Result;
import common.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class Controller {

    String uri = "http://payment-9001";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PaymentFeign paymentFeign;

    @GetMapping("/get/{id}")
    public Result getPayment(@PathVariable("id") int id) {
        Result result = restTemplate.getForObject(uri + "/payment/getPaymentById/"+id, Result.class);
        System.out.println("get....");
        return result;
    }

    @GetMapping("/insert")
    public Result insertPayment(Payment payment) {
        Result result = restTemplate.postForObject(uri + "/payment/insertPayment", payment, Result.class);
        System.out.println("post...");
        return result;
    }


    @GetMapping("/get1/{id}")
    public Result get1(@PathVariable("id") int id) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return paymentFeign.getPaymentById(id);
    }

    @GetMapping("/insert1")
    public Result insert1(Payment payment) {
        return paymentFeign.insertPayment(payment);
    }
}
