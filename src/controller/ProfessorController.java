/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AgendaDAO;
import dao.ExamDAO;
import dao.RegistrationDAO;
import exceptions.InvalidFieldException;
import exceptions.MissingFieldException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Agenda;
import model.Exam;
import model.Registration;
import model.Student;
import model.Class;
import tools.Validator;

/**
 *
 * @author JoãoVitor
 */
public class ProfessorController {
    
    public static void insertExam(Student selectedStudent, Class selectedClass, String date, String description, String grade) throws SQLException, ParseException, MissingFieldException, InvalidFieldException{
        if(selectedClass == null || 
                selectedStudent == null || 
                !Validator.validateRequired(date) || 
                !Validator.validateRequired(description) ||
                !Validator.validateRequired(grade))
            throw new MissingFieldException();
        
        if(!Validator.validateDate(date))
            throw new ParseException("", 0);
        
        if(!Validator.validateNumber(grade))
            throw new InvalidFieldException("Nota deve ser um número.");
        
        Registration registration;
        registration = RegistrationDAO.getRegistrationByStudentIdAndClassId(
                selectedStudent.getId(), selectedClass.getId());

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date myDate = formatter.parse(date);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

        Exam exam = new Exam(description, grade, sqlDate , registration);
        ExamDAO.insertExam(exam);
    }
    
    public static void deleteExam(Exam exam) {
        ExamDAO.deleteExam(exam);
    }
    
    public static void editExam(Exam exam, String description, String grade, String date) throws ParseException, MissingFieldException, InvalidFieldException{
        if(!Validator.validateRequired(date) || 
            !Validator.validateRequired(description) ||
            !Validator.validateRequired(grade))
            throw new MissingFieldException();
        
        if(!Validator.validateDate(date))
            throw new ParseException("", 0);

        if(!Validator.validateNumber(grade))
            throw new InvalidFieldException("Nota deve ser um número.");

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date myDate = formatter.parse(date);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        exam.setDelivery_date(sqlDate);
        
        exam.setDescription(description);
        exam.setGrade(grade);
        exam.setDelivery_date(sqlDate);
        
        ExamDAO.editExam(exam);
    }
    
    public static void insertAgenda(Class selectedClass, String date, String description) throws SQLException, ParseException, MissingFieldException, InvalidFieldException{
        if(selectedClass == null || 
                !Validator.validateRequired(date) || 
                !Validator.validateRequired(description))
            throw new MissingFieldException();
        
        if(!Validator.validateDate(date))
            throw new ParseException("", 0);
        
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date myDate = formatter.parse(date);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

        Agenda agenda = new Agenda(description, sqlDate, selectedClass);
        AgendaDAO.insertAgenda(agenda);
    }
    
    public static void editAgenda(Agenda agenda, String description, String date, Class selectedClass) throws ParseException, MissingFieldException{
        if(selectedClass == null || 
                !Validator.validateRequired(date) || 
                !Validator.validateRequired(description))
            throw new MissingFieldException();
        
        if(!Validator.validateDate(date))
            throw new ParseException("", 0);
        
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date myDate = formatter.parse(date);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        
        agenda.setDate(sqlDate);
        agenda.setDescription(description);
        
        AgendaDAO.editAgenda(agenda);
    }
    
    
    public static void deleteAgenda(Agenda agenda){
        AgendaDAO.deleteAgenda(agenda);
    }
}
