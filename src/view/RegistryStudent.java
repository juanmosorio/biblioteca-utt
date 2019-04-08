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
public class RegistryStudent extends javax.swing.JPanel {

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
    String vMatValue = "";
    String vNameActivity = "";

    boolean existsM, existsN = false;

    /**
     * Creates new form RegistryStudent
     */
    public RegistryStudent() {
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
        Timer timer = new Timer(1, new Clock());
        timer.start();
        btnSearchN.setEnabled(false);
        btnSearchM.setEnabled(false);
        rbMale.setSelected(true);
        rbTSU.setSelected(true);

        btnOut.setEnabled(false);
        lbEndtime.setEnabled(false);
        lbNameEndtime.setEnabled(false);
        lbNameStarttime.setEnabled(false);
        lbStartime.setEnabled(false);
        btnIn.setEnabled(false);
    }

    void colorsInterface() {
        Color color = new Color(41, 48, 81);
        btnSearchM.setBackground(color);
        btnSearchN.setBackground(color);
        btnIn.setBackground(color);
        btnOut.setBackground(color);

        cbxActivity.setBackground(Color.WHITE);
        cbxCarrera.setBackground(Color.WHITE);

        rbMale.setBackground(color.white);
        rbFemale.setBackground(color.white);
        rbTSU.setBackground(color.white);
        rbIngenieria.setBackground(color.white);

        txtMatricula.setBorder(BorderFactory.createLineBorder(color));
        txtName.setBorder(BorderFactory.createLineBorder(color));
        txtLastname.setBorder(BorderFactory.createLineBorder(color));
        txtLastname2.setBorder(BorderFactory.createLineBorder(color));
    }

    void cleanM() {
        txtName.setText("");
        txtLastname.setText("");
        txtLastname2.setText("");
        rbMale.isSelected();
        cbxCarrera.setSelectedItem("Elige tu Carrera");
        cbxActivity.setSelectedItem("Elige una actividad");
        rbTSU.isSelected();
    }

    void cleanN() {
        txtMatricula.setText("");
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
        if (vGrado.equals("TSU")) {
            rbTSU.setSelected(true);
        } else if (vGrado.equals("INGENIERIA")) {
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

    void foundM() {
        if (existsM == false) {
            JOptionPane.showMessageDialog(null, "Matricula no encontrada!\nBusque por nombre o intente de nuevo!");
            txtMatricula.setText("");
            txtName.requestFocus();

        } else {
            txtName.setText(vName);
            txtLastname.setText(vLastname);
            txtLastname2.setText(vLastname2);
            loadrbGender();
            cbxCarrera.setSelectedItem(vId_careerv);
            loadrbGrade();
        }
    }

    void foundN() {
        if (existsN == false) {
            txtMatricula.setText("");
            JOptionPane.showMessageDialog(null, "Nombre no encontrado!\nRellene los campos siguientes!");;
            rbMale.isSelected();
            cbxCarrera.setSelectedItem("Elige tu Carrera");
            rbTSU.isSelected();
        } else {
            loadrbGender();
            loadrbGrade();
        }
    }
    
    void defaults() {
        cleanM();
        cleanN();
        itemsEnable(true);
        cbxActivity.setEnabled(true);
        btnOut.setEnabled(false);
        btnIn.setEnabled(false);
        lbNameStarttime.setEnabled(false);
        lbStartime.setEnabled(false);
        lbNameEndtime.setEnabled(false);
        lbEndtime.setEnabled(false);
        rbTSU.setSelected(true);
        rbMale.setSelected(true);
    }

    void searchPersonUserMatOut(String vMatValue) {//buca por medio de la matricula<--revisar
        existsM = false;
        String date = formatBirthday.format(jdDate.getDate());//<--revisar
        String tipo = "ESTUDIANTE";
        ResultSet result = controller.searchPersonUserMatOut(vMatValue, date, tipo);
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
                lbEndtime.setEnabled(true);//<<----optimizar este codigo
                lbNameEndtime.setEnabled(true);//<<----optimizar este codigo
                cbxActivity.setEnabled(false);
                itemsEnable(false);//<-toma el vaor de false
                existsM = true;
                cbxActivity.setSelectedItem(vNameActivity);
                foundM();
            } else {
                searchPersonUserMat(vMatValue);
            }
        } catch (SQLException ex) {//<--revisar
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }

    }

