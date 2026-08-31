package Student;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static Connection con;

    public static Connection getConnection() {

        try {

            if (con == null || con.isClosed()) {

                Class.forName("com.mysql.cj.jdbc.Driver");

                con = DriverManager.getConnection(
                		 "jdbc:mysql://localhost:3306/sims?user=root&&password=root");

                System.out.println("Database Connected Successfully...");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }
}
