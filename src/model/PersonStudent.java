/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Juan1
 */
public class PersonStudent {

    int id;
    int matricula;//almacenara la matricula*
    String name, lastname, lastname2;//almacenara el nombre de el alumno*
    String activity;//la actividad en la biblioteca*<-reparar
    String grade/*<-tipo?*/, career;//indica la carrera ,si pertenece a TSU o INGENIERIA<-revisar
    String sexo;//indica de que genenro es el usuario*
    String date;//almacena la fecha *
    String startTime, endTime;//almacena la hora de inicio y de final*

    /////////////////////////////admin
    String user, rool;

    public PersonStudent() {//constructor para inicializar las variables
        this.id = 0;
        this.matricula = 0;
        this.name = "";
        this.lastname = "";
        this.lastname2 = "";
        this.activity = "";
        this.grade = "";
        this.career = "";
        this.sexo = "";
        this.date = null;
        this.startTime = null;
        this.endTime = null;
        this.user = "";
        this.rool = "";
    }

    public String getRool() {
        return rool;
    }

    public void setRool(String rool) {
        this.rool = rool;
    }

    public String getLastname2() {
        return lastname2;
    }

    public void setLastname2(String lastname2) {
        this.lastname2 = lastname2;
    }

    //get and set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
