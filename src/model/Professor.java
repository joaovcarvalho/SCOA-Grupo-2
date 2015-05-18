/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author JoãoVitor
 */
public class Professor extends Type {
    
    private int id;
    private String register;
    private String room;
    private String telephone;
    private String lattes;
    private String name;

    public Professor(int id, String register, String room, String telephone, String lattes, String name) {
        this.id = id;
        this.register = register;
        this.room = room;
        this.telephone = telephone;
        this.lattes = lattes;
        this.name = name;
    }

    public Professor() {
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLattes() {
        return lattes;
    }

    public void setLattes(String lattes) {
        this.lattes = lattes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
