/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.ControllerReports;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Juan1
 */
public class Reports {

    Conexion conexion;//se crea conexion de tipo Conexion

    public Reports() {//se inicializa en el constructor
        conexion = new Conexion();
        executeReport();
    }


    public void executeReport() {
        ControllerReports report1 = new ControllerReports();
        report1.executeReportOne();
    }
}
