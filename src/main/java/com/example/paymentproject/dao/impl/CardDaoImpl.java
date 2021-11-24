package com.example.paymentproject.dao.impl;

import com.example.paymentproject.dao.iterfaces.CardDao;
import com.example.paymentproject.entity.Card;
import com.example.paymentproject.entity.Enums.CardStatus;
import com.example.paymentproject.entity.Enums.UserRequest;
import com.example.paymentproject.entity.Enums.UserStatus;
import com.example.paymentproject.entity.Payment;
import com.example.paymentproject.utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardDaoImpl implements CardDao {
    @Override
    public Card insertCard(Card card) {
        int random = Utils.randomInt();
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement
                     ("INSERT INTO CARDS VALUES (?, ?, ?, ?,?,?,?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            card.setCardId(random);
            pstmt.setInt(1, card.getCardId());
            pstmt.setInt(2, card.getUserId());
            pstmt.setLong(3, card.getCardSum());
            pstmt.setLong(4, card.getBillId());
            pstmt.setString(5, String.valueOf(card.isCardStatus()));
            pstmt.setString(6, String.valueOf(card.getUserStatus()));
            pstmt.setString(7, String.valueOf(card.getUserRequest()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return card;
    }

    public void requestToUnblock (int cardId) {
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement
                     ("UPDATE CARDS SET user_request=? WHERE card_id=?")) {
            pstmt.setString(1, "UNBLOCK_CARD");
            pstmt.setLong(2, cardId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cleanRequestToUnblock (int cardId) {
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement
                     ("UPDATE CARDS SET user_request=? WHERE card_id=?")) {
            pstmt.setString(1, "NO_REQUEST");
            pstmt.setLong(2, cardId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Card searchCardById(int userId) {
        Card card = null;
        ResultSet result = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement("SELECT * FROM CARDS WHERE user_id=?")) {
            pstm.setInt(1, userId);
            result = pstm.executeQuery();
            if (result.next()) {
                card = new Card();
                card.setBillId(result.getLong("bill_id"));
                card.setCardId(result.getInt("card_id"));
                card.setUserId(result.getInt("user_id"));
                card.setCardSum(result.getLong("card_sum"));
                card.setCardStatus(CardStatus.valueOf(result.getString("card_status")));
                card.setUserStatus(UserStatus.valueOf(result.getString("user_status")));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } finally {
            close(result);
        }
        return card;
    }

    @Override
    public Card searchCardByCardId(int cardId) {
        Card card = null;
        ResultSet result = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement("SELECT * FROM CARDS WHERE card_id=?")) {
            pstm.setInt(1, cardId);
            result = pstm.executeQuery();
            if (result.next()) {
                card = new Card();
                card.setBillId(result.getLong("bill_id"));
                card.setCardId(result.getInt("card_id"));
                card.setUserId(result.getInt("user_id"));
                card.setCardSum(result.getLong("card_sum"));
                card.setCardStatus(CardStatus.valueOf(result.getString("card_status")));
                card.setUserStatus(UserStatus.valueOf(result.getString("user_status")));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } finally {
            close(result);
        }
        return card;
    }

    @Override
    public void deleteCard(Card card) {
    }

    @Override
    public void blockCard(int cardId) {
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement
                     ("UPDATE CARDS SET card_status=? WHERE card_id=?")) {
            pstmt.setString(1, "BLOCKED");
            pstmt.setLong(2, cardId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unBlockCard(int cardId) {
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement
                     ("UPDATE CARDS SET card_status=? WHERE card_id=?")) {
            pstmt.setString(1, "ACTIVE");
            pstmt.setLong(2, cardId);
            pstmt.executeUpdate();
            cleanRequestToUnblock(cardId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Card> findAllUserCards(int userid, int pageNumber, int size) {
        List<Card> cards = new ArrayList<>();
        ResultSet rs = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM cards where user_id = ? " +
                     " ORDER by card_id LIMIT ?, ?")) {
            pstmt.setLong(1, userid);
            pstmt.setInt(2, (pageNumber - 1) * size);
            pstmt.setInt(3, size);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                cards.add(new Card(rs.getInt(1), rs.getInt(2),
                        rs.getLong(3), rs.getLong(4),
                        CardStatus.valueOf(rs.getString(5)),
                        UserStatus.valueOf(rs.getString(6)),
                        UserRequest.valueOf(rs.getString(7))));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } finally {
            close(rs);
        }
        return cards;
    }

    public int countOfAllUsersCards(){
        ResultSet rs = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt =
                     connection.prepareStatement("SELECT COUNT(*)" +
                             " FROM CARDS ")) {
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Card> findAllCards(int pageNumber, int size) {
        List<Card> cards = new ArrayList<>();
        ResultSet rs = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM cards ORDER BY card_id  LIMIT ?, ? ")) {
            pstmt.setInt(1, (pageNumber - 1) * size);
            pstmt.setInt(2, size);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                cards.add(new Card(rs.getInt(1), rs.getInt(2),
                        rs.getLong(3), rs.getLong(4), CardStatus.valueOf(rs.getString(5)),
                        UserStatus.valueOf(rs.getString(6)), UserRequest.valueOf(rs.getString(7))));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } finally {
            close(rs);
        }
        return cards;
    }

    @Override
    public void updateBalAfterSubmit(Payment payment) {
        Card card = searchCardByCardId(payment.getCardId());
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement("UPDATE CARDS SET card_sum=? WHERE card_id=?")) {
            pstmt.setLong(2, payment.getCardId());
            if (payment.getTransactionType().equalsIgnoreCase("positive"))
                pstmt.setInt(1, (int) (payment.getPaymentSum() + card.getCardSum()));
            else
                pstmt.setInt(1, (int) (card.getCardSum() - payment.getPaymentSum()));
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            }
        }
    }
}