package com.example.paymentproject.dao.iterfaces;

import com.example.paymentproject.entity.Card;

import java.sql.SQLException;

public interface CardDao {

    Card insertCard(Card card) throws SQLException;

    Card searchUserCards(long user_id);


}
