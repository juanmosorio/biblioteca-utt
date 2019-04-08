/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Juan1
 */
public class Conexion {

    /*public Connection getConexion() {
        Connection conect = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conect = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=Biblioteca", "sa", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conect;
    }
    */
      public Connection getConexion() {

        Connection conexion = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String serverDb = "jdbc:mysql://localhost:3306/biblioteca";
            String userDb = "root";
            String passwordDb = "";
            conexion = DriverManager.getConnection(serverDb, userDb, passwordDb);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "The Driver was not Found");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "There was a problem" + e.getMessage());
        } finally {
            return conexion;
        }
    }
}
