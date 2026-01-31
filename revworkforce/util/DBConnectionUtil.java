
package com.revworkforce.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {

    private static final String URL =
        "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "revworkforce";
    private static final String PASSWORD = "rev123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
