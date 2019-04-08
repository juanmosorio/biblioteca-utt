/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static view.RegistryStudent.*;
import static view.RegistryStudentG.*;
import static view.RegistryTeacher.*;

/**
 *
 * @author Juan1
 */
public class ControllerTooll {

    public void UpperCaseName() {//vuelve mayuscula la primera letra
        String t = txtName.getText();
        if (t.length() > 0) {
            char pk = t.charAt(0);
            t = Character.toUpperCase(pk) + t.substring(1, t.length());
            txtName.setText(t);
        }
    }

    public void UpperCaseLastname() {//vuelve mayuscula la primera letra
        String t = txtLastname.getText();
        if (t.length() > 0) {
            char pk = t.charAt(0);
            t = Character.toUpperCase(pk) + t.substring(1, t.length());
            txtLastname.setText(t);
        }
    }

    public void UpperCaseLastname2() {//vuelve mayuscula la primera letra
        String t = txtLastname2.getText();
        if (t.length() > 0) {
            char pk = t.charAt(0);
            t = Character.toUpperCase(pk) + t.substring(1, t.length());
            txtLastname2.setText(t);
        }
    }

    /////////////////////GRUPO/////////////////
    public void UpperCaseNameG() {//vuelve mayuscula la primera letra
        String t = txtNameG.getText();
        if (t.length() > 0) {
            char pk = t.charAt(0);
            t = Character.toUpperCase(pk) + t.substring(1, t.length());
            txtNameG.setText(t);
        }
    }

    public void UpperCaseLastnameG() {//vuelve mayuscula la primera letra
        String t = txtLastnameG.getText();
        if (t.length() > 0) {
            char pk = t.charAt(0);
            t = Character.toUpperCase(pk) + t.substring(1, t.length());
            txtLastnameG.setText(t);
        }
    }

    public void UpperCaseLastname2G() {//vuelve mayuscula la primera letra
        String t = txtLastname2G.getText();
        if (t.length() > 0) {
            char pk = t.charAt(0);
            t = Character.toUpperCase(pk) + t.substring(1, t.length());
            txtLastname2G.setText(t);
        }
    }

    /////////////////////TEACHER/////////////////
    public void UpperCaseNameT() {//vuelve mayuscula la primera letra
        String t = txtNameT.getText();
        if (t.length() > 0) {
            char pk = t.charAt(0);
            t = Character.toUpperCase(pk) + t.substring(1, t.length());
            txtNameT.setText(t);
        }
    }

    public void UpperCaseLastnameT() {//vuelve mayuscula la primera letra
        String t = txtLastnameT.getText();
        if (t.length() > 0) {
            char pk = t.charAt(0);
            t = Character.toUpperCase(pk) + t.substring(1, t.length());
            txtLastnameT.setText(t);
        }
    }

    public void UpperCaseLastname2T() {//vuelve mayuscula la primera letra
        String t = txtLastname2T.getText();
        if (t.length() > 0) {
            char pk = t.charAt(0);
            t = Character.toUpperCase(pk) + t.substring(1, t.length());
            txtLastname2T.setText(t);
        }
    }
}
