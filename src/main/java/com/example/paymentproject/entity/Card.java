package com.example.paymentproject.entity;

import com.example.paymentproject.entity.Enums.CardStatus;
import com.example.paymentproject.entity.Enums.UserStatus;

public class Card {
    private int cardId;
    private int userId;
    private long cardSum;
    private long billId;
    private CardStatus cardStatus;
    private UserStatus userStatus;

    public Card(int cardId, int userId, long cardSum, long billId, CardStatus cardStatus, UserStatus userStatus) {
        this.cardId = cardId;
        this.userId = userId;
        this.cardSum = cardSum;
        this.billId = billId;
        this.cardStatus = cardStatus;
        this.userStatus = userStatus;

    }

    public Card() {
    }

    public static Card createCard(User user) {
        Card card = new Card();
        card.setUserId(user.getUserId());
        card.setCardSum(4000);
        card.setCardStatus(CardStatus.ACTIVE);
        card.setBillId(user.getUserBill());
        card.setUserStatus(user.getUserStatus());
        return card;
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
