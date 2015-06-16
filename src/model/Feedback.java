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
public class Feedback {
    private int id;
    private String description;
    private String type;

    public Feedback(int id, String description, String type) {
        this.id = id;
        this.description = description;
        this.type = type;
    }
    
    public Feedback( String description, String type) {
        this.description = description;
        this.type = type;
    }
   
    @Override
    public String toString() {
        return "Feedback{" + "id=" + getId() + ", description=" + getDescription() + ", type=" + getType() + '}';
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
    
}
