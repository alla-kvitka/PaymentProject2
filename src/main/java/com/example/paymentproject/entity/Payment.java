package com.example.paymentproject.entity;

import com.example.paymentproject.entity.Enums.CardStatus;
import com.example.paymentproject.entity.Enums.TransactionType;

public class Payment {
    private int userId;
    private int paymentSum;
    private int paymentId;
    private int cardId;
    private long billId;
    private TransactionType transactionType;

    public Payment() {
    }

    public Payment(int paymentSum, int paymentId, int cardId, long billId,
                   TransactionType transactionType) {
        this.paymentSum = paymentSum;
        this.paymentId = paymentId;
        this.cardId = cardId;
        this.billId = billId;
        this.transactionType = transactionType;
    }

    public static Payment createPayment(Card card, TransactionType transactionType,
                                        int sum) {
        Payment payment = new Payment();
        payment.setUserId(card.getUserId());
        payment.setCardId(card.getCardId());
        payment.setPaymentSum(sum);
        payment.setTransactionType(transactionType);
        payment.setBillId(card.getBillId());
        return payment;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(int paymentSum) {
        this.paymentSum = paymentSum;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
