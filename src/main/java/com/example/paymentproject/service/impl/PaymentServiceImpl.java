package com.example.paymentproject.service.impl;

import com.example.paymentproject.dao.impl.PaymentDaoImpl;
import com.example.paymentproject.entity.Card;
import com.example.paymentproject.entity.Payment;
import com.example.paymentproject.entity.Transaction;
import com.example.paymentproject.service.interfaces.PaymentService;

import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    PaymentDaoImpl paymentDao = new PaymentDaoImpl();

    public Payment createPayment(Card card, String transactionType,
                                 int sum) {
        Payment payment = new Payment();
        payment.setUserId(card.getUserId());
        payment.setCardId(card.getCardId());
        payment.setPaymentSum(sum);
        payment.setTransactionType(transactionType);
        payment.setBillId(card.getBillId());
        payment.setStatus(0);
        return payment;
    }

    @Override
    public Payment insertPayment(Payment payment) {
        return paymentDao.insertPayment(payment);
    }

    @Override
    public void submitAllPaymentsForUser(int userId) {
        paymentDao.submitAllPaymentsForUser(userId);
    }

    @Override
    public void addToHistory(Payment payment) {
        paymentDao.addToHistory(payment);
    }

    @Override
    public void submitPayment(Payment payment) {
        paymentDao.submitPayment(payment);
    }

    @Override
    public List<Payment> searchAllCreatedPayments(int userId) {
        return paymentDao.searchAllCreatedPayments(userId);
    }

    @Override
    public List<Transaction> searchAllUserTransaction(int pageNumber, int size, int userId) {
        return paymentDao.searchAllUserTransaction(pageNumber, size, userId);
    }

    public int getNoOfRecords(int userId) {
        return paymentDao.countOfAllUserTransactions(userId);
    }
}