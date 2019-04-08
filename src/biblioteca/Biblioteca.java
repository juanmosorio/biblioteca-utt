/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import controller.ClockStart;
import view.Menu;
import model.Reports;
import view.Output;
import view.Settings;

/**
 *
 * @author JUAN
 */
public class Biblioteca {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Menu view = new Menu();
        view.setVisible(true);
        view.setLocationRelativeTo(null);

//        Settings view = new Settings();
//        view.setVisible(true);
//        view.setLocationRelativeTo(null);

//        ClockStart c = new ClockStart();
    }

}
