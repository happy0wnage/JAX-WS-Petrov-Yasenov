package ua.nure.petryasya.dao;

/**
 * Created by Владислав on 28.07.2015.

 */

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

    private static final Logger LOG = Logger.getLogger(MySqlConnection.class);

    /*static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.getMessage();
        }
    }*/
    public static Connection getWebConnection() {
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost/movie";
            String username = "root";
            String password = "1234";
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            Connection con = DriverManager.getConnection(url, username,
                    password);
            return con;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /*public static Connection getWebConnection() {
        Connection con = null;
        try {
            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/Course");
            con = ds.getConnection();
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        } catch (NamingException e) {
            LOG.error(e.getMessage());
        }
        return con;
    }*/

}
