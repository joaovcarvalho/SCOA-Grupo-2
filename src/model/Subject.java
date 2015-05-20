/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Amanda
 */
public class Subject {
    private int id;
    private String name;
    private String description;
    private String code;
    private String credits;
    private int course_id;

    public Subject(int id, String name, String description, String code, String credits, int course_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.code = code;
        this.credits = credits;
        this.course_id = course_id;
    }

    public String getCode() {
        return code;
    }

    public int getCourse_id() {
        return course_id;
    }

    public String getCredits() {
        return credits;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
