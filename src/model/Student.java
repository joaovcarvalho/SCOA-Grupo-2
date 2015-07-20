/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Douglas
 */
public class Student extends Type {
    
    private String name;
    private String telephone;
    private String register;
    private String address;
    private String email;
    private String birth_date;
    private int semester;
    
    private User user;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Student(int id, String name, String telephone, String register, String address, String email, String birth_date, int semester) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.register = register;
        this.address = address;
        this.email = email;
        this.birth_date = birth_date;
        this.semester = semester;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    
    

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }


    
    

    public Student() {
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
        return "Student{" + "name=" + getName() + ", telephone=" + getTelephone() + ", register=" + getRegister() + ", address=" + getAddress() + ", email=" + getEmail() + ", birth_date=" + getBirth_date() + ", semester=" + getSemester() + '}';
    }
    
    
    
    
    
}