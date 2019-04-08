/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import static view.Principal.lbClockStart;

/**
 *
 * @author Juan1
 */
public class ClockStart implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        GregorianCalendar time = new GregorianCalendar();
        int hour, minutes, secons;
        hour = time.get(Calendar.HOUR);
        minutes = time.get(Calendar.MINUTE);
        secons = time.get(Calendar.SECOND);
//        lbClockStart.setText(String.valueOf(hour + ":" + minutes + ":" + secons));
    }
}
