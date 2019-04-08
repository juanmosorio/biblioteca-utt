/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.SplashScreen;

/**
 *
 * @author Juan1
 */
public class PersonStudentDao {

    Conexion conexion;//se crea conexion de tipo Conexion

    public PersonStudentDao() {//se inicializa en el constructor
        conexion = new Conexion();
    }

    public ResultSet searchPersonUserMat(String matValue) {//busca los datos de la persona pormedio de la matricula
        ResultSet result = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("SELECT LTRIM(RTRIM(p.nombre)) as nombre,"
                    + " LTRIM(RTRIM(apellidoPaterno)) as apellidoPaterno, LTRIM(RTRIM(apellidoMaterno))"
                    + " as apellidoMaterno, genero, LTRIM(RTRIM(grado)) as grado, p.id_carrera,  LTRIM(RTRIM(c.nombre))  AS nombre_carrera "
                    + "FROM Student AS p "
                    + "JOIN Carrera AS c "
                    + "ON p.id_carrera = c.id_carrera"
                    + " WHERE matricula = '" + matValue + "';");
            result = ps.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return result;
    }

    public ResultSet searchPersonUserNam(String namValue, String lanValue, String lan2Value) {//busca los datos de la persona pormedio de el nombre
        ResultSet result = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("SELECT LTRIM(RTRIM(matricula)) as matricula, genero, LTRIM(RTRIM(grado)) as grado,"
                    + " p.id_carrera, LTRIM(RTRIM(c.nombre))  AS nombre_carrera "
                    + "FROM Student AS p "
                    + "JOIN Carrera AS c "
                    + "ON p.id_carrera = c.id_carrera "
                    + "WHERE p.nombre = '" + namValue + "'  AND apellidoPaterno"
                    + " = '" + lanValue + "' AND apellidoMaterno = '" + lan2Value + "' ");
            result = ps.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return result;
    }

    public ResultSet generateIdActivity(String value) {//genera el id de actividad
        ResultSet result = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("SELECT id_actividad FROM Actividad "
                    + "WHERE nombre = '" + value + "'");
            result = ps.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return result;
    }

    public ResultSet generateIdCareer(String value) {//genera el id de actividad
        ResultSet result = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("SELECT id_carrera FROM Carrera "
                    + "WHERE nombre = '" + value + "'");
            result = ps.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return result;
    }

