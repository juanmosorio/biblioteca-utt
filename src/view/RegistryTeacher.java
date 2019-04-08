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
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import static view.Registry.btnExit;

/**
 *
 * @author Juan1
 */
public class RegistryTeacher extends javax.swing.JPanel {

    //controlador
    ControllerPersonStudent controller = new ControllerPersonStudent();
    //
    ControllerTooll ct = new ControllerTooll();
    //
    private int IdActivity, IdCareer; //c atividad
    private int hour, minutes, secons, year, month, day;//variables para la fecha y hora
    Calendar fecha = new GregorianCalendar();//calendario
    SimpleDateFormat formatBirthday = new SimpleDateFormat("yyyy-MM-dd");//variable de Grado format

    //variables para almacenar la busqueda de por matricula y nombre
    String vName = "", vLastname = "", vLastname2 = "";
    int vGender = 1;
    String vGrado = "";
    int vId_career = 0;
    String vId_careerv = "";
    String vNoValue = "";
    String vNameActivity = "";

    boolean existsNu, existsName = false;

    /**
     * Creates new form RegistryStudent
     */
    public RegistryTeacher() {
        initComponents();
        colorsInterface();
        initItems();
    }

    void itemsEnable(boolean value) {
        rbMale.setEnabled(value);//<<----optimizar este codigo
        rbFemale.setEnabled(value);//<<----optimizar este codigo
        cbxCarrera.setEnabled(value);//<<----optimizar este codigo
        rbTSU.setEnabled(value);//<<----optimizar este codigo
        rbIngenieria.setEnabled(value);//<<----optimizar este codigo
    }

    void initItems() {//inicia objetos
        jdDate.setCalendar(fecha);
        jdDate.setEnabled(false);
        jdDate.setVisible(false);
//        loadComboActivity();//individual
//        loadComboCareer();//grupo
        Timer timer = new Timer(1, new Clock());
        timer.start();
        btnSearchN.setEnabled(false);
        btnSearchNo.setEnabled(false);
        rbMale.setSelected(true);
        rbTSU.setSelected(true);

        //revisar
        btnOut.setEnabled(false);
        lbEndtimeT.setEnabled(false);
        lbNameEndtime.setEnabled(false);
        lbNameStarttime.setEnabled(false);
        lbStartimeT.setEnabled(false);//<<----optimizar este codigo
        btnIn.setEnabled(false);
    }

    void colorsInterface() {
        Color color = new Color(41, 48, 81);
        btnSearchNo.setBackground(color);
        btnSearchN.setBackground(color);
        btnIn.setBackground(color);
        btnOut.setBackground(color);

        cbxActivity.setBackground(Color.WHITE);
        cbxCarrera.setBackground(Color.WHITE);

        rbMale.setBackground(color.white);
        rbFemale.setBackground(color.white);
        rbTSU.setBackground(color.white);
        rbIngenieria.setBackground(color.white);

        txtNoTeacher.setBorder(BorderFactory.createLineBorder(color));
        txtNameT.setBorder(BorderFactory.createLineBorder(color));
        txtLastnameT.setBorder(BorderFactory.createLineBorder(color));
        txtLastname2T.setBorder(BorderFactory.createLineBorder(color));
    }

    void cleanNum() {
        txtNameT.setText("");
        txtLastnameT.setText("");
        txtLastname2T.setText("");
        rbMale.isSelected();
        cbxCarrera.setSelectedItem("Elige tu Carrera");
        cbxActivity.setSelectedItem("Elige una actividad");
        rbTSU.isSelected();
    }

    void cleanNam() {
        txtNoTeacher.setText("");
        rbMale.isSelected();
        cbxCarrera.setSelectedItem("Elige tu Carrera");
        cbxActivity.setSelectedItem("Elige una actividad");
        rbTSU.isSelected();
    }

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

    void loadrbGender() {
        if (vGender == 1) {
            rbMale.setSelected(true);
        } else if (vGender == 0) {
            rbFemale.setSelected(true);
        }
    }

