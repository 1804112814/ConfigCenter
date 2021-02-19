package com.cloud.payment.controller;

import com.cloud.payment.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import common.data.Result;
import common.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@DefaultProperties(defaultFallback = "timeFallBack",commandProperties = {
                @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),  // 是否开启断路器
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),  //请求次数
                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
                @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
        })

@RequestMapping("/payment")
@RestController
public class PaymentController
{
    @Autowired
    PaymentService paymentService;


    @GetMapping("/a")
    public int getPayment ()
    {
        return 1;

    }

    @PostMapping("/insertPayment")
    public Result insertPayment(@RequestBody Payment payment) {
        System.out.println("@@@"+payment.getId());
        int i = paymentService.insertPayment(payment);
        return new Result(200,"success").data("result",i);
    }

    @HystrixCommand
    @GetMapping("/getPaymentById/{id}")
    public Result getPaymentById(@PathVariable("id") int id) {
        if (id < 0) {
            int age = 10/0;
        }
        Payment payment = paymentService.getPaymentById(id);
        return new Result(200,"success").data("data",payment);
    }

//    @HystrixCommand(fallbackMethod = "timeFallBack",commandProperties = {
//        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
//    })
    @HystrixCommand
    @GetMapping("/time/{id}")
    public Result time(@PathVariable("id") int id) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Result(200,"success").data("data","(●'◡'●)");
    }

    public Result timeFallBack(){
        return new Result(400,"waiting").data("data","PaymentController-time()服务超时");
    }

}
