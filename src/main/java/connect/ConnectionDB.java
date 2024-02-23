package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionDB {
    private static ConnectionDB connectionDB;
    private static Connection connection;


    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            new ConnectionDB();
        }
        return connection;
    }

    private ConnectionDB() throws SQLException {
        try {
            Class.forName(Config.DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String jdbcURL = "jdbc:postgresql://" + Config.SERVER + "/" + Config.DB; // тут лучше через String.format
        connection = DriverManager.getConnection(jdbcURL, Config.LOGIN, Config.PASS);
    }

    public static ConnectionDB getInstance() throws SQLException {
        return connectionDB == null ? (connectionDB = new ConnectionDB()) : connectionDB;
    }


//    public ConnectionDB() throws SQLException {
//        try {
//            Class.forName(Config.DRIVER);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        String jdbcURL = "jdbc:postgresql://" +Config.SERVER + "/" + Config.DB;
//        connection = DriverManager.getConnection(jdbcURL, Config.LOGIN, Config.PASS);
//    }
}
