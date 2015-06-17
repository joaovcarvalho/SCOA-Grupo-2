/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author JoãoVitor
 */
public class Agenda {
    
    private int id;
    private String description;
    private Date date;
    private Class myClass;

    public Agenda(int id, String description, Date date, Class myClass) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.myClass = myClass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Class getMyClass() {
        return myClass;
    }

    public void setMyClass(Class myClass) {
        this.myClass = myClass;
    }
    
    
    
}
