package com.example.paymentproject.dao.impl;

import com.example.paymentproject.entity.User;
import com.example.paymentproject.utils.Utils;
import com.example.paymentproject.dao.iterfaces.CardDao;
import com.example.paymentproject.entity.Card;
import com.example.paymentproject.entity.Enums.CardStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardDaoImpl implements CardDao {
    @Override
    public Card insertCard(Card card) throws SQLException {
        int random = Utils.randomInt();
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement
                     ("INSERT INTO CARDS VALUES (?, ?, ?, ?,?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            card.setCardId(random);
            pstmt.setInt(1, card.getCardId());
            pstmt.setInt(2, card.getUserId());
            pstmt.setLong(3, 4000);
            pstmt.setLong(4, card.getBillId());
            pstmt.setString(5, String.valueOf(card.isCardStatus()));
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return card;
    }

    @Override
    public Card searchUserCards(int id) {
        Card card = null;
        ResultSet rs = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM CARDS WHERE user_id=?")) {
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                card = new Card();
                card.setCardId(rs.getInt("card_id"));
                card.setUserId(rs.getInt("user_id"));
                card.setCardSum(rs.getLong("card_sum"));
                card.setCardStatus(CardStatus.valueOf(rs.getString("card_status")));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } finally {
            close(rs);
        }
        return card;
    }


    public List<Card> findAllUsersCards(int userid) {
        List<Card> cards = new ArrayList<>();
        ResultSet rs = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM cards where user_id = ?")) {
            pstmt.setLong(1, userid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                cards.add(new Card(rs.getInt(1), rs.getInt(2),
                        rs.getLong(3),rs.getLong(4), CardStatus.valueOf(rs.getString(5))));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } finally {
            close(rs);
        }
        return cards;
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
