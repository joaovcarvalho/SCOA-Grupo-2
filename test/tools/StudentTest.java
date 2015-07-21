/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import controller.SecretaryController;
import dao.StudentDAO;
import exceptions.InvalidFieldException;
import exceptions.MissingFieldException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;
import model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JoãoVitor
 */
public class StudentTest {
    
    public StudentTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testInsert() {
        Student student = new Student();
        student.setRegister("11037860");
        student.setName("João Vitor Elias Carvalho");
        student.setAddress("Rua Lemos de Brito");
        student.setEmail("joaovec2@gmail.com");
        student.setTelephone("2222-2222");
        student.setBirth_date("30/11/1993");
        student.setSemester( 6 );

        User user = new User("14256772740", "1234", student);
        student.setUser(user);
        
        try {
            SecretaryController.insertStudent(student);
            
            Student s = StudentDAO.getStudentByEmail("joaovec2@gmail.com");
            assertNotNull(s);
            
            if(s != null){
                StudentDAO.deleteStudent(s);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MissingFieldException ex) {
            Logger.getLogger(StudentTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(StudentTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFieldException ex) {
            Logger.getLogger(StudentTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
