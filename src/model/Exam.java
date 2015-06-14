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
public class Exam {
    private int id;
    private String description;
    private String grade;
    private Date delivery_date;
    private Registration registration;

    public Exam(int id, String description, String grade, Date delivery_date, Registration registration) {
        this.id = id;
        this.description = description;
        this.grade = grade;
        this.delivery_date = delivery_date;
        this.registration = registration;
    }

    public Exam(String description, String grade, Date delivery_date, Registration registration) {
        this.description = description;
        this.grade = grade;
        this.delivery_date = delivery_date;
        this.registration = registration;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Date getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(Date delivery_date) {
        this.delivery_date = delivery_date;
    }
    
    

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        return "Exam{" + "id=" + id + ", description=" + description + ", grade=" + grade + ", delivery_date=" + delivery_date + ", registration=" + registration + '}';
    }
    
    
}
