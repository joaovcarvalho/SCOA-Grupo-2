/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.DataAccessObject;

/**
 *
 * @author Amanda
 */
public class Class {
    private int id;
    private Professor professor;
    private Room room;
    private Subject subject;
    private int semester;
      
    public Class(int id, Professor professor, Room room, Subject subject, int semester) {
        this.id = id;
        this.professor = professor;
        this.room = room;
        this.subject = subject;
        this.semester = semester;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "Class{" + "id=" + id + ", professor=" + professor + ", room=" + room + ", subject=" + subject + ", semester=" + semester + '}';
    }
      
      
}
