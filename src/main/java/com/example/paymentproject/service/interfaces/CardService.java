package com.example.paymentproject.service.interfaces;

import com.example.paymentproject.entity.Card;
import com.example.paymentproject.entity.Payment;

import java.sql.SQLException;
import java.util.List;

public interface CardService {
    Card insertCard(Card card) throws SQLException;

    Card searchCardById(int userId);

    Card searchCardByCardId(int cardId);

    void deleteCard(Card card);

    void blockCard(int cardId);

    void unBlockCard(int cardId);

    public List<Card> findAllUserCards(int userid, int page, int size);

    List<Card> findAllCards(int pageNumber, int size);

    void updateBalAfterSubmit (Payment payment);

    void requestToUnblock (int cardId);

   int countOfAllUsersCards();
}
