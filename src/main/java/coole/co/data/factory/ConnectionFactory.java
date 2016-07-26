/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coole.co.data.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author nguyendanghung
 */
public class ConnectionFactory {

    private static Connection con;
    //TODO : need to get port and host from configuration
    protected static String PHOENIX_HOST = "localhost";
    protected static int PHOENIX_PORT = 2181;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (con == null || con.isClosed()) {
            Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
            con = DriverManager.getConnection("jdbc:phoenix:" + PHOENIX_HOST + ":" + PHOENIX_PORT);
        }
        return con;
    }

}