    void loadrbGrade() {
        if (vGrado.equals("TSU")) {//<--ERROR
            rbTSU.setSelected(true);
        } else if (vGrado.equals("INGENIERIA")) {//<--ERROR
            rbIngenieria.setSelected(true);
        }
    }

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

    void foundNumber() {
        if (existsNu == false) {
            JOptionPane.showMessageDialog(null, "Numero no encontrado!\nBusque por nombre o intente de nuevo!");
            txtNoTeacher.setText("");
            txtNameT.requestFocus();
        } else {
            ///mover
            txtNameT.setText(vName);
            txtLastnameT.setText(vLastname);
            txtLastname2T.setText(vLastname2);
            loadrbGender();
            cbxCarrera.setSelectedItem(vId_careerv);
            loadrbGrade();
        }
    }

    void foundName() {
        if (existsName == false) {
            txtNoTeacher.setText("");
            JOptionPane.showMessageDialog(null, "Nombre no encontrado!\nRellene los campos siguientes!");;
            rbMale.isSelected();
            cbxCarrera.setSelectedItem("Elige tu Carrera");
            rbTSU.isSelected();
        } else {
            loadrbGender();
//            cbxCarrera.setSelectedItem(vId_careerv);
            loadrbGrade();
        }
    }

    void searchPersonUserNumtOut(String vNumValue) {//buca por medio de la matricula<--revisar
        existsNu = false;
        String date = formatBirthday.format(jdDate.getDate());//<--revisar
        String tipo = "DOCENTE";
        ResultSet result = controller.searchPersonUserMatOut(vNumValue, date, tipo);
        try {
            if (result.next()) {
                vName = result.getString("nombre");
                vLastname = result.getString("apellidoPaterno");
                vLastname2 = result.getString("apellidoMaterno");
                vGender = result.getInt("genero");
                vGrado = result.getString("grado");
                vId_career = result.getInt("id_carrera");
                vId_careerv = result.getString("nombre_carrera");
                vNameActivity = result.getString("nombre_actividad");
                btnOut.setEnabled(true);//<<----optimizar este codigo
                lbEndtimeT.setEnabled(true);//<<----optimizar este codigo
                lbNameEndtime.setEnabled(true);//<<----optimizar este codigo
                cbxActivity.setEnabled(false);
                itemsEnable(false);//<-toma el vaor de false
                existsNu = true;
                cbxActivity.setSelectedItem(vNameActivity);
                foundNumber();
            } else {
                searchPersonUserNum(vNumValue);
            }
        } catch (SQLException ex) {//<--revisar
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
    }

    void searchPersonUserNum(String vNumValue) {//buca por medio de la matricula
        existsNu = false;
        ResultSet result = controller.searchPersonTeacherNum(vNoValue);
        try {
            if (result.next()) {
                vName = result.getString("nombre");
                vLastname = result.getString("apellidoPaterno");
                vLastname2 = result.getString("apellidoMaterno");
                vGender = result.getInt("genero");
                vGrado = result.getString("grado");
                vId_career = result.getInt("id_carrera");
                vId_careerv = result.getString("nombre_carrera");
                btnIn.setEnabled(true);//<<----optimizar este codigo
                lbStartimeT.setEnabled(true);//<<----optimizar este codigo
                lbNameStarttime.setEnabled(true);//<<----optimizar este codigo
                cbxActivity.setEnabled(true);
                itemsEnable(false);
                existsNu = true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
        foundNumber();
    }

    void searchPersonUserNamOut(String vName, String vLastname, String vLastname2) {//busca por medio del nombre
        String tipo = "DOCENTE";
        existsName = false;
        String date = formatBirthday.format(jdDate.getDate());//<--revisar
        ResultSet result = controller.searchPersonUserNamOut(vName, vLastname, vLastname2, date, tipo);
        try {
            if (result.next()) {
                vNoValue = result.getString("matricula");
                vGender = result.getInt("genero");
                vGrado = result.getString("grado");
                vId_career = result.getInt("id_carrera");
                vId_careerv = result.getString("nombre_carrera");
                vNameActivity = result.getString("nombre_actividad");
                btnOut.setEnabled(true);//<<----optimizar este codigo
                lbEndtimeT.setEnabled(true);//<<----optimizar este codigo
                lbNameEndtime.setEnabled(true);//<<----optimizar este codigo
                cbxActivity.setEnabled(false);
                itemsEnable(false);
                existsName = true;
                txtNoTeacher.setText(vNoValue);
                String vGenerev = null;
                if (vGender == 1) {
                    vGenerev = "Masculino";
                } else if (vGender == 0) {
                    vGenerev = "Femenino";
                }
                cbxActivity.setSelectedItem(vNameActivity);
                foundName();
            } else {
                searchPersonUserNam(vName, vLastname, vLastname2);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
    }

    void searchPersonUserNam(String vName, String vLastname, String vLastname2) {//busca por medio del nombre
        existsName = false;
        ResultSet result = controller.searchPersonTeacherNam(vName, vLastname, vLastname2);
        try {
            if (result.next()) {
                vNoValue = result.getString("numero_empleado");
                vGender = result.getInt("genero");
                vGrado = result.getString("grado");
                vId_career = result.getInt("id_carrera");
                vId_careerv = result.getString("nombre_carrera");
                btnIn.setEnabled(true);//<<----optimizar este codigo
                lbStartimeT.setEnabled(true);//<<----optimizar este codigo
                lbNameStarttime.setEnabled(true);//<<----optimizar este codigo
                itemsEnable(false);
                lbNameEndtime.setEnabled(false);
                lbEndtimeT.setEnabled(false);
                btnOut.setEnabled(false);
                existsName = true;
                cbxCarrera.setSelectedItem(vId_careerv);
                txtNoTeacher.setText(vNoValue);
                String vGenerev = null;
                if (vGender == 1) {
                    vGenerev = "Masculino";
                } else if (vGender == 0) {
                    vGenerev = "Femenino";
                }
            } else {
                lbNameStarttime.setEnabled(true);
                lbStartimeT.setEnabled(true);
                btnIn.setEnabled(true);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
        foundName();
    }
    
    void defaults() {
        cleanNam();
            cleanNum();
            rbFemale.setEnabled(true);
            rbMale.setEnabled(true);
            cbxCarrera.setEnabled(true);
            rbTSU.setEnabled(true);
            rbIngenieria.setEnabled(true);
            cbxActivity.setEnabled(true);
            lbEndtimeT.setEnabled(false);
            lbNameEndtime.setEnabled(false);
            lbNameStarttime.setEnabled(false);
            lbStartimeT.setEnabled(false);
            btnIn.setEnabled(false);
            btnOut.setEnabled(false);
            rbTSU.setSelected(true);
            rbMale.setSelected(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbgGender = new javax.swing.ButtonGroup();
        rbgGrade = new javax.swing.ButtonGroup();
        jPanelStudent = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNameT = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtLastnameT = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtLastname2T = new javax.swing.JTextField();
        btnSearchN = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNoTeacher = new javax.swing.JTextField();
        btnSearchNo = new javax.swing.JButton();
        jdDate = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        rbMale = new javax.swing.JRadioButton();
        rbFemale = new javax.swing.JRadioButton();
        jLabel18 = new javax.swing.JLabel();
        cbxCarrera = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        rbTSU = new javax.swing.JRadioButton();
        rbIngenieria = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cbxActivity = new javax.swing.JComboBox<>();
        lbNameStarttime = new javax.swing.JLabel();
        btnIn = new javax.swing.JButton();
        lbStartimeT = new javax.swing.JFormattedTextField();
        lbNameEndtime = new javax.swing.JLabel();
        lbEndtimeT = new javax.swing.JFormattedTextField();
        btnOut = new javax.swing.JButton();

        jPanelStudent.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(41, 48, 81));
        jLabel2.setText("Nombre:");

        txtNameT.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtNameT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNameTKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(41, 48, 81));
        jLabel3.setText("Apellido Paterno:");

        txtLastnameT.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtLastnameT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLastnameTKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(41, 48, 81));
        jLabel5.setText("Apellido Materno:");

        txtLastname2T.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtLastname2T.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtLastname2TMouseClicked(evt);
            }
        });
        txtLastname2T.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLastname2TKeyTyped(evt);
            }
        });

