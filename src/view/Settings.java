/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerPersonStudent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Juan1
 */
public class Settings extends javax.swing.JFrame {

    /**
     * Creates new form Settings
     */
    SettingsUser setUser = new SettingsUser();
    SettingsCareer setCareer = new SettingsCareer();
    SettingsActivity setActivity = new SettingsActivity();
    Dimension WH = Toolkit.getDefaultToolkit().getScreenSize();
    ControllerPersonStudent controller = new ControllerPersonStudent();

    public Settings() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/img/icon.png")).getImage());
        rbModUser.setSelected(true);
        setActivity.setVisible(false);
        setCareer.setVisible(false);
        setUser.setVisible(true);
        setUser.setSize(WH);
        setUser.setLocation(0, 0);
        container.add(setUser, BorderLayout.CENTER);
        container.revalidate();
        initItems();
    }

    void initItems() {
        SettingsUser.txtUser.setEnabled(false);
        SettingsUser.pswPass.setEnabled(false);
        SettingsUser.pswPass1.setEnabled(false);
        SettingsUser.btnAceptUserU.setEnabled(false);
        SettingsUser.btnCancelUserU.setEnabled(false);
        rbUpdate.setEnabled(false);
        rbDelete.setEnabled(false);
    }

    void Selected() {
        if (rbModUser.isSelected()) {
            setActivity.setVisible(false);
            setCareer.setVisible(false);
            setUser.setVisible(true);
            setUser.setSize(WH);
            setUser.setLocation(0, 0);
            container.add(setUser, BorderLayout.CENTER);
            container.revalidate();
        } else if (rbModAct.isSelected()) {
            setUser.setVisible(false);
            setCareer.setVisible(false);
            setActivity.setVisible(true);
            setActivity.setSize(WH);
            setActivity.setLocation(0, 0);
            container.add(setActivity, BorderLayout.CENTER);
            container.revalidate();
        } else if (rbModCarr.isSelected()) {
            setUser.setVisible(false);
            setActivity.setVisible(false);
            setCareer.setVisible(true);
            setCareer.setSize(WH);
            setCareer.setLocation(0, 0);
            container.add(setCareer, BorderLayout.CENTER);
            container.revalidate();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        rbInsert = new javax.swing.JRadioButton();
        rbUpdate = new javax.swing.JRadioButton();
        rbDelete = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        container = new javax.swing.JPanel();
        rbModUser = new javax.swing.JRadioButton();
        rbModAct = new javax.swing.JRadioButton();
        rbModCarr = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar - Biblioteca");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(41, 48, 81));

        rbInsert.setBackground(new java.awt.Color(41, 48, 81));
        buttonGroup2.add(rbInsert);
        rbInsert.setForeground(new java.awt.Color(255, 255, 255));
        rbInsert.setText("Ingresar");
        rbInsert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add.png"))); // NOI18N
        rbInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbInsertActionPerformed(evt);
            }
        });

        rbUpdate.setBackground(new java.awt.Color(41, 48, 81));
        buttonGroup2.add(rbUpdate);
        rbUpdate.setForeground(new java.awt.Color(255, 255, 255));
        rbUpdate.setText("Modificar");
        rbUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/create_white.png"))); // NOI18N
        rbUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbUpdateActionPerformed(evt);
            }
        });

        rbDelete.setBackground(new java.awt.Color(41, 48, 81));
        buttonGroup2.add(rbDelete);
        rbDelete.setForeground(new java.awt.Color(255, 255, 255));
        rbDelete.setText("Eliminar");
        rbDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        rbDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDeleteActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        container.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );

        rbModUser.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rbModUser);
        rbModUser.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        rbModUser.setForeground(new java.awt.Color(41, 48, 81));
        rbModUser.setText("Usuario");
        rbModUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit_usuario.png"))); // NOI18N
        rbModUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbModUserActionPerformed(evt);
            }
        });

        rbModAct.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rbModAct);
        rbModAct.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        rbModAct.setForeground(new java.awt.Color(41, 48, 81));
        rbModAct.setText("Actividad");
        rbModAct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit_activity.png"))); // NOI18N
        rbModAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbModActActionPerformed(evt);
            }
        });

        rbModCarr.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rbModCarr);
        rbModCarr.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        rbModCarr.setForeground(new java.awt.Color(41, 48, 81));
        rbModCarr.setText("Carrera");
        rbModCarr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/idt_career.png"))); // NOI18N
        rbModCarr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbModCarrActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbModUser)
                .addGap(42, 42, 42)
                .addComponent(rbModAct)
                .addGap(45, 45, 45)
                .addComponent(rbModCarr)
                .addGap(47, 47, 47))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbModUser)
                    .addComponent(rbModAct)
                    .addComponent(rbModCarr))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(rbInsert)
                        .addGap(45, 45, 45)
                        .addComponent(rbUpdate)
                        .addGap(51, 51, 51)
                        .addComponent(rbDelete))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbInsert)
                    .addComponent(rbUpdate)
                    .addComponent(rbDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbModUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbModUserActionPerformed
        // TODO add your handling code here:
        Selected();
        clean();
    }//GEN-LAST:event_rbModUserActionPerformed

    private void rbModActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbModActActionPerformed
        // TODO add your handling code here:
        Selected();
        clean();
    }//GEN-LAST:event_rbModActActionPerformed

    private void rbModCarrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbModCarrActionPerformed
        // TODO add your handling code here:
        Selected();
        clean();
    }//GEN-LAST:event_rbModCarrActionPerformed

    void selectRowUser() {
        int filasel = SettingsUser.jTable1.getSelectedRow();
        String userName = (String) SettingsUser.jTable1.getValueAt(filasel, 0);
        SettingsUser.txtUser.setText(userName);
    }

    void selectRowActivity() {
        int filasel = SettingsActivity.jTable1.getSelectedRow();
        String activity = (String) SettingsActivity.jTable1.getValueAt(filasel, 0);
        SettingsActivity.txtActivity.setText(activity);
    }

    void selectRowCareer() {
        int filasel = SettingsCareer.jTable1.getSelectedRow();
        String career = (String) SettingsCareer.jTable1.getValueAt(filasel, 0);
        SettingsCareer.txtCareer.setText(career);
    }

    void clean() {
        setActivity.clean();
        setUser.clean();
        setCareer.clean();
    }
    private void rbInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbInsertActionPerformed
        // TODO add your handling code here:
        if (rbModUser.isSelected()) {
            SettingsUser.txtUser.setText("");
            SettingsUser.pswPass.setText("");
            SettingsUser.pswPass1.setText("");

            SettingsUser.txtUser.setEnabled(true);
            SettingsUser.pswPass.setEnabled(true);
            SettingsUser.pswPass1.setEnabled(true);
            SettingsUser.btnAceptUserU.setEnabled(true);
            SettingsUser.btnCancelUserU.setEnabled(true);
            SettingsUser.jTable1.setEnabled(false);
        } else if (rbModAct.isSelected()) {
            SettingsActivity.txtActivity.setText("");

            SettingsActivity.txtActivity.setEnabled(true);
            SettingsActivity.btnAceptUserU.setEnabled(true);
            SettingsActivity.btnCancelUserU.setEnabled(true);
            SettingsActivity.jTable1.setEnabled(false);
        } else if (rbModCarr.isSelected()) {
            SettingsCareer.txtCareer.setText("");

            SettingsCareer.txtCareer.setEnabled(true);
            SettingsCareer.btnAceptUserU.setEnabled(true);
            SettingsCareer.btnCancelUserU.setEnabled(true);
            SettingsCareer.jTable1.setEnabled(false);
        }
    }//GEN-LAST:event_rbInsertActionPerformed

    private void rbUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbUpdateActionPerformed
        // TODO add your handling code here:
        rbDelete.setEnabled(false);
        if (rbModUser.isSelected()) {
            selectRowUser();
//            SettingsUser.txtUser.setEnabled(true);
            SettingsUser.pswPass.setEnabled(true);
            SettingsUser.pswPass1.setEnabled(true);
            SettingsUser.btnAceptUserU.setEnabled(true);
            SettingsUser.btnCancelUserU.setEnabled(true);
            SettingsUser.jTable1.setEnabled(false);
        } else if (rbModAct.isSelected()) {
            selectRowActivity();
            SettingsActivity.txtActivity.setEnabled(true);
            SettingsActivity.btnAceptUserU.setEnabled(true);
            SettingsActivity.btnCancelUserU.setEnabled(true);
            SettingsActivity.jTable1.setEnabled(false);
        } else if (rbModCarr.isSelected()) {
            selectRowCareer();
            SettingsCareer.txtCareer.setEnabled(true);
            SettingsCareer.btnAceptUserU.setEnabled(true);
            SettingsCareer.btnCancelUserU.setEnabled(true);
            SettingsCareer.jTable1.setEnabled(false);
        }
    }//GEN-LAST:event_rbUpdateActionPerformed

    private void rbDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDeleteActionPerformed
        // TODO add your handling code here:
        rbUpdate.setEnabled(false);
        if (rbModUser.isSelected()) {
            int filasel = SettingsUser.jTable1.getSelectedRow();
            String userName = (String) SettingsUser.jTable1.getValueAt(filasel, 0);
            Object[] botones = {"Aceptar", " Cancelar"};
            int option = JOptionPane.showOptionDialog(null, "¿Desea eliminar a " + userName + "?", "Advertencia",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);
            if (option == 0) {
                controller.deleteAdmin(userName);
            }
            setUser.clean();
            controller.fillTalbleAdmin(SettingsUser.jTable1);
        } else if (rbModAct.isSelected()) {
            int filasel = SettingsActivity.jTable1.getSelectedRow();
            String activity = (String) SettingsActivity.jTable1.getValueAt(filasel, 0);
            Object[] botones = {"Aceptar", " Cancelar"};
            int option = JOptionPane.showOptionDialog(null, "¿Desea eliminar " + activity + "?", "Advertencia",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);
            if (option == 0) {
                controller.deleteActivity(activity);
            }
            setActivity.clean();
            controller.fillTalbleActivity(SettingsActivity.jTable1);
        } else if (rbModCarr.isSelected()) {
            int filasel = SettingsCareer.jTable1.getSelectedRow();
            String career = (String) SettingsCareer.jTable1.getValueAt(filasel, 0);
            Object[] botones = {"Aceptar", " Cancelar"};
            int option = JOptionPane.showOptionDialog(null, "¿Desea eliminar " + career + "?", "Advertencia",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);
            if (option == 0) {
                controller.deleteCareer(career);
            }
            setCareer.clean();
            controller.fillTalbleCareer(SettingsCareer.jTable1);
        }
    }//GEN-LAST:event_rbDeleteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Settings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JPanel container;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JRadioButton rbDelete;
    public static javax.swing.JRadioButton rbInsert;
    private javax.swing.JRadioButton rbModAct;
    private javax.swing.JRadioButton rbModCarr;
    private javax.swing.JRadioButton rbModUser;
    public static javax.swing.JRadioButton rbUpdate;
    // End of variables declaration//GEN-END:variables
}
