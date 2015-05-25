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
      private int idProfessor;
      private int id_room;
      private int id_subject;

    public Class(int id, int id_professor, int id_room, int id_subject) {
        this.id = id;
        this.idProfessor = id_professor;
        this.id_room = id_room;
        this.id_subject = id_subject;
    }

    public int getId() {
        return id;
    }

    public int getId_room() {
        return id_room;
    }

    public int getId_professor() {
        return idProfessor;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_professor(int id_professor) {
        this.idProfessor = id_professor;
    }

    public void setId_room(int id_room) {
        this.id_room = id_room;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }
      
      
}
