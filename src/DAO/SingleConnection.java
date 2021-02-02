/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author COZMET
 */
public class SingleConnection {
    private String db = "projet";
    private String user = "root";
    private String pwd = "";
    private String url = "jdbc:mysql://localhost:3306/"+db;
    private static Connection connection = null;
    private SingleConnection(){
        try {
            connection = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException ex) {
            Logger.getLogger(SingleConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Connection getConnection(){
        if(connection==null)
            new SingleConnection();
        return connection;
    }
}
