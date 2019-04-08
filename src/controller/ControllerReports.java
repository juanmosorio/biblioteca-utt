/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;
import model.Conexion;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import static view.Reports.panelReport;

/**
 *
 * @author Juan1
 */
public class ControllerReports {

    Conexion conexion;//se crea conexion de tipo Conexion

    public ControllerReports() {//se inicializa en el constructor
        conexion = new Conexion();
    }

    public void executeReport(String startDate, String endDate, String report) {
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(ControllerReports.class.getResource("/report/" + report + ".jasper"));
            Map parameters = new HashMap<String, Object>();
            parameters.put("startDate", startDate);
            parameters.put("endDate", endDate);
            JasperPrint jp = JasperFillManager.fillReport(jr, parameters, conexion.getConexion());
            //se crea el visor con el reporte
            JRViewer jRViewer = new JRViewer(jp);
//            JRExporter exporter = new JRPdfExporter();
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
//            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reportePDF.pdf"));
//            exporter.exportReport();
            //se elimina elementos del contenedor JPanel
            panelReport.removeAll();
            //para el tamaño de l reporte se agrega un BorderLayout
            panelReport.setLayout(new BorderLayout());
            panelReport.add(jRViewer, BorderLayout.CENTER);
            jRViewer.setVisible(true);
            panelReport.repaint();
            panelReport.revalidate();
        } catch (JRException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void executeReportOne() {
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(ControllerReports.class.getResource("/report/RP.jasper"));
//            Map parameters = new HashMap<String, Object>();
//            parameters.put("HOMBRES", contains);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conexion.getConexion());
            JasperViewer jv = new JasperViewer(jp);
            jv.show();
        } catch (JRException ex) {
            System.err.println(ex.getMessage());
        }
    }

    ////////////////////////prueba///////////////////////////////////////////////
    public void executeReport1(String startDate, String endDate) {
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(ControllerReports.class.getResource("/report/RP.jasper"));
            Map parameters = new HashMap<String, Object>();
            parameters.put("startDate", startDate);
            parameters.put("endDate", endDate);
            JasperPrint jp = JasperFillManager.fillReport(jr, parameters, conexion.getConexion());
            //se crea el visor con el reporte
            JRViewer jRViewer = new JRViewer(jp);
            //se elimina elementos del contenedor JPanel
            panelReport.removeAll();
            //para el tamaño de l reporte se agrega un BorderLayout
            panelReport.setLayout(new BorderLayout());
            panelReport.add(jRViewer, BorderLayout.CENTER);
            jRViewer.setVisible(true);
            panelReport.repaint();
            panelReport.revalidate();
        } catch (JRException ex) {
            System.err.println(ex.getMessage());
        }
    }

//    public void exportReportToExcel(String startDate, String endDate, String report) {
//        try {
//            HashMap params = new HashMap();
//            params.put("startDate", startDate);
//            params.put("endDate", endDate);
//            // Cargamos la plantilla
//            JasperDesign objJasperDesign = JRXmlLoader.load("C:/Users/juan2/OneDrive/Project-BETA/Biblioteca/src/report/Reporte 1.jrxml");
//            // Compilamos la plantilla
//            JasperReport objJasperReport = JasperCompileManager.compileReport(objJasperDesign);
//            // Poblamos la plantilla con los datos de la BD y parametros
//            JasperPrint objJasperPrint = JasperFillManager.fillReport(objJasperReport, params, conexion.getConexion());
//            // Instanciamos la clase exportadora
//            JExcelApiExporter xlsExporter = new JExcelApiExporter();
//            JRXlsExporter exporterXLS = new JRXlsExporter();
//
//            xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT,
//                    objJasperPrint);
//            xlsExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
//                    Boolean.TRUE);
//            xlsExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "C:/archivo.xls");
//
//        } catch (JRException ex) {
//            System.out.println("Error "+ex.getMessage());
//        }
//// ---- Generando archivo XLS FIN ---- //
}
