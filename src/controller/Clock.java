/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import static view.Registry.*;

import static view.RegistryStudent.lbStartime;
import static view.RegistryStudent.lbEndtime;

import static view.RegistryStudentG.lbStartimeG;
import static view.RegistryStudentG.lbEndtimeG;
import static view.RegistryTeacher.lbStartimeT;
import static view.RegistryTeacher.lbEndtimeT;

/**
 *
 * @author Juan1
 */
public class Clock implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        GregorianCalendar time = new GregorianCalendar();
        int hour, minutes, secons, year, month, day;
        hour = time.get(Calendar.HOUR_OF_DAY);
        minutes = time.get(Calendar.MINUTE);
        secons = time.get(Calendar.SECOND);

        lbEndtime.setText(String.valueOf(hour + ":" + minutes + ":" + secons));
        lbStartime.setText(String.valueOf(hour + ":" + minutes + ":" + secons));
        
        
        lbStartimeT.setText(String.valueOf(hour + ":" + minutes + ":" + secons));
        lbEndtimeT.setText(String.valueOf(hour + ":" + minutes + ":" + secons));

        
        lbStartimeG.setText(String.valueOf(hour + ":" + minutes + ":" + secons));
        lbEndtimeG.setText(String.valueOf(hour + ":" + minutes + ":" + secons));

        
        year = time.get(Calendar.YEAR);
        month = time.get(Calendar.MONTH);
        day = time.get(Calendar.DAY_OF_MONTH);

        lbDate.setText(String.valueOf(day + "-" + (month + 1) + "-" + year));
    }

}
