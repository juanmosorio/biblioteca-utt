/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Clock;
import controller.ControllerPersonStudent;
import controller.ControllerTooll;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import static view.Registry.btnExit;

/**
 *
 * @author Juan1
 */
public class RegistryStudentG extends javax.swing.JPanel {

    //controlador
    ControllerPersonStudent controller = new ControllerPersonStudent();
    //
    ControllerTooll ct = new ControllerTooll();
    //
    private int IdActivity, IdCareer; //c atividad
    private int hour, minutes, secons, year, month, day;//variables para la fecha y hora
    Calendar fecha = new GregorianCalendar();//calendario
    SimpleDateFormat formatBirthday = new SimpleDateFormat("yyyy-MM-dd");//variable de tipo format

    String vName = "", vLastname = "", vLastname2 = "";
    int vGender = 1;
    String vTipo = "";
    Integer vNoMan = 0;
    Integer vNoWman = 0;
    int vId_career = 0;
    String vId_careerv = "";
    String vMatValue = "";
    String vNameActivity = "";

    boolean existsM, existsN = false;

    /**
     * Creates new form RegistryStudentG
     */
    public RegistryStudentG() {
        initComponents();
        initItems();
    }

    void initItems() {//inicia objetos
        jdDate.setCalendar(fecha);
        jdDate.setEnabled(false);
        jdDate.setVisible(false);
//        loadComboActivity();//individual
//        loadComboCareer();//grupo
        Timer timer = new Timer(1, new Clock());
        timer.start();
        btnSearch.setEnabled(false);
        lbNameEndtime.setEnabled(false);
        lbEndtimeG.setEnabled(false);
        btnOut.setEnabled(false);
        lbNameStarttime.setEnabled(false);
        lbStartimeG.setEnabled(false);
        btnIn.setEnabled(false);
        rbTSU.setSelected(true);
//        btnSearchM.setEnabled(false);

        Color color = new Color(41, 48, 81);
        btnIn.setBackground(color);
        btnOut.setBackground(color);
        btnSearch.setBackground(color);

        cbxActivity.setBackground(Color.white);
        cbxCarrera.setBackground(Color.white);

        rbTSU.setBackground(Color.white);
        rbIngenieria.setBackground(Color.white);

        txtNameG.setBorder(BorderFactory.createLineBorder(color));
        txtLastnameG.setBorder(BorderFactory.createLineBorder(color));
        txtLastname2G.setBorder(BorderFactory.createLineBorder(color));
    }

    void itemsEnable(boolean value) {
        cbxCarrera.setEnabled(value);//<<----optimizar este codigo
        rbTSU.setEnabled(value);//<<----optimizar este codigo
        rbIngenieria.setEnabled(value);//<<----optimizar este codigo
        cbxActivity.setEnabled(value);//<<----optimizar este codigo
        jspNoM.setEnabled(value);
        jspNoF.setEnabled(value);
    }

    void cleanN() {
        cbxCarrera.setSelectedItem("Elige tu Carrera");
        rbTSU.isSelected();
        jspNoM.setValue(0);
        jspNoF.setValue(0);
        cbxActivity.setSelectedItem("Elige una actividad");
        rbTSU.isSelected();
    }

    void loadrbGrade() {
        if (vTipo.equals("TSU")) {//<--ERROR
            rbTSU.setSelected(true);
        } else if (vTipo.equals("INGENIERIA")) {//<--ERROR
            rbIngenieria.setSelected(true);
        }
    }

    void foundN() {
        if (existsN == false) {
            JOptionPane.showMessageDialog(null, "Nombre no encontrado!\nRellene los campos siguientes!");;
            cbxCarrera.setSelectedItem("Elige tu Carrera");
            rbTSU.isSelected();
        } else {
            cbxCarrera.setSelectedItem(vId_careerv);
            loadrbGrade();
        }
    }

