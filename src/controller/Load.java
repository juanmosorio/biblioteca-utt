/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JProgressBar;

/**
 *
 * @author FÉLIX GARCÍA
 */
public class Load extends Thread {

    JProgressBar progreso;

    public Load(JProgressBar progreso) {
        super();
        this.progreso = progreso;
    }

    public void run() {
        for (int i = 1; i <= 100; i++) {
            progreso.setValue(i);
            pausa(50);
        }
        
    }

    public void pausa(int mlSeg) {
        try {
            Thread.sleep(mlSeg);
        } catch (Exception e) {
            
        }
    }
}