    void searchPersonUserMat(String vMatValue) {//buca por medio de la matricula
        existsM = false;
        ResultSet result = controller.searchPersonUserMat(vMatValue);
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
                lbStartime.setEnabled(true);//<<----optimizar este codigo
                lbNameStarttime.setEnabled(true);//<<----optimizar este codigo
                cbxActivity.setEnabled(true);
                itemsEnable(false);
                existsM = true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
        foundM();
    }

    void searchPersonUserNamOut(String vName, String vLastname, String vLastname2) {//busca por medio del nombre
        String tipo = "ESTUDIANTE";
        existsN = false;
        String date = formatBirthday.format(jdDate.getDate());//<--revisar
        ResultSet result = controller.searchPersonUserNamOut(vName, vLastname, vLastname2, date, tipo);
        try {
            if (result.next()) {
                vMatValue = result.getString("matricula");
                vGender = result.getInt("genero");
                vGrado = result.getString("grado");
                vId_career = result.getInt("id_carrera");
                vId_careerv = result.getString("nombre_carrera");
                vNameActivity = result.getString("nombre_actividad");
                btnOut.setEnabled(true);//<<----optimizar este codigo
                lbEndtime.setEnabled(true);//<<----optimizar este codigo
                lbNameEndtime.setEnabled(true);//<<----optimizar este codigo
                cbxActivity.setEnabled(false);
                itemsEnable(false);
                existsN = true;
                txtMatricula.setText(vMatValue);
                cbxCarrera.setSelectedItem(vId_careerv);
                String vGenerev = null;
                if (vGender == 1) {
                    vGenerev = "Masculino";
                } else if (vGender == 0) {
                    vGenerev = "Femenino";
                }
                cbxActivity.setSelectedItem(vNameActivity);
                foundN();
            } else {
                searchPersonUserNam(vName, vLastname, vLastname2);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
    }

    void searchPersonUserNam(String vName, String vLastname, String vLastname2) {//busca por medio del nombre
        existsN = false;
        ResultSet result = controller.searchPersonUserNam(vName, vLastname, vLastname2);
        try {
            if (result.next()) {
                vMatValue = result.getString("matricula");
                vGender = result.getInt("genero");
                vGrado = result.getString("grado");
                vId_career = result.getInt("id_carrera");
                vId_careerv = result.getString("nombre_carrera");
                btnIn.setEnabled(true);//<<----optimizar este codigo
                lbStartime.setEnabled(true);//<<----optimizar este codigo
                lbNameStarttime.setEnabled(true);//<<----optimizar este codigo
                itemsEnable(false);
                lbNameEndtime.setEnabled(false);
                lbEndtime.setEnabled(false);
                btnOut.setEnabled(false);
                cbxActivity.setEnabled(true);
                existsN = true;

                cbxCarrera.setSelectedItem(vId_careerv);
                txtMatricula.setText(vMatValue);
                String vGenerev = null;
                if (vGender == 1) {
                    vGenerev = "Masculino";
                } else if (vGender == 0) {
                    vGenerev = "Femenino";
                }
            } else {
                lbNameStarttime.setEnabled(true);
                lbStartime.setEnabled(true);
                btnIn.setEnabled(true);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
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

        rbgGender = new javax.swing.ButtonGroup();
        rbgGrade = new javax.swing.ButtonGroup();
        jPanelStudent = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtLastname = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtLastname2 = new javax.swing.JTextField();
        btnSearchN = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMatricula = new javax.swing.JTextField();
        btnSearchM = new javax.swing.JButton();
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
        lbStartime = new javax.swing.JFormattedTextField();
        lbNameEndtime = new javax.swing.JLabel();
        lbEndtime = new javax.swing.JFormattedTextField();
        btnOut = new javax.swing.JButton();

        jPanelStudent.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(41, 48, 81));
        jLabel2.setText("Nombre:");

        txtName.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNameKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(41, 48, 81));
        jLabel3.setText("Apellido Paterno:");

        txtLastname.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtLastname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLastnameKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(41, 48, 81));
        jLabel5.setText("Apellido Materno:");

        txtLastname2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtLastname2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtLastname2MouseClicked(evt);
            }
        });
        txtLastname2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLastname2KeyTyped(evt);
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
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLastname, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLastname2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtLastname, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtLastname2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchN, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(41, 48, 81));
        jLabel1.setText("Matrícula:");

        txtMatricula.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtMatricula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMatriculaMouseClicked(evt);
            }
        });
        txtMatricula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMatriculaKeyTyped(evt);
            }
        });

        btnSearchM.setBackground(new java.awt.Color(255, 255, 255));
        btnSearchM.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnSearchM.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Search.png"))); // NOI18N
        btnSearchM.setText("Buscar por Matrícula");
        btnSearchM.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnSearchM.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSearchM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchMActionPerformed(evt);
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
                .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearchM, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearchM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        lbStartime.setForeground(new java.awt.Color(41, 48, 84));
        lbStartime.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance())));
        lbStartime.setText("00:00:00");
        lbStartime.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        lbNameEndtime.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbNameEndtime.setForeground(new java.awt.Color(41, 48, 81));
        lbNameEndtime.setText("Hora de Salida:");

        lbEndtime.setForeground(new java.awt.Color(41, 48, 84));
        lbEndtime.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance())));
        lbEndtime.setText("00:00:00");
        lbEndtime.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

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
                .addComponent(lbStartime, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnIn)
                .addGap(36, 36, 36)
                .addComponent(lbNameEndtime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbEndtime, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(lbStartime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbNameEndtime)
                        .addComponent(lbEndtime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        if (txtLastname2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "¡Ingresa tu nombre completo para comensar con la busqueda!");
        } else {
            vName = (txtName.getText());
            vLastname = (txtLastname.getText());
            vLastname2 = (txtLastname2.getText());
            searchPersonUserNamOut(vName, vLastname, vLastname2);
        }
    }//GEN-LAST:event_btnSearchNActionPerformed

    private void btnSearchMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchMActionPerformed
        // TODO add your handling code here:
        vMatValue = txtMatricula.getText();
        if (txtMatricula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "¡Ingresa tu matricula para comensar con la busqueda!");
        } else {
            searchPersonUserMatOut(vMatValue);
        }

    }//GEN-LAST:event_btnSearchMActionPerformed

    private void btnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActionPerformed
        // TODO add your handling code here:
        date();
        generateIdActivity();//inicia el metodo
        generateIdCareer();
        if (txtName.getText().isEmpty() || txtLastname.getText().isEmpty()
                || txtLastname2.getText().isEmpty() || cbxActivity.getSelectedItem().toString()
                == "Elige una actividad") {
            JOptionPane.showMessageDialog(null, "!Por favor llene todos los campos correctamente¡");
        } else {
            String matricula = txtMatricula.getText();
            String name = txtName.getText();
            String lastname = txtLastname.getText();//<-revisar
            String lastname2 = txtLastname2.getText();
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
            String tipo = "ESTUDIANTE";
            controller.insertPersonUser(matricula, name, lastname, lastname2, sexo, grade, startTime, endTime,
                    date, career, activity, tipo);
            if (existsN == false && existsM == false) {
                controller.insertPruebaStudent(matricula, name, lastname, lastname2, sexo, grade,
                        career);
            }
            //btnExit.doClick();
            defaults();
        }
    }//GEN-LAST:event_btnInActionPerformed

    private void txtLastname2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLastname2KeyTyped
        // TODO add your handling code here:
        cleanN();
        itemsEnable(true);
        cbxActivity.setEnabled(true);
        btnOut.setEnabled(false);
        lbEndtime.setEnabled(false);
        lbNameEndtime.setEnabled(false);
        lbNameStarttime.setEnabled(false);
        lbStartime.setEnabled(false);//<<----optimizar este codigo
        btnIn.setEnabled(false);
        btnSearchM.setEnabled(false);
        btnSearchN.setEnabled(true);
        ct.UpperCaseLastname2();
        char keyPressed = evt.getKeyChar();
        if (keyPressed == KeyEvent.VK_ENTER) {
            btnSearchN.doClick();
        }

    }//GEN-LAST:event_txtLastname2KeyTyped

    private void txtLastname2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLastname2MouseClicked
        // TODO add your handling code here:
        btnSearchM.setEnabled(false);
        btnSearchN.setEnabled(true);
    }//GEN-LAST:event_txtLastname2MouseClicked

    void itemsHEnables(boolean value) {
        btnOut.setEnabled(value);
        lbEndtime.setEnabled(value);
        lbNameEndtime.setEnabled(value);
        lbNameStarttime.setEnabled(value);
        lbStartime.setEnabled(value);//<<----optimizar este codigo
        btnIn.setEnabled(value);
    }
    private void txtNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyTyped
        // TODO add your handling code here:
        itemsEnable(true);
        cbxActivity.setEnabled(true);
        itemsHEnables(false);
        ct.UpperCaseName();
        char keyPressed = evt.getKeyChar();
        if (keyPressed == KeyEvent.VK_ENTER) {
            txtLastname.requestFocus();
        }
        cleanN();
    }//GEN-LAST:event_txtNameKeyTyped

    private void txtLastnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLastnameKeyTyped
        // TODO add your handling code here:
        itemsEnable(true);
        cbxActivity.setEnabled(true);
        itemsHEnables(false);
        ct.UpperCaseLastname();
        char keyPressed = evt.getKeyChar();
        if (keyPressed == KeyEvent.VK_ENTER) {
            txtLastname2.requestFocus();
        }
        cleanN();
    }//GEN-LAST:event_txtLastnameKeyTyped

    private void txtMatriculaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatriculaKeyTyped

        itemsEnable(true);
        cbxActivity.setEnabled(true);
        itemsHEnables(false);

        //solo permite numeros
        cleanM();
        btnSearchM.setEnabled(true);
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
        //busca a una persona por medio de su matricula
        char keyPressed = evt.getKeyChar();
        if (keyPressed == KeyEvent.VK_ENTER) {
            btnSearchM.doClick();
        }
        if (keyPressed == KeyEvent.VK_BACK_SPACE) {
            cleanM();
        }
    }//GEN-LAST:event_txtMatriculaKeyTyped

    private void txtMatriculaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMatriculaMouseClicked
        // TODO add your handling code here:
        btnSearchM.setEnabled(true);
        btnSearchN.setEnabled(false);
    }//GEN-LAST:event_txtMatriculaMouseClicked

    private void btnOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOutActionPerformed
        // TODO add your handling code here:
        date();
        String date = formatBirthday.format(jdDate.getDate());
        String matricula = txtMatricula.getText();
        String endTime = hour + ":" + minutes + ":" + secons;
        controller.insertPersonUserEndtime(endTime, matricula, date);
        //btnExit.doClick();
        defaults();
    }//GEN-LAST:event_btnOutActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIn;
    private javax.swing.JButton btnOut;
    private javax.swing.JButton btnSearchM;
    private javax.swing.JButton btnSearchN;
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
    public static javax.swing.JFormattedTextField lbEndtime;
    private javax.swing.JLabel lbNameEndtime;
    private javax.swing.JLabel lbNameStarttime;
    public static javax.swing.JFormattedTextField lbStartime;
    private javax.swing.JRadioButton rbFemale;
    private javax.swing.JRadioButton rbIngenieria;
    private javax.swing.JRadioButton rbMale;
    private javax.swing.JRadioButton rbTSU;
    private javax.swing.ButtonGroup rbgGender;
    private javax.swing.ButtonGroup rbgGrade;
    public static javax.swing.JTextField txtLastname;
    public static javax.swing.JTextField txtLastname2;
    public static javax.swing.JTextField txtMatricula;
    public static javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables

}