        btnSearchN.setBackground(new java.awt.Color(255, 255, 255));
        btnSearchN.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnSearchN.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Search.png"))); // NOI18N
        btnSearchN.setText("Buscar por Nombre");
        btnSearchN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel2)
                .addGap(26, 26, 26)
                .addComponent(txtNameT, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLastnameT, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLastname2T, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearchN)
                .addContainerGap(79, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNameT, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtLastnameT, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtLastname2T, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchN, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(41, 48, 81));
        jLabel1.setText("Número de Empleado:");

        txtNoTeacher.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtNoTeacher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNoTeacherMouseClicked(evt);
            }
        });
        txtNoTeacher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoTeacherKeyTyped(evt);
            }
        });

        btnSearchNo.setBackground(new java.awt.Color(255, 255, 255));
        btnSearchNo.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnSearchNo.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchNo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Search.png"))); // NOI18N
        btnSearchNo.setText("Buscar por Número");
        btnSearchNo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnSearchNo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSearchNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchNoActionPerformed(evt);
            }
        });

        jdDate.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtNoTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearchNo, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(jdDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNoTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearchNo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(41, 48, 81));
        jLabel17.setText("Género:");

        rbgGender.add(rbMale);
        rbMale.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        rbMale.setForeground(new java.awt.Color(41, 48, 81));
        rbMale.setText("Masculino");

        rbgGender.add(rbFemale);
        rbFemale.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        rbFemale.setForeground(new java.awt.Color(41, 48, 81));
        rbFemale.setText("Femenino");

        jLabel18.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(41, 48, 81));
        jLabel18.setText("Carrera:");

        cbxCarrera.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        cbxCarrera.setForeground(new java.awt.Color(41, 48, 81));
        cbxCarrera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige tu Carrera" }));

        jLabel19.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(41, 48, 81));
        jLabel19.setText("Grado:");

        rbgGrade.add(rbTSU);
        rbTSU.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        rbTSU.setForeground(new java.awt.Color(41, 48, 81));
        rbTSU.setText("TSU");

        rbgGrade.add(rbIngenieria);
        rbIngenieria.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        rbIngenieria.setForeground(new java.awt.Color(41, 48, 81));
        rbIngenieria.setText("INGENIERÍA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel17)
                .addGap(28, 28, 28)
                .addComponent(rbMale)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbFemale)
                .addGap(42, 42, 42)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbxCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbTSU)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbIngenieria)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(rbMale)
                    .addComponent(rbFemale)
                    .addComponent(jLabel18)
                    .addComponent(cbxCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(rbTSU)
                    .addComponent(rbIngenieria))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(41, 48, 81));
        jLabel8.setText("Actividad:");

        cbxActivity.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        cbxActivity.setForeground(new java.awt.Color(41, 48, 81));
        cbxActivity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige una actividad" }));

        lbNameStarttime.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbNameStarttime.setForeground(new java.awt.Color(41, 48, 81));
        lbNameStarttime.setText("Hora de Entrada:");

        btnIn.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnIn.setForeground(new java.awt.Color(255, 255, 255));
        btnIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Input.png"))); // NOI18N
        btnIn.setText("Registrar Entrada");
        btnIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInActionPerformed(evt);
            }
        });

        lbStartimeT.setForeground(new java.awt.Color(41, 48, 84));
        lbStartimeT.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance())));
        lbStartimeT.setText("00:00:00");
        lbStartimeT.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        lbNameEndtime.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbNameEndtime.setForeground(new java.awt.Color(41, 48, 81));
        lbNameEndtime.setText("Hora de Salida:");

        lbEndtimeT.setForeground(new java.awt.Color(41, 48, 84));
        lbEndtimeT.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance())));
        lbEndtimeT.setText("00:00:00");
        lbEndtimeT.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        btnOut.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnOut.setForeground(new java.awt.Color(255, 255, 255));
        btnOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Output.png"))); // NOI18N
        btnOut.setText("Registrar Salida");
        btnOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(cbxActivity, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(lbNameStarttime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbStartimeT, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnIn)
                .addGap(36, 36, 36)
                .addComponent(lbNameEndtime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbEndtimeT, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnOut)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cbxActivity, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbNameStarttime)
                        .addComponent(btnIn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbStartimeT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbNameEndtime)
                        .addComponent(lbEndtimeT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnOut, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout jPanelStudentLayout = new javax.swing.GroupLayout(jPanelStudent);
        jPanelStudent.setLayout(jPanelStudentLayout);
        jPanelStudentLayout.setHorizontalGroup(
            jPanelStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelStudentLayout.setVerticalGroup(
            jPanelStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelStudentLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchNActionPerformed
        // TODO add your handling code here:
        if (txtLastname2T.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "¡Ingresa tu nombre completo para comensar con la busqueda!");
        } else {
            vName = (txtNameT.getText());
            vLastname = (txtLastnameT.getText());
            vLastname2 = (txtLastname2T.getText());
            searchPersonUserNamOut(vName, vLastname, vLastname2);
        }
    }//GEN-LAST:event_btnSearchNActionPerformed

    private void btnSearchNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchNoActionPerformed
        // TODO add your handling code here:
        vNoValue = txtNoTeacher.getText();
        if (txtNoTeacher.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "¡Ingresa tu numero de empleado para comensar con la busqueda!");
        } else {
            searchPersonUserNumtOut(vNoValue);
        }

    }//GEN-LAST:event_btnSearchNoActionPerformed

    private void btnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActionPerformed
        // TODO add your handling code here:
        date();
        generateIdActivity();//inicia el metodo
        generateIdCareer();
        if (txtNameT.getText().isEmpty() || txtLastnameT.getText().isEmpty()
                || txtLastname2T.getText().isEmpty() || cbxActivity.getSelectedItem().toString()
                == "Elige una actividad") {
            JOptionPane.showMessageDialog(null, "!Por favor llene todos los campos correctamente¡");
        } else {
            String number = txtNoTeacher.getText();
            String name = txtNameT.getText();
            //            Integer matricula = Integer.parseInt(txtMatricula.getText());
            String lastname = txtLastnameT.getText();//<-revisar
            String lastname2 = txtLastname2T.getText();
            int activity = IdActivity;//toma el valor de IdActivity
            if (rbTSU.isSelected()) {
                vGrado = "TSU";
            } else if (rbIngenieria.isSelected()) {
                vGrado = "INGENIERIA";
            }
            String grade = vGrado;//<-revisar
            int career = IdCareer;//<-revisar
            if (rbFemale.isSelected()) {
                vGender = 0;
            } else if (rbMale.isSelected()) {
                vGender = 1;
            }
            int sexo = vGender;//<-revisar
            String date = formatBirthday.format(jdDate.getDate());//<--revisar
            String startTime = hour + ":" + minutes + ":" + secons;
            String endTime = 00 + ":" + 00 + ":" + 00;
            String tipo = "DOCENTE";
            controller.insertPersonUser(number, name, lastname, lastname2, sexo, grade, startTime,
                    endTime, date, career, activity, tipo);
            if (existsName == false && existsNu == false) {
                controller.insertTeacherTeacher(number, name, lastname, lastname2, sexo, grade,
                        career);
            }
            //btnExit.doClick();
            defaults();
        }
    }//GEN-LAST:event_btnInActionPerformed

    private void txtLastname2TKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLastname2TKeyTyped
        // TODO add your handling code here:
        btnSearchNo.setEnabled(false);
        btnSearchN.setEnabled(true);
        ct.UpperCaseLastname2T();
        char keyPressed = evt.getKeyChar();
        if (keyPressed == KeyEvent.VK_ENTER) {
            btnSearchN.doClick();
        }
    }//GEN-LAST:event_txtLastname2TKeyTyped

    private void txtLastname2TMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLastname2TMouseClicked
        // TODO add your handling code here:
        btnSearchNo.setEnabled(false);
        btnSearchN.setEnabled(true);
    }//GEN-LAST:event_txtLastname2TMouseClicked

    void itemsHEnables(boolean value) {
        btnOut.setEnabled(value);
        lbEndtimeT.setEnabled(value);
        lbNameEndtime.setEnabled(value);
        lbNameStarttime.setEnabled(value);
        lbStartimeT.setEnabled(value);//<<----optimizar este codigo
        btnIn.setEnabled(value);
    }
    private void txtNameTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameTKeyTyped
        // TODO add your handling code here:
        itemsEnable(true);
        cbxActivity.setEnabled(true);
        itemsHEnables(false);
        ct.UpperCaseNameT();
        char keyPressed = evt.getKeyChar();
        if (keyPressed == KeyEvent.VK_ENTER) {
            txtLastnameT.requestFocus();
        }
        cleanNam();
    }//GEN-LAST:event_txtNameTKeyTyped

    private void txtLastnameTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLastnameTKeyTyped
        // TODO add your handling code here:
        itemsEnable(true);
        cbxActivity.setEnabled(true);
        itemsHEnables(false);
        ct.UpperCaseLastnameT();
        char keyPressed = evt.getKeyChar();
        if (keyPressed == KeyEvent.VK_ENTER) {
            txtLastname2T.requestFocus();
        }
        cleanNam();
    }//GEN-LAST:event_txtLastnameTKeyTyped

    private void txtNoTeacherKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoTeacherKeyTyped
        //solo permite numeros
        itemsEnable(true);
        cbxActivity.setEnabled(true);
        itemsHEnables(false);

        //solo permite numeros
        cleanNum();
        btnSearchNo.setEnabled(true);
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
        //busca a una persona por medio de su matricula
        char keyPressed = evt.getKeyChar();
        if (keyPressed == KeyEvent.VK_ENTER) {
            btnSearchNo.doClick();
        }
        if (keyPressed == KeyEvent.VK_BACK_SPACE) {
            cleanNum();
        }
    }//GEN-LAST:event_txtNoTeacherKeyTyped

    private void txtNoTeacherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNoTeacherMouseClicked
        // TODO add your handling code here:
        btnSearchNo.setEnabled(true);
        btnSearchN.setEnabled(false);
    }//GEN-LAST:event_txtNoTeacherMouseClicked

    private void btnOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOutActionPerformed
        // TODO add your handling code here:
        date();
        String date = formatBirthday.format(jdDate.getDate());
        String nomber = txtNoTeacher.getText();
        String endTime = hour + ":" + minutes + ":" + secons;
        controller.insertPersonUserEndtime(endTime, nomber, date);
        //btnExit.doClick();
        defaults();
    }//GEN-LAST:event_btnOutActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIn;
    private javax.swing.JButton btnOut;
    private javax.swing.JButton btnSearchN;
    private javax.swing.JButton btnSearchNo;
    public static javax.swing.JComboBox<String> cbxActivity;
    public static javax.swing.JComboBox<String> cbxCarrera;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelStudent;
    private com.toedter.calendar.JDateChooser jdDate;
    public static javax.swing.JFormattedTextField lbEndtimeT;
    private javax.swing.JLabel lbNameEndtime;
    private javax.swing.JLabel lbNameStarttime;
    public static javax.swing.JFormattedTextField lbStartimeT;
    private javax.swing.JRadioButton rbFemale;
    private javax.swing.JRadioButton rbIngenieria;
    private javax.swing.JRadioButton rbMale;
    private javax.swing.JRadioButton rbTSU;
    private javax.swing.ButtonGroup rbgGender;
    private javax.swing.ButtonGroup rbgGrade;
    public static javax.swing.JTextField txtLastname2T;
    public static javax.swing.JTextField txtLastnameT;
    public static javax.swing.JTextField txtNameT;
    public static javax.swing.JTextField txtNoTeacher;
    // End of variables declaration//GEN-END:variables
}
