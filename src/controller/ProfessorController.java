/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ExamDAO;
import dao.RegistrationDAO;
import exceptions.InvalidFieldException;
import exceptions.MissingFieldException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public static void editExam(Exam exam){
        
    }
    
}
