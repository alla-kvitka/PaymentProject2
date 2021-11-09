package com.example.paymentproject.dao.impl;

import com.example.paymentproject.utils.Utils;
import com.example.paymentproject.dao.iterfaces.CardDao;
import com.example.paymentproject.entity.Card;
import com.example.paymentproject.entity.Enums.CardStatus;

import java.sql.*;

public class CardDaoImpl implements CardDao {
    @Override
    public Card insertCard(Card card) throws SQLException {
        ResultSet rs = null;
        int random = Utils.randomInt();
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement
                     ("INSERT INTO CARDS VALUES (?, ?, ?, ?,?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, random);
            pstmt.setInt(2, card.getUserId());
            pstmt.setLong(3, 4000);
            pstmt.setLong(4, card.getBillId());
            pstmt.setString(5, String.valueOf(card.isCardStatus()));
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {

                card.setCardId(random);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
        }
        return card;
    }

    @Override
    public Card searchUserCards(long id) {
        Card card = null;
        ResultSet rs = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM CARDS WHERE user_id=?")) {
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                card = new Card();
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
