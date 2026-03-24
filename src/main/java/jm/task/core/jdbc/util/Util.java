package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/zadacha_1_1_3",
                    "root",
                    "1234");
            System.out.println("Подключение успешно!");
        } catch (SQLException e) {
            System.out.println("Ошибка:" + e.getMessage());
        }
        return connection;
    }
}
