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
public class Secretary extends Type {
    
    private String name;

    public Secretary(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Secretary() {
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Secretary{" + "name=" + name + ", id=" + id + '}';
    }
    
    
    
    
    
}
