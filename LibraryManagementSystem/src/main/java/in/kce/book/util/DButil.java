package in.kce.book.util;

import java.sql.*;

public class DButil {
    public static Connection getDBConnection() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");

            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String user = "system";
            String pass = "admin";

            return DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
