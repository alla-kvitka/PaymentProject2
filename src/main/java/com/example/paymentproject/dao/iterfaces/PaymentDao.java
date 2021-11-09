package com.example.paymentproject.dao.iterfaces;

import com.example.paymentproject.entity.Payment;

import java.sql.SQLException;

public interface PaymentDao {

     Payment insertPayment(Payment payment) throws SQLException;
}
