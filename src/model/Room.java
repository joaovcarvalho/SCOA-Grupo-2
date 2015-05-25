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
public class Room {
     private int id;
     private String number;
     private String capacity;

    public Room(int id, String number, String capacity) {
        this.id = id;
        this.number = number;
        this.capacity = capacity;
    }

    public String getCapacity() {
        return capacity;
    }

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    
}
