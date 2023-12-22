package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String url = "jdbc:mysql://localhost:3306/testschema";
    private static final String dbUser = "root";
    private static final String dbPassword = "root";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static SessionFactory sessionFactory;
    public Util() {
    }
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, dbUser, dbPassword);
        } catch (SQLException e) {
            System.out.println("Connect not possible" + e);
        }
        return conn;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, driver);
                properties.put(Environment.URL, url);
                properties.put(Environment.USER, dbUser);
                properties.put(Environment.PASS, dbPassword);
                sessionFactory = new Configuration().addAnnotatedClass(User.class).setProperties(properties).buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
