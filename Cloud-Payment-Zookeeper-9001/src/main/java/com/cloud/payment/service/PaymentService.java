package com.cloud.payment.service;

import common.entities.Payment;


public interface PaymentService {
    int insertPayment(Payment payment);

    Payment getPaymentById(int id);
}
