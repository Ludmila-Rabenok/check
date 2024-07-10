package ru.clevertec.check.util;

import ru.clevertec.check.exception.InternalServerException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static Connection connection;
    private static final String DRIVER = "org.postgresql.Driver";

    private DBConnector() {
        connect();
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connect();
            }
            return connection;
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }

    private static void connect() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(
                    ArgumentDatasource.getURL(),
                    ArgumentDatasource.getUSER(),
                    ArgumentDatasource.getPASSWORD());
        } catch (SQLException | ClassNotFoundException e) {
            throw new InternalServerException();
        }
    }
}