import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

    static final String DB_URL = "jdbc:mysql://localhost:3306";
    static final String USER = "root";
    static final String PASS = "Banaz12";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stm = conn.createStatement();) {
            String sql = "CREATE DATABASE";
            stm.executeUpdate(sql);
            System.out.println("Database created scessfully...");

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