    void countM(String vName, String vLastname, String vLastname2, String date) {
        ResultSet result = controller.countM(vName, vLastname, vLastname2, date);
        try {
            if (result.next()) {
                vNoMan = result.getInt("M");
                jspNoM.setValue(vNoMan);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
    }

    void countF(String vName, String vLastname, String vLastname2, String date) {
        ResultSet result = controller.countF(vName, vLastname, vLastname2, date);
        try {
            if (result.next()) {
                vNoWman = result.getInt("F");
                jspNoF.setValue(vNoWman);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
    }

    void searchGroupStudentOut(String vName, String vLastname, String vLastname2) {//busca por medio del nombre
        existsN = false;
        String date = formatBirthday.format(jdDate.getDate());//<--revisar
        ResultSet result = controller.searchGroupStudentOut(vName, vLastname, vLastname2, date);
        try {
            if (result.next()) {
                vId_career = result.getInt("id_carrera");
                vId_careerv = result.getString("nombre_carrera");
                vTipo = result.getString("grado");
                vNameActivity = result.getString("nombre_actividad");
                btnOut.setEnabled(true);//<<----optimizar este codigo
                lbEndtimeG.setEnabled(true);//<<----optimizar este codigo
                lbNameEndtime.setEnabled(true);//<<----optimizar este codigo
                lbNameStarttime.setEnabled(false);//<<----optimizar este codigo
                lbStartimeG.setEnabled(false);//<<----optimizar este codigo
                btnIn.setEnabled(false);
                itemsEnable(false);
                existsN = true;
                cbxActivity.setSelectedItem(vNameActivity);
                countM(vName, vLastname, vLastname2, date);
                countF(vName, vLastname, vLastname2, date);
                foundN();
            } else {
                searchGroupStudent(vName, vLastname, vLastname2);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
    }

    void searchGroupStudent(String vName, String vLastname,
            String vLastname2
    ) {//busca por medio del nombre
        existsN = false;
        ResultSet result = controller.searchGroupStudent(vName, vLastname, vLastname2);
        try {
            if (result.next()) {
                existsN = true;
                vId_career = result.getInt("id_carrera");
                vId_careerv = result.getString("nombre_carrera");
                vTipo = result.getString("grado");
                btnIn.setEnabled(true);//<<----optimizar este codigo
                lbStartimeG.setEnabled(true);//<<----optimizar este codigo
                lbNameStarttime.setEnabled(true);//<<----optimizar este codigo
                itemsEnable(existsN);
                lbNameEndtime.setEnabled(false);
                lbEndtimeG.setEnabled(false);
                btnOut.setEnabled(false);
                cbxCarrera.setEnabled(false);
                rbIngenieria.setEnabled(false);
                rbTSU.setEnabled(false);
            } else {
                lbNameStarttime.setEnabled(true);
                lbStartimeG.setEnabled(true);
                btnIn.setEnabled(true);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
//        txtMatricula.setText(vMatValue);
//        String vGenerev = null;
//        if (vGender == 1) {
//            vGenerev = "Masculino";
//        } else if (vGender == 0) {
//            vGenerev = "Femenino";
//        }
        foundN();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgGrade = new javax.swing.ButtonGroup();
        jPanelTeacher = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNameG = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtLastnameG = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtLastname2G = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cbxCarrera = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        rbTSU = new javax.swing.JRadioButton();
        rbIngenieria = new javax.swing.JRadioButton();
        jdDate = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jspNoM = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        jspNoF = new javax.swing.JSpinner();
        jLabel14 = new javax.swing.JLabel();
        cbxActivity = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        lbNameStarttime = new javax.swing.JLabel();
        btnIn = new javax.swing.JButton();
        lbNameEndtime = new javax.swing.JLabel();
        btnOut = new javax.swing.JButton();
        lbStartimeG = new javax.swing.JFormattedTextField();
        lbEndtimeG = new javax.swing.JFormattedTextField();

        jPanelTeacher.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(41, 48, 81));
        jLabel1.setText("Nombre:");

        txtNameG.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtNameG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNameGKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(41, 48, 81));
        jLabel3.setText("Apellido Paterno: ");

        txtLastnameG.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtLastnameG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLastnameGKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(41, 48, 81));
        jLabel5.setText("Apellido Materno: ");

        txtLastname2G.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtLastname2G.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLastname2GKeyTyped(evt);
            }
        });

        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Search.png"))); // NOI18N
        btnSearch.setText("Buscar");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNameG, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLastnameG, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLastname2G, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch)
                .addContainerGap(134, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNameG, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtLastnameG, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtLastname2G, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setPreferredSize(new java.awt.Dimension(937, 95));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(41, 48, 81));
        jLabel4.setText("Carrera:");

        cbxCarrera.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        cbxCarrera.setForeground(new java.awt.Color(41, 48, 81));
        cbxCarrera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige tu Carrera" }));

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(41, 48, 81));
        jLabel11.setText("Grado:");

        btgGrade.add(rbTSU);
        rbTSU.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        rbTSU.setForeground(new java.awt.Color(41, 48, 81));
        rbTSU.setText("TSU");

        btgGrade.add(rbIngenieria);
        rbIngenieria.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        rbIngenieria.setForeground(new java.awt.Color(41, 48, 81));
        rbIngenieria.setText("INGENIERÍA");

        jdDate.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbxCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbTSU)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbIngenieria)
                .addGap(54, 54, 54)
                .addComponent(jdDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(cbxCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(rbTSU)
                        .addComponent(rbIngenieria)))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setPreferredSize(new java.awt.Dimension(937, 95));

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(41, 48, 81));
        jLabel7.setText("Número de Hombres: ");

        jspNoM.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(41, 48, 81));
        jLabel13.setText("Número de Mujeres: ");

        jspNoF.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(41, 48, 81));
        jLabel14.setText("Actividad:");

        cbxActivity.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        cbxActivity.setForeground(new java.awt.Color(41, 48, 81));
        cbxActivity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige una actividad" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jspNoM, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(12, 12, 12)
                .addComponent(jspNoF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(cbxActivity, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jspNoM, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jspNoF, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(cbxActivity, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setPreferredSize(new java.awt.Dimension(937, 95));

        lbNameStarttime.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbNameStarttime.setForeground(new java.awt.Color(41, 48, 81));
        lbNameStarttime.setText("Hora de Entrada:");

        btnIn.setForeground(new java.awt.Color(255, 255, 255));
        btnIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Input.png"))); // NOI18N
        btnIn.setText("Registar Entrada");
        btnIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInActionPerformed(evt);
            }
        });

        lbNameEndtime.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbNameEndtime.setForeground(new java.awt.Color(41, 48, 81));
        lbNameEndtime.setText("Hora de Salida:");

        btnOut.setForeground(new java.awt.Color(255, 255, 255));
        btnOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Output.png"))); // NOI18N
        btnOut.setText("Registrar Salida");
        btnOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOutActionPerformed(evt);
            }
        });

        lbStartimeG.setForeground(new java.awt.Color(41, 48, 84));
        lbStartimeG.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance())));
        lbStartimeG.setText("00:00:00");
        lbStartimeG.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        lbEndtimeG.setForeground(new java.awt.Color(41, 48, 84));
        lbEndtimeG.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance())));
        lbEndtimeG.setText("00:00:00");
        lbEndtimeG.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(lbNameStarttime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbStartimeG, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(btnIn)
                .addGap(87, 87, 87)
                .addComponent(lbNameEndtime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbEndtimeG, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(btnOut)
                .addContainerGap(288, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNameStarttime)
                    .addComponent(btnIn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNameEndtime)
                    .addComponent(btnOut, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbStartimeG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEndtimeG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout jPanelTeacherLayout = new javax.swing.GroupLayout(jPanelTeacher);
        jPanelTeacher.setLayout(jPanelTeacherLayout);
        jPanelTeacherLayout.setHorizontalGroup(
            jPanelTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE)
        );
        jPanelTeacherLayout.setVerticalGroup(
            jPanelTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTeacherLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTeacher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActionPerformed
        // TODO add your handling code here:
        date();
        generateIdActivity();
        generateIdCareer();
        Integer NoM = (Integer) jspNoM.getValue();
        Integer NoF = (Integer) jspNoF.getValue();
        if (txtNameG.getText().isEmpty() || txtLastnameG.getText().isEmpty() || txtLastname2G.getText().isEmpty()
                || cbxCarrera.getSelectedItem().toString() == "Elige tu Carrera" || (NoM <= 0 && NoF <= 0)
                || cbxActivity.getSelectedItem().toString() == "Elige una actividad") {
            JOptionPane.showMessageDialog(null, "!Por favor llene todos los campos correctamente¡");
        } else {
            String name = txtNameG.getText();
            String lastname = txtLastnameG.getText();
            String lastname2 = txtLastname2G.getText();
            int career = IdCareer;
            if (rbTSU.isSelected()) {
                vTipo = "TSU";
            } else if (rbIngenieria.isSelected()) {
                vTipo = "INGENIERIA";
            }
            String grade = vTipo;
            int activity = IdActivity;
            Integer noH = Integer.parseInt(jspNoM.getValue().toString());
            Integer noM = Integer.parseInt(jspNoF.getValue().toString());
            Integer total = noH + noM;
            String startTime = hour + ":" + minutes + ":" + secons;
            String endTime = 00 + ":" + 00 + ":" + 00;
            String date = formatBirthday.format(jdDate.getDate());
            String tipo = "ESTUDIANTE";
            controller.insertPersonStudentG(name, lastname, lastname2, career, grade,
                    activity, noH, noM, total, startTime, endTime, date, tipo);
            
           
            //btnExit.doClick();
            defaults();
        }
    }//GEN-LAST:event_btnInActionPerformed

    void defaults() {
        cbxCarrera.setEnabled(true);
        rbIngenieria.setEnabled(true);
        rbTSU.setEnabled(true);
        jspNoF.setEnabled(true);
        jspNoM.setEnabled(true);
        cbxActivity.setEnabled(true);
         txtNameG.setText("");
            txtLastnameG.setText("");
            txtLastname2G.setText("");
            cleanN();
            btnIn.setEnabled(false);
            btnOut.setEnabled(false);
            lbEndtimeG.setEnabled(false);
            lbNameEndtime.setEnabled(false);
            lbNameStarttime.setEnabled(false);
            lbStartimeG.setEnabled(false);
            rbTSU.setSelected(true);
    }
    void intemHEnables() {
        btnOut.setEnabled(false);//<<----optimizar este codigo
        lbEndtimeG.setEnabled(false);//<<----optimizar este codigo
        lbNameEndtime.setEnabled(false);//<<----optimizar este codigo
        lbNameStarttime.setEnabled(false);//<<----optimizar este codigo
        lbStartimeG.setEnabled(false);//<<----optimizar este codigo
        btnIn.setEnabled(false);
    }
    private void txtNameGKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameGKeyTyped
        // TODO add your handling code here:
        intemHEnables();
        cleanN();
        itemsEnable(true);
        ct.UpperCaseNameG();
        char keyPressed = evt.getKeyChar();
        if (keyPressed == KeyEvent.VK_ENTER) {
            txtLastnameG.requestFocus();
        }
    }//GEN-LAST:event_txtNameGKeyTyped

    private void txtLastnameGKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLastnameGKeyTyped
        // TODO add your handling code here:
        intemHEnables();
        cleanN();
        itemsEnable(true);
        ct.UpperCaseLastnameG();
//        ct.UpperCaseNameG();
        char keyPressed = evt.getKeyChar();
        if (keyPressed == KeyEvent.VK_ENTER) {
            txtLastname2G.requestFocus();
        }
    }//GEN-LAST:event_txtLastnameGKeyTyped

    private void txtLastname2GKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLastname2GKeyTyped
        // TODO add your handling code here:
        intemHEnables();
        cleanN();
        itemsEnable(true);
        ct.UpperCaseLastname2G();
        btnSearch.setEnabled(true);
        char keyPressed = evt.getKeyChar();
        if (keyPressed == KeyEvent.VK_ENTER) {
            btnSearch.doClick();
        }
    }//GEN-LAST:event_txtLastname2GKeyTyped

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        if (txtLastname2G.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "¡Ingresa tu nombre completo para comensar con la busqueda!");
        } else {
            vName = (txtNameG.getText());
            vLastname = (txtLastnameG.getText());
            vLastname2 = (txtLastname2G.getText());
            searchGroupStudentOut(vName, vLastname, vLastname2);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOutActionPerformed
        // TODO add your handling code here:
        date();
        String endTime = hour + ":" + minutes + ":" + secons;
        String date = formatBirthday.format(jdDate.getDate());
        String name = txtNameG.getText();
        String lastname = txtLastnameG.getText();
        String lastname2 = txtLastname2G.getText();
        controller.groupStudentEndtime(name, lastname, lastname2, endTime, date);
        //btnExit.doClick();
        defaults();
    }//GEN-LAST:event_btnOutActionPerformed

    void date() {//genera ña fecha y hora del sistema
        GregorianCalendar time = new GregorianCalendar();
        //Horas
        hour = time.get(Calendar.HOUR_OF_DAY);
        minutes = time.get(Calendar.MINUTE);
        secons = time.get(Calendar.SECOND);
        //fecha
        year = time.get(Calendar.YEAR);
        month = time.get(Calendar.MONTH);
        day = time.get(Calendar.DAY_OF_MONTH);
    }

