/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.PersonStudentDao;
import view.SplashScreen;

/**
 *
 * @author Juan1
 */
public class ControllerPersonStudent {

    PersonStudentDao model = new PersonStudentDao();

    public ResultSet searchPersonUserMat(String matValue) {//busca a la persona por medio de la matricula
        ResultSet result = model.searchPersonUserMat(matValue);
        return result;
    }

    public ResultSet searchPersonUserNam(String namValue, String lanValue, String lan2Value) {//busca a la persona por medio de el nombre
        ResultSet result = model.searchPersonUserNam(namValue, lanValue, lan2Value);
        return result;
    }

    public ResultSet generateIdActivity(String value) {//genera el id de actividad
        ResultSet rs = model.generateIdActivity(value);
        return rs;
    }

    public ResultSet generateIdCareer(String value) {//genera el id de actividad
        ResultSet rs = model.generateIdCareer(value);
        return rs;
    }

    public void insertPersonUser(String matricula, String name, String lastname, String lastname2, int sexo,
            String grade, String startTime, String endTime, String date, int career, int activity, String tipo) {
        String messageResult = model.insertPersonUser(matricula, name, lastname, lastname2, sexo, grade,
                startTime, endTime, date, career, activity, tipo);//guarda los datos de la base de datos
        if (messageResult != null) {
            SplashScreen view = new SplashScreen(messageResult);
            view.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Registro Erroneo ");
        }
    }

    public void insertPruebaStudent(String matricula, String name, String lastname, String lastname2,
            int sexo, String grade, int career) {
        String messageResult = model.insertPruebaStudent(matricula, name, lastname, lastname2, sexo,
                grade, career);//guarda los datos de la base de datos
        if (messageResult != null) {
            SplashScreen view = new SplashScreen(messageResult);
            view.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Registro Erroneo ");
        }
    }

    //fin de individual
////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //inicio de grupo
    public void insertPersonStudentG(String name, String lastname, String lastname2, int career,
            String grade, int activity, int noH, int noM, int total, String startTime,
            String endTime, String date, String tipo) {
        String messageResult = model.insertPersonStudentG(name, lastname, lastname2, career, grade,
                activity, noH, noM, total, startTime, endTime, date, tipo);//guarda los datos de la base de datos
        if (messageResult != null) {
            SplashScreen view = new SplashScreen(messageResult);
            view.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Registro Erroneo ");
        }
    }

    ///////////////////////////////////////////////////////////////
    //docente
//borrado
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ResultSet searchPersonUserMatOut(String matValue, String date, String tipo) {//busca a la persona por medio de la matricula
        ResultSet result = model.searchPersonUserMatOut(matValue, date, tipo);
        return result;
    }

    ///////////////////////////////////////////////////////////////////////////7
    ////////////////////<-inserta la hora de entrada
    public void insertPersonUserEndtime(String endTime, String matValue, String date) {
        String messageResult = model.insertPersonUserEndtime(endTime, matValue, date);
        if (messageResult != null) {
            SplashScreen view = new SplashScreen(messageResult);
            view.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Registro Erroneo ");
        }
    }
    ///////////////////////////////////////////////////////////////////////////7
    ////////////////////<-inserta la hora de entrada

    public ResultSet searchPersonUserNamOut(String namValue, String lanValue, String lan2Value,
            String date, String tipo) {//busca a la persona por medio de el nombre
        ResultSet result = model.searchPersonUserNamOut(namValue, lanValue, lan2Value, date, tipo);
        return result;
    }

    /////grupo/////////////////////7
    public ResultSet searchGroupStudentOut(String namValue, String lanValue, String lan2Value, String date) {//busca a la persona por medio de el nombre
        ResultSet result = model.searchGroupStudentOut(namValue, lanValue, lan2Value, date);
        return result;
    }

    public ResultSet countM(String namValue, String lanValue, String lan2Value, String date) {//busca a la persona por medio de el nombre
        ResultSet result = model.countM(namValue, lanValue, lan2Value, date);
        return result;
    }

    public ResultSet countF(String namValue, String lanValue, String lan2Value, String date) {//busca a la persona por medio de el nombre
        ResultSet result = model.countF(namValue, lanValue, lan2Value, date);
        return result;
    }

    public ResultSet searchGroupStudent(String namValue, String lanValue, String lan2Value) {//busca a la persona por medio de el nombre
        ResultSet result = model.searchGroupStudent(namValue, lanValue, lan2Value);
        return result;
    }

