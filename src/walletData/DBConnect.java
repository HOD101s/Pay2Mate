package walletData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/pay2mate";
    private static Connection connection;
    private static Statement statement;

    public static void connect(){
        try {
            connection = DriverManager.getConnection(DB_URL,"root","");
            statement = connection.createStatement();
            System.out.println("Connection Established");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Statement getStatement() {
        return statement;
    }

    public static void disconnect(){

        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