//    void loadComboActivity() {//cargar el combo actividad 
//        ResultSet result = controller.loadComboActivity();//crea un objeto de controler 
//        try {
//            while (result.next()) {
//                cbxActivity.addItem(result.getString("nombre"));
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
//        }
//    }//individual && grupo
    void generateIdActivity() {//genera el id de activity
        String value = cbxActivity.getSelectedItem().toString();

        ResultSet result = controller.generateIdActivity(value);
        int vIdActivity = 0;
        try {
            while (result.next()) {
                vIdActivity = result.getInt("id_actividad");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
        IdActivity = vIdActivity;
    }//individual 

//    void loadComboCareer() {//cargar el combo Carrera Grupo
//        ResultSet result = controller.loadComboCareer();//crea un objeto de controler 
//        try {
//            while (result.next()) {
//                cbxCarrera.addItem(result.getString("nombre_carrera"));
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
//        }
//    }//individual
    void generateIdCareer() {//genera el id de activity
        String value = cbxCarrera.getSelectedItem().toString();

        ResultSet result = controller.generateIdCareer(value);
        int vIdCareer = 0;
        try {
            while (result.next()) {
                vIdCareer = result.getInt("id_carrera");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
        IdCareer = vIdCareer;
    }//individual 

//    int vGender = 1;
//    String vTipo = "";

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgGrade;
    private javax.swing.JButton btnIn;
    private javax.swing.JButton btnOut;
    private javax.swing.JButton btnSearch;
    public static javax.swing.JComboBox<String> cbxActivity;
    public static javax.swing.JComboBox<String> cbxCarrera;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelTeacher;
    private com.toedter.calendar.JDateChooser jdDate;
    private javax.swing.JSpinner jspNoF;
    private javax.swing.JSpinner jspNoM;
    public static javax.swing.JFormattedTextField lbEndtimeG;
    private javax.swing.JLabel lbNameEndtime;
    private javax.swing.JLabel lbNameStarttime;
    public static javax.swing.JFormattedTextField lbStartimeG;
    private javax.swing.JRadioButton rbIngenieria;
    private javax.swing.JRadioButton rbTSU;
    public static javax.swing.JTextField txtLastname2G;
    public static javax.swing.JTextField txtLastnameG;
    public static javax.swing.JTextField txtNameG;
    // End of variables declaration//GEN-END:variables

}
