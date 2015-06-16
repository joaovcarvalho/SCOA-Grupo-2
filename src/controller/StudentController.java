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
import model.Feedback;
import dao.FeedbackDAO;

/**
 *
 * @author Douglas Quintanilha
 */
public class StudentController {
    
    public static void insertFeedback(String type, String description)throws SQLException, ParseException, MissingFieldException, InvalidFieldException{
        if( !Validator.validateRequired(type) || !Validator.validateRequired(description)){
            throw new MissingFieldException();
        }
        String s;
        if(type.equals("Reclamação")){
            s = "complaint";
        }
        else if(type.equals("Sugestão")){
            s = "suggestion";
        }else{
            s = "";
        }
        Feedback f = new Feedback(description,s);
        FeedbackDAO.insertFeedback(f);
    }
    
}
