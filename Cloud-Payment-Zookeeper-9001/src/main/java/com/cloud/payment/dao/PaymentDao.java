package com.cloud.payment.dao;

import common.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PaymentDao {
    int insertPayment(@Param("pay") Payment payment);

    Payment getPaymentById(@Param("id") int id);
}
