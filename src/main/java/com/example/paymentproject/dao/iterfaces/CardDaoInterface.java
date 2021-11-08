package com.example.paymentproject.dao.iterfaces;

import com.example.paymentproject.entity.Card;

public interface CardDaoInterface {

    void createCard(Card card);

    Card searchCard();
}
