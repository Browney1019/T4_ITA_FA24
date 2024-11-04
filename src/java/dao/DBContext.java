package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Author: PC
 */
public class DBContext {

    public Connection conn = null;
    private static DBContext instance;
    private final String DB_ACC = "sa";
    private final String DB_PASS = "123456789";
    private final String DB_HOST = "localhost";
    private final String DB_PORT = "3306";
    private final String DB_NAME = "mpms";

    public static DBContext getInstance() {
        if (instance == null) {
            instance = new DBContext();
        }
        return instance;
    }

    public ResultSet getData(String sql) {
        ResultSet rs = null;
        Statement state;
        try {
            state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public DBContext() {
        try {
            if (conn == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String connectionString = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
                conn = DriverManager.getConnection(connectionString, DB_ACC, DB_PASS);
                System.out.println("connected");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// Add a method to close the connection when done
    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection closed");
            } catch (SQLException ex) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    protected Connection getConnection() {
        return conn;
    }

    public static void main(String[] args) {
        DBContext.getInstance().getConnection();
        DBContext db = new DBContext();
    }
}
