package resortmanager.webservice.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: ilyasavchenko
 * Date: 11/7/13
 * Time: 12:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionSingleton {

    private static Connection connection = null;

    public static Connection getConnection() {
        //  only for development
        String connectionString = "jdbc:mysql://localhost:3306/resort";
        String connectionUser = "root";
        String connectionPassword = "newPass1";
        //
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return connection;
    }
}