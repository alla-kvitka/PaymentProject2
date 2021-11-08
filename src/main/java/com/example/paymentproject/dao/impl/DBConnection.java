package com.example.paymentproject.dao.impl;



import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;


public class DBConnection {
//    private static final Logger LOGGER = Logger.getLogger(DBConnection.class);
    static DBConnection instance;
    public DataSource dataSource;

    public static synchronized DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    private DBConnection() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup(
                    "java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/Payments");
        } catch (Throwable e) {
            System.out.println("Ne bere");
//            LOGGER.("Cannot init DBManager", e);
        }
    }


    public Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }


    public void commitAndClose(Connection connection) {
        try {
            connection.commit();
            connection.close();
        } catch (SQLException e) {
//            LOGGER.warning("Cannot commit and close", e);
        }
    }


    public void rollbackAndClose(Connection connection) {
        try {
            connection.rollback();
            connection.close();
        } catch (SQLException e) {
//            LOGGER.warning("Cannot rollback and close", e);
        }
    }


    public void close(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
//                LOGGER.warning("Cannot close statement", e);
            }
        }
    }


    public void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
//                LOGGER.warning("Cannot close resultSet", e);
            }
        }
    }
}
