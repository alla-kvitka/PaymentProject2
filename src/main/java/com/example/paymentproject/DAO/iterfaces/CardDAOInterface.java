package com.example.paymentproject.DAO.iterfaces;

import com.example.paymentproject.entity.Card;

public interface CardDAOInterface {

    void createCard(Card card);

    Card searchCard();
}