    public String insertPersonUser(String matricula, String name, String lastname, String lastname2, int sexo,
            String grade, String startTime, String endTime, String date, int career, int activity, String tipo) {
        String queryResult = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("insert into Usuario (matricula,"
                    + "nombre,apellidoPaterno, apellidoMaterno,genero,grado,hora_entrada,hora_salida,fecha,id_carrera,id_actividad,tipo)"
                    + " values (?,?,?,?,?,?,?,?,?,?,?,?);");

            ps.setString(1, matricula);
            ps.setString(2, name);
            ps.setString(3, lastname);
            ps.setString(4, lastname2);
            ps.setInt(5, sexo);
            ps.setString(6, grade);
            ps.setString(7, startTime);
            ps.setString(8, endTime);
            ps.setString(9, date);
            ps.setInt(10, career);
            ps.setInt(11, activity);
            ps.setString(12, tipo);

            int numAffectedRows = ps.executeUpdate();
            if (numAffectedRows > 0) {
                queryResult = name + " ¡Bienvenido a la Biblioteca!";
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
        return queryResult;
    }

    public String insertPruebaStudent(String matricula, String name, String lastname, String lastname2,
            int sexo, String grade, int career) {
        String queryResult = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("insert into Student (matricula, nombre,"
                    + "apellidoPaterno, apellidoMaterno,genero, grado, id_carrera) "
                    + " values (?,?,?,?,?,?,?);");

            ps.setString(1, matricula);
            ps.setString(2, name);
            ps.setString(3, lastname);
            ps.setString(4, lastname2);
            ps.setInt(5, sexo);
            ps.setString(6, grade);
            ps.setInt(7, career);

            int numAffectedRows = ps.executeUpdate();

            if (numAffectedRows > 0) {
                queryResult = name + " ¡Bienvenido a la Biblioteca!";
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
        return queryResult;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String insertPersonStudentG(String name, String lastname, String lastname2, int career,
            String grade, int activity, int noH, int noM, int total, String startTime,
            String endTime, String date, String tipo) {
        String queryResult = null;
        int numAffectedRows = 0;
        try {
            Connection DbAccess = conexion.getConexion();
            if (grade.equals("TSU")) {
                if (noH > 0) {
                    for (int i = 1; i <= noH; i++) {
                        PreparedStatement ps = DbAccess.prepareStatement("INSERT INTO Usuario(matricula,nombre,"
                                + "apellidoPaterno,apellidoMaterno,genero,grado,hora_entrada,hora_salida,fecha,"
                                + "id_carrera,id_actividad,tipo) values (?,?,?,?,?,?,?,?,?,?,?,?)");

                        ps.setString(1, "1");
                        ps.setString(2, name);
                        ps.setString(3, lastname);
                        ps.setString(4, lastname2);
                        ps.setInt(5, 1);
                        ps.setString(6, grade);
                        ps.setString(7, startTime);
                        ps.setString(8, endTime);
                        ps.setString(9, date);
                        ps.setInt(10, career);
                        ps.setInt(11, activity);
                        ps.setString(12, tipo);

                        numAffectedRows = ps.executeUpdate();
                    }

                }
                if (noM > 0) {
                    for (int i = 1; i <= noM; i++) {
                        PreparedStatement ps = DbAccess.prepareStatement("INSERT INTO Usuario(matricula,nombre,"
                                + "apellidoPaterno,apellidoMaterno,genero,grado,hora_entrada,hora_salida,fecha,"
                                + "id_carrera,id_actividad,tipo) values (?,?,?,?,?,?,?,?,?,?,?,?)");

                        ps.setString(1, "1");
                        ps.setString(2, name);
                        ps.setString(3, lastname);
                        ps.setString(4, lastname2);
                        ps.setInt(5, 0);
                        ps.setString(6, grade);
                        ps.setString(7, startTime);
                        ps.setString(8, endTime);
                        ps.setString(9, date);
                        ps.setInt(10, career);
                        ps.setInt(11, activity);
                        ps.setString(12, tipo);

                        numAffectedRows = ps.executeUpdate();
                    }

                }
            }
            if (grade.equals("INGENIERIA")) {
                if (noH > 0) {
                    for (int i = 1; i <= noH; i++) {
                        PreparedStatement ps = DbAccess.prepareStatement("INSERT INTO Usuario(matricula,nombre,"
                                + "apellidoPaterno,apellidoMaterno,genero,grado,hora_entrada,hora_salida,fecha,"
                                + "id_carrera,id_actividad,tipo) values (?,?,?,?,?,?,?,?,?,?,?,?)");

                        ps.setString(1, "1");
                        ps.setString(2, name);
                        ps.setString(3, lastname);
                        ps.setString(4, lastname2);
                        ps.setInt(5, 1);
                        ps.setString(6, grade);
                        ps.setString(7, startTime);
                        ps.setString(8, endTime);
                        ps.setString(9, date);
                        ps.setInt(10, career);
                        ps.setInt(11, activity);
                        ps.setString(12, tipo);

                        numAffectedRows = ps.executeUpdate();
                    }

                }
                if (noM > 0) {
                    for (int i = 1; i <= noM; i++) {
                        PreparedStatement ps = DbAccess.prepareStatement("INSERT INTO Usuario(matricula,nombre,"
                                + "apellidoPaterno,apellidoMaterno,genero,grado,hora_entrada,hora_salida,fecha,"
                                + "id_carrera,id_actividad,tipo) values (?,?,?,?,?,?,?,?,?,?,?,?)");

                        ps.setString(1, "1");
                        ps.setString(2, name);
                        ps.setString(3, lastname);
                        ps.setString(4, lastname2);
                        ps.setInt(5, 0);
                        ps.setString(6, grade);
                        ps.setString(7, startTime);
                        ps.setString(8, endTime);
                        ps.setString(9, date);
                        ps.setInt(10, career);
                        ps.setInt(11, activity);
                        ps.setString(12, tipo);

                        numAffectedRows = ps.executeUpdate();
                    }

                }
            }

            if (numAffectedRows > 0) {
                queryResult = "¡Bienvenidos a la Biblioteca UTT!";
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
        return queryResult;
    }
    ///////////////////////////////////////////////////////////////////////////

    //Teacher
    //borrado
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public ResultSet searchPersonUserMatOut(String matValue, String date, String tipo) {//<-busqueda de por hora
        ResultSet result = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("SELECT LTRIM(RTRIM(u.nombre)) AS nombre, "
                    + "LTRIM(RTRIM(apellidoPaterno)) AS apellidoPaterno, LTRIM(RTRIM(apellidoMaterno))"
                    + " AS apellidoMaterno, genero, LTRIM(RTRIM(grado)) AS grado, u.id_carrera, LTRIM(RTRIM(c.nombre))"
                    + "AS nombre_carrera, hora_entrada, LTRIM(RTRIM(a.nombre)) as nombre_actividad "
                    + "FROM Usuario AS u JOIN Carrera AS c ON u.id_carrera = c.id_carrera "
                    + "JOIN Actividad AS a ON u.id_actividad = a.id_actividad "
                    + "WHERE matricula = '" + matValue + "' AND fecha = '" + date + "' "
                    + "AND hora_salida = '00:00:00.0000000' AND tipo = '" + tipo + "'");
            result = ps.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return result;
    }

    ////////////////////<-inserta la hora de salida
    public String insertPersonUserEndtime(String endTime, String matValue, String date) {
        String result = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("UPDATE Usuario SET hora_salida"
                    + " = '" + endTime + "' WHERE matricula = '" + matValue + "' and fecha ='" + date + "' "
                    + "AND hora_salida = '00:00:00.0000000'");
            int numAffectedRows = ps.executeUpdate();
            if (numAffectedRows > 0) {
                result = "¡Gracias por registrarte! \nHora de salida: " + endTime;
            }
        } catch (SQLException ex) {
            System.out.println("ERROR");
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return result;
    }

    public ResultSet searchPersonUserNamOut(String namValue, String lanValue, String lan2Value,
            String date, String tipo) {//busca los datos de la persona pormedio de el nombre
        ResultSet result = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("SELECT LTRIM(RTRIM(matricula)) as matricula,"
                    + "genero, LTRIM(RTRIM(grado)) as grado,p.id_carrera, LTRIM(RTRIM(c.nombre))  AS nombre_carrera,"
                    + " LTRIM(RTRIM(a.nombre)) as nombre_actividad "
                    + "FROM Usuario AS p JOIN Carrera AS c ON p.id_carrera = c.id_carrera "
                    + "JOIN Actividad AS a ON p.id_actividad = a.id_actividad "
                    + "WHERE p.nombre = '" + namValue + "' "
                    + "AND apellidoPaterno = '" + lanValue + "' "
                    + "AND apellidoMaterno = '" + lan2Value + "' AND fecha ='" + date + "' "
                    + "AND hora_salida = '00:00:00.0000000' AND tipo = '" + tipo + "'");
            result = ps.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return result;
    }

    //////////////////////////////grupo////////////////////////////////////////////////////7
    public ResultSet countM(String namValue, String lanValue, String lan2Value, String date) {//busca los datos de la persona pormedio de el nombre
        ResultSet result = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("SELECT COUNT(genero) AS M FROM Usuario as u "
                    + "WHERE genero = 1 AND u.matricula = '1' AND u.nombre = '" + namValue + "' AND u.apellidoPaterno = '" + lanValue + "' "
                    + "AND u.apellidoMaterno = '" + lan2Value + "' AND u.fecha = '" + date + "' "
                    + "AND hora_salida = '00:00:00.0000000'");
            result = ps.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return result;
    }

    public ResultSet countF(String namValue, String lanValue, String lan2Value, String date) {//busca los datos de la persona pormedio de el nombre
        ResultSet result = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("SELECT COUNT(genero) AS F FROM Usuario as u "
                    + "WHERE genero = 0 AND u.matricula = '1' AND u.nombre = '" + namValue + "' AND u.apellidoPaterno = '" + lanValue + "' "
                    + "AND u.apellidoMaterno = '" + lan2Value + "' AND u.fecha = '" + date + "' "
                    + "AND hora_salida = '00:00:00.0000000'");
            result = ps.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return result;
    }

    public ResultSet searchGroupStudentOut(String namValue, String lanValue, String lan2Value, String date) {//busca los datos de la persona pormedio de el nombre
        ResultSet result = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("SELECT LTRIM(RTRIM(u.nombre)) AS nombre, "
                    + "LTRIM(RTRIM(apellidoPaterno)) AS apellidoPaterno, LTRIM(RTRIM(apellidoMaterno))"
                    + " AS apellidoMaterno, genero, LTRIM(RTRIM(grado)) AS grado, u.id_carrera, LTRIM(RTRIM(c.nombre))"
                    + "AS nombre_carrera, hora_entrada, LTRIM(RTRIM(a.nombre)) as nombre_actividad "
                    + "FROM Usuario AS u JOIN Carrera AS c ON u.id_carrera = c.id_carrera "
                    + "JOIN Actividad AS a ON u.id_actividad = a.id_actividad "
                    + "WHERE u.matricula = '1' AND u.nombre = '" + namValue + "'  "
                    + "AND u.apellidoPaterno = '" + lanValue + "'  "
                    + "AND u.apellidoMaterno = '" + lan2Value + "'  "
                    + "AND u.fecha = '" + date + "' "
                    + "AND hora_salida = '00:00:00.0000000'");
            result = ps.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return result;
    }

    public ResultSet searchGroupStudent(String namValue, String lanValue, String lan2Value) {//busca los datos de la persona pormedio de el nombre
        ResultSet result = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("SELECT c.id_carrera, LTRIM(RTRIM(c.nombre)) "
                    + "AS nombre_carrera, LTRIM(RTRIM(grado)) AS grado "
                    + "FROM Student AS p JOIN Carrera AS c ON p.id_carrera = c.id_carrera "
                    + "WHERE p.nombre = '" + namValue + "' "
                    + "AND p.apellidoPaterno = '" + lanValue + "' "
                    + "AND p.apellidoMaterno = '" + lan2Value + "' ");
            result = ps.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return result;
    }

    public String groupStudentEndtime(String name, String lastname, String lastname2, String endTime, String date) {
        String result = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("UPDATE Usuario SET hora_salida = '" + endTime + "' "
                    + "WHERE matricula = '1' AND nombre = '" + name + "' "
                    + "AND apellidoPaterno = '" + lastname + "' "
                    + "AND apellidoMaterno = '" + lastname2 + "' "
                    + "AND fecha ='" + date + "'"
                    + " AND hora_salida = '00:00:00.0000000'");
            int numAffectedRows = ps.executeUpdate();
            if (numAffectedRows > 0) {
                result = "¡Gracias por registrarte! \nHora de salida: " + endTime;
            }
        } catch (SQLException ex) {
            System.out.println("ERROR");
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return result;
    }

    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////Teacher////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public ResultSet searchPersonTeacherNum(String numValue) {//busca los datos de la persona pormedio de la matricula
        ResultSet result = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("SELECT LTRIM(RTRIM(p.nombre)) as nombre, "
                    + "LTRIM(RTRIM(apellidoPaterno)) as apellidoPaterno, LTRIM(RTRIM(apellidoMaterno)) "
                    + "as apellidoMaterno, genero, LTRIM(RTRIM(grado)) as grado, p.id_carrera,  "
                    + "LTRIM(RTRIM(c.nombre))  AS nombre_carrera "
                    + "FROM Teacher AS p "
                    + "JOIN Carrera AS c ON p.id_carrera = c.id_carrera "
                    + "WHERE numero_empleado = '" + numValue + "';");
            result = ps.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return result;
    }

    public ResultSet searchPersonTeacherNam(String namValue, String lanValue, String lan2Value) {//busca los datos de la persona pormedio de el nombre
        ResultSet result = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("SELECT LTRIM(RTRIM(numero_empleado)) as "
                    + "numero_empleado, genero, LTRIM(RTRIM(grado)) as grado, p.id_carrera, LTRIM(RTRIM(c.nombre)) "
                    + "AS nombre_carrera FROM Teacher AS p JOIN Carrera AS c ON p.id_carrera = c.id_carrera "
                    + "WHERE p.nombre = '" + namValue + "' "
                    + "AND apellidoPaterno = '" + lanValue + "' "
                    + "AND apellidoMaterno = '" + lan2Value + "' ");
            result = ps.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return result;
    }

    public String insertTeacherTeacher(String no, String name, String lastname, String lastname2,
            int sexo, String grade, int career) {
        String queryResult = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("INSERT INTO Teacher (numero_empleado, "
                    + "nombre, apellidoPaterno, apellidoMaterno,genero, grado, id_carrera) "
                    + "VALUES (?,?,?,?,?,?,?);");

            ps.setString(1, no);
            ps.setString(2, name);
            ps.setString(3, lastname);
            ps.setString(4, lastname2);
            ps.setInt(5, sexo);
            ps.setString(6, grade);
            ps.setInt(7, career);

            int numAffectedRows = ps.executeUpdate();

            if (numAffectedRows > 0) {
                queryResult = name + " ¡Bienvenido a la Biblioteca!";
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
        return queryResult;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////LOGIN///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public ResultSet login(String username, String password) {
        ResultSet result = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("SELECT * FROM UserLogin "
                    + "WHERE nombre = '" + username + "' AND pass = '" + password + "'");
            result = ps.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return result;
    }

    public ResultSet loginAdmin(String username, String password) {
        ResultSet result = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("SELECT * FROM UserLogin "
                    + "WHERE nombre = '" + username + "' AND pass = '" + password + "' AND (rool = 'Administrador' OR rool = 'ROOT')");
            result = ps.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return result;
    }

    public ArrayList<PersonStudent> listPerson() {
        ArrayList listPerson = new ArrayList();
        PersonStudent person;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("SELECT"
                    + " LTRIM(RTRIM(U.nombre)) AS nombre,"
                    + "LTRIM(RTRIM(apellidoPaterno)) AS apellidoPaterno,"
                    + "LTRIM(RTRIM(apellidoMaterno)) AS apellidoMaterno,"
                    + "genero = "
                    + "CASE "
                    + "WHEN genero = 1 THEN 'Hombre' "
                    + "WHEN genero = 0 THEN 'Mujer' "
                    + "END,grado,hora_entrada,hora_salida,A.nombre AS actividad "
                    + "FROM Usuario as U JOIN Actividad as A ON U.id_actividad = A.id_actividad "
                    + "WHERE hora_salida = '00:00:00.0000000'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                person = new PersonStudent();//declaro objeto persona
                person.setName(rs.getString(1));//se estables dni
                person.setLastname(rs.getString(2));
                person.setLastname2(rs.getString(3));
                person.setSexo(rs.getString(4));
                person.setGrade(rs.getString(5));
                person.setStartTime(rs.getString(6));
                person.setEndTime(rs.getString(7));
                person.setActivity(rs.getString(8));
                listPerson.add(person);//se establese persona al array de listPerson
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        return listPerson;
    }

    public String closePersonUserEndtimeAll() {
        String result = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("UPDATE Usuario SET hora_salida = NOW() "
                    + "WHERE hora_salida = '00:00:00.0000000'");
            int numAffectedRows = ps.executeUpdate();
            if (numAffectedRows > 0) {
                result = "¡Registros cerrados!";
            }
        } catch (SQLException ex) {
            System.out.println("ERROR");
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return result;
    }
        public String closePersonUserEndtime(String name, String lastName, String LastName2) {
        String result = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("UPDATE Usuario SET hora_salida = NOW() "
                    + "WHERE hora_salida = '00:00:00.0000000' AND nombre = '"+name+"' AND apellidoPaterno = '"+lastName+"'"
                            + "AND apellidoMaterno = '"+LastName2+"'");
            int numAffectedRows = ps.executeUpdate();
            if (numAffectedRows > 0) {
                result = "¡Registros cerrados!";
            }
        } catch (SQLException ex) {
            System.out.println("ERROR");
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return result;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////Admin///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String insertAdmin(String nameUser, String PassUser, String rool) {
        String queryResult = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("INSERT INTO UserLogin (nombre,pass,rool) "
                    + "VALUES (?,?,?);");

            ps.setString(1, nameUser);
            ps.setString(2, PassUser);
            ps.setString(3, rool);

            int numAffectedRows = ps.executeUpdate();

            if (numAffectedRows > 0) {
                queryResult = " ¡EL Usuario " + nameUser + " a sido registrado";
            }

        } catch (SQLException ex) {
            String message = ex.getMessage();
            if (message.equals(ex.getMessage())) {
                JOptionPane.showMessageDialog(null, "¡Ya hay un usuario con ese nombre vuelve a intertarlo!");
            } else {
                JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
            }
        }
        return queryResult;
    }

    public ResultSet loadComboUser() {//cargar las opciones de actividad
        ResultSet result = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("SELECT LTRIM(RTRIM(nombre)) as nombre FROM UserLogin WHERE rool != 'Administrador'");
            result = ps.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return result;
    }//individual && grupo

    public ResultSet generateIdAdmin(String value) {//genera el id de actividad
        ResultSet result = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("SELECT id FROM UserLogin "
                    + "WHERE nombre = '" + value + "'");
            result = ps.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return result;
    }

    public String deleteAdmin(String userName) {
        String queryResult = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("DELETE FROM UserLogin WHERE nombre = '" + userName + "'");

            int numAffectedRows = ps.executeUpdate();

            if (numAffectedRows > 0) {
                queryResult = "Usuario Eliminado!";
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        return queryResult;
    }//end detelePerson

    public String updateAdmin(String nameUser, String newPass) {
        String result = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("UPDATE UserLogin" + " "
                    + "SET pass = '" + newPass + "' WHERE nombre ='" + nameUser + "' ");
            int numAffectedRows = ps.executeUpdate();
            if (numAffectedRows > 0) {
                result = "¡EL Usuario " + nameUser + " a sido Actualizado!";
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return result;
    }

    public ArrayList<PersonStudent> listUser() {
        ArrayList listPerson = new ArrayList();
        PersonStudent person;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("SELECT LTRIM(RTRIM(nombre)) AS Nombre, rool AS Rool "
                    + "FROM UserLogin WHERE rool = 'Usuario'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                person = new PersonStudent();//declaro objeto persona
                person.setUser(rs.getString(1));//se estables dni
                person.setRool(rs.getString(2));
                listPerson.add(person);//se establese persona al array de listPerson
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        return listPerson;
    }

    public ArrayList<PersonStudent> listAdmin() {
        ArrayList listPerson = new ArrayList();
        PersonStudent person;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("SELECT LTRIM(RTRIM(nombre)) AS Nombre, rool AS Rool "
                    + "FROM UserLogin WHERE rool != 'ROOT' ORDER BY rool");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                person = new PersonStudent();//declaro objeto persona
                person.setUser(rs.getString(1));//se estables dni
                person.setRool(rs.getString(2));
                listPerson.add(person);//se establese persona al array de listPerson
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        return listPerson;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public ArrayList<PersonStudent> listActivity() {
        ArrayList listPerson = new ArrayList();
        PersonStudent person;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("SELECT LTRIM(RTRIM(nombre)) AS Actividad "
                    + "FROM Actividad");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                person = new PersonStudent();//declaro objeto persona
                person.setActivity(rs.getString(1));//se estables dni
                listPerson.add(person);//se establese persona al array de listPerson
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        return listPerson;
    }

    public String insertActivity(String activity) {
        String queryResult = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("INSERT INTO Actividad (nombre) "
                    + "VALUES(?)");
            ps.setString(1, activity);
            int numAffectedRows = ps.executeUpdate();
            if (numAffectedRows > 0) {
                queryResult = activity + " ¡Actividad agregada correctamente!";
            }
        } catch (SQLException ex) {
            String message = ex.getMessage();
            if (message.equals(ex.getMessage())) {
                JOptionPane.showMessageDialog(null, "¡Ya hay una actividad con ese nombre vuelve a intertarlo!");
            } else {
                JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
            }
        }
        return queryResult;
    }

    public String updateActivity(String activity, int IdActivity) {
        String result = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("UPDATE Actividad set nombre = '" + activity + "' "
                    + "WHERE id_actividad = '" + IdActivity + "'");
            int numAffectedRows = ps.executeUpdate();
            if (numAffectedRows > 0) {
                result = "Actividad Actualizada!";
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return result;
    }

    public String deleteActivity(String activity) {
        String queryResult = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("DELETE FROM Actividad WHERE nombre = '" + activity + "'");
            int numAffectedRows = ps.executeUpdate();
            if (numAffectedRows > 0) {
                queryResult = "Actividad Eliminada!";
            }
        } catch (Exception ex) {
            String message = ex.getMessage();
            if (message.equals(ex.getMessage())) {
                JOptionPane.showMessageDialog(null, "¡No se puede elminar\nActividad en uso!");
            } else {
                JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
            }
        }
        return queryResult;
    }//end detelePerson
    //////////////////////////////////////////////////////////////////////////////////////

    public ArrayList<PersonStudent> listCareer() {
        ArrayList listPerson = new ArrayList();
        PersonStudent person;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("SELECT LTRIM(RTRIM(nombre)) AS Carrera "
                    + "FROM Carrera");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                person = new PersonStudent();//declaro objeto persona
                person.setCareer(rs.getString(1));//se estables dni
                listPerson.add(person);//se establese persona al array de listPerson
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        return listPerson;
    }

    public String insertCareer(String career) {
        String queryResult = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("INSERT INTO Carrera (nombre) "
                    + "VALUES(?)");
            ps.setString(1, career);
            int numAffectedRows = ps.executeUpdate();
            if (numAffectedRows > 0) {
                queryResult = career + " ¡Carrera agregada correctamente!";
            }
        } catch (SQLException ex) {
            String message = ex.getMessage();
            if (message.equals(ex.getMessage())) {
                JOptionPane.showMessageDialog(null, "¡Ya hay una carrera con ese nombre vuelve a intertarlo!");
            } else {
                JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
            }
        }
        return queryResult;
    }

    public String updateCareer(String career, int idCareer) {
        String result = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("UPDATE Carrera set nombre = '" + career + "' "
                    + "WHERE id_carrera = '" + idCareer + "'");
            int numAffectedRows = ps.executeUpdate();
            if (numAffectedRows > 0) {
                result = "Carrera Actualizada!";
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return result;
    }

    public String deleteCareer(String career) {
        String queryResult = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("DELETE FROM Carrera WHERE nombre = '" + career + "'");
            int numAffectedRows = ps.executeUpdate();
            if (numAffectedRows > 0) {
                queryResult = "Carrera Eliminada!";
            }
        } catch (Exception ex) {
            String message = ex.getMessage();
            if (message.equals(ex.getMessage())) {
                JOptionPane.showMessageDialog(null, "¡No se puede elminar\nCarrera en uso!");
            } else {
                JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
            }
        }
        return queryResult;
    }//end detelePerson

    ////////////////////////////////////////////////////////////////////////////////////////////
    public ResultSet loadComboActivity() {//cargar las opciones de actividad
        ResultSet result = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("SELECT LTRIM(RTRIM(nombre)) as nombre FROM Actividad");
            result = ps.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return result;
    }//individual && grupo

    public ResultSet loadComboCareer() {//cargar las opciones de Carrera
        ResultSet result = null;
        try {
            Connection DbAccess = conexion.getConexion();
            PreparedStatement ps = DbAccess.prepareStatement("SELECT LTRIM(RTRIM(nombre)) AS nombre FROM Carrera");
            result = ps.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return result;
    }
}