    public void groupStudentEndtime(String name, String lastname, String lastname2, String endTime, String date) {
        String messageResult = model.groupStudentEndtime(name, lastname, lastname2, endTime, date);
        if (messageResult != null) {
            SplashScreen view = new SplashScreen(messageResult);
            view.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Registro Erroneo ");
        }
    }

    //////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////Teacher//////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ResultSet searchPersonTeacherNum(String matValue) {//busca a la persona por medio de la matricula
        ResultSet result = model.searchPersonTeacherNum(matValue);
        return result;
    }

    public ResultSet searchPersonTeacherNam(String namValue, String lanValue, String lan2Value) {//busca a la persona por medio de el nombre
        ResultSet result = model.searchPersonTeacherNam(namValue, lanValue, lan2Value);
        return result;
    }

    public void insertTeacherTeacher(String no, String name, String lastname, String lastname2,
            int sexo, String grade, int career) {
        String messageResult = model.insertTeacherTeacher(no, name, lastname, lastname2, sexo,
                grade, career);//guarda los datos de la base de datos
        if (messageResult != null) {
            SplashScreen view = new SplashScreen(messageResult);
            view.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Registro Erroneo ");
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////LOGIN///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public ResultSet login(String username, String password) {//busca a la persona por medio de el nombre
        ResultSet result = model.login(username, password);
        return result;
    }

    public ResultSet loginAdmin(String username, String password) {//busca a la persona por medio de el nombre
        ResultSet result = model.loginAdmin(username, password);
        return result;
    }
    public int numRows;

    public void fillTalble(JTable table) {
        DefaultTableModel modelTable = new DefaultTableModel();

        table.setModel(modelTable);//Obtiene los datos de la tabla

        modelTable.addColumn("Nombre");//Agrega el nombre a las cabezeras
        modelTable.addColumn("Apellido Paterno");
        modelTable.addColumn("Apellido Materno");
        modelTable.addColumn("Genero");
        modelTable.addColumn("Grado");
        modelTable.addColumn("Entrada");
        modelTable.addColumn("Salida");
        modelTable.addColumn("Actividad");

        Object[] column = new Object[8];
        numRows = model.listPerson().size();

        for (int i = 0; i < numRows; i++) {
            column[0] = model.listPerson().get(i).getName();//Agrega dni al array columna[0]
            column[1] = model.listPerson().get(i).getLastname();//Agrega lastName al array columna[0]
            column[2] = model.listPerson().get(i).getLastname2();//Agrega name al array columna[0]
            column[3] = model.listPerson().get(i).getSexo();//Agrega date al array columna[0]
            column[4] = model.listPerson().get(i).getGrade();//Agrega place al array columna[0]
            column[5] = model.listPerson().get(i).getStartTime();//Agrega name al array columna[0]
            column[6] = model.listPerson().get(i).getEndTime();//Agrega date al array columna[0]
            column[7] = model.listPerson().get(i).getActivity();//Agrega place al array columna[0]
            modelTable.addRow(column);
        }
    }//end fillTable

    public void closePersonUserEndtimeAll() {
        String messageResult = model.closePersonUserEndtimeAll();
        if (messageResult != null) {
            SplashScreen view = new SplashScreen(messageResult);
            view.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Registro Erroneo ");
        }
    }

    public void closePersonUserEndtime(String name, String lastName, String LastName2) {
        String messageResult = model.closePersonUserEndtime(name, lastName, LastName2);
        if (messageResult != null) {
            SplashScreen view = new SplashScreen(messageResult);
            view.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Registro Erroneo ");
        }
    }

    public void insertAdmin(String nameUser, String passUser, String rool) {
        String messageResult = model.insertAdmin(nameUser, passUser, rool);
        if (messageResult != null) {
            SplashScreen view = new SplashScreen(messageResult);
            view.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Registro Erroneo ");
        }
    }

    public ResultSet loadComboUser() {//cargar el combo de actividad
        ResultSet result = model.loadComboUser();
        return result;
    }//inidividual && grupo

    public ResultSet generateIdAdmin(String value) {//genera el id de actividad
        ResultSet rs = model.generateIdAdmin(value);
        return rs;
    }

    public void deleteAdmin(String userName) {
        String messageResult = model.deleteAdmin(userName);
        if (messageResult != null) {
            SplashScreen view = new SplashScreen(messageResult);
            view.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Registro Erroneo ");
        }
    }

    public void updateAdmin(String userName, String newPass) {
        String messageResult = model.updateAdmin(userName, newPass);
        if (messageResult != null) {
            SplashScreen view = new SplashScreen(messageResult);
            view.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Registro Erroneo ");
        }
    }

    public void fillTalbleUser(JTable table) {
        DefaultTableModel modelTable = new DefaultTableModel();

        table.setModel(modelTable);//Obtiene los datos de la tabla

        modelTable.addColumn("Nombre");//Agrega el nombre a las cabezeras
        modelTable.addColumn("Rool");

        Object[] column = new Object[2];
        numRows = model.listUser().size();

        for (int i = 0; i < numRows; i++) {
            column[0] = model.listUser().get(i).getUser();//Agrega dni al array columna[0]
            column[1] = model.listUser().get(i).getRool();//Agrega lastName al array columna[0]
            modelTable.addRow(column);
        }
    }//end fillTable

    public void fillTalbleAdmin(JTable table) {
        DefaultTableModel modelTable = new DefaultTableModel();

        table.setModel(modelTable);//Obtiene los datos de la tabla

        modelTable.addColumn("Nombre");//Agrega el nombre a las cabezeras
        modelTable.addColumn("Rool");

        Object[] column = new Object[2];
        numRows = model.listAdmin().size();

        for (int i = 0; i < numRows; i++) {
            column[0] = model.listAdmin().get(i).getUser();//Agrega dni al array columna[0]
            column[1] = model.listAdmin().get(i).getRool();//Agrega lastName al array columna[0]
            modelTable.addRow(column);
        }
    }//end fillTable
    //////////////////////////////////////////////////////////////////////////////////////////////////

    public void fillTalbleActivity(JTable table) {
        DefaultTableModel modelTable = new DefaultTableModel();

        table.setModel(modelTable);//Obtiene los datos de la tabla

        modelTable.addColumn("Actividad");//Agrega el nombre a las cabezeras

        Object[] column = new Object[1];
        numRows = model.listActivity().size();

        for (int i = 0; i < numRows; i++) {
            column[0] = model.listActivity().get(i).getActivity();//Agrega dni al array columna[0]
            modelTable.addRow(column);
        }
    }//end fillTable

    public void insertActivity(String activity) {
        String messageResult = model.insertActivity(activity);
        if (messageResult != null) {
            SplashScreen view = new SplashScreen(messageResult);
            view.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Registro Erroneo ");
        }
    }

    public void updateActivity(String activity, int IdActivity) {
        String messageResult = model.updateActivity(activity, IdActivity);
        if (messageResult != null) {
            SplashScreen view = new SplashScreen(messageResult);
            view.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Registro Erroneo ");
        }
    }

    public void deleteActivity(String activity) {
        String messageResult = model.deleteActivity(activity);
        if (messageResult != null) {
            SplashScreen view = new SplashScreen(messageResult);
            view.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    public void fillTalbleCareer(JTable table) {
        DefaultTableModel modelTable = new DefaultTableModel();

        table.setModel(modelTable);//Obtiene los datos de la tabla

        modelTable.addColumn("Carrera");//Agrega el nombre a las cabezeras

        Object[] column = new Object[1];
        numRows = model.listCareer().size();

        for (int i = 0; i < numRows; i++) {
            column[0] = model.listCareer().get(i).getCareer();//Agrega dni al array columna[0]
            modelTable.addRow(column);
        }
    }//end fillTable

    public void insertCareer(String career) {
        String messageResult = model.insertCareer(career);
        if (messageResult != null) {
            SplashScreen view = new SplashScreen(messageResult);
            view.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Registro Erroneo ");
        }
    }

    public void updateCareer(String career, int IdCareer) {
        String messageResult = model.updateCareer(career, IdCareer);
        if (messageResult != null) {
            SplashScreen view = new SplashScreen(messageResult);
            view.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Registro Erroneo ");
        }
    }

    public void deleteCareer(String career) {
        String messageResult = model.deleteCareer(career);
        if (messageResult != null) {
            SplashScreen view = new SplashScreen(messageResult);
            view.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    ///////////////////////////////////////////////////////////////////////
    public ResultSet loadComboActivity() {//cargar el combo de actividad
        ResultSet result = model.loadComboActivity();
        return result;
    }//inidividual && grupo

    public ResultSet loadComboCareer() {//cargar el combo de Carrera
        ResultSet result = model.loadComboCareer();
        return result;
    }

}
