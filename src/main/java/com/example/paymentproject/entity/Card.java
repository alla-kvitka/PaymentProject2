package com.example.paymentproject.entity;

import com.example.paymentproject.entity.Enums.CardStatus;
import com.example.paymentproject.entity.Enums.UserRequest;
import com.example.paymentproject.entity.Enums.UserStatus;

public class Card {
    private int cardId;
    private int userId;
    private long cardSum;
    private long billId;
    private CardStatus cardStatus;
    private UserStatus userStatus;
    private UserRequest userRequest;

    public Card(int cardId, int userId, long cardSum, long billId,
                CardStatus cardStatus, UserStatus userStatus, UserRequest userRequest) {
        this.cardId = cardId;
        this.userId = userId;
        this.cardSum = cardSum;
        this.billId = billId;
        this.cardStatus = cardStatus;
        this.userStatus = userStatus;
        this.userRequest=userRequest;
    }

    public Card() {
    }


    public UserRequest getUserRequest() {
        return userRequest;
    }

    public void setUserRequest(UserRequest userRequest) {
        this.userRequest = userRequest;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getCardSum() {
        return cardSum;
    }

    public void setCardSum(long cardSum) {
        this.cardSum = cardSum;
    }

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public CardStatus isCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }
}
