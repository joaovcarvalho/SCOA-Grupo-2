/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

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
public class ValidatorTest {
    
    public ValidatorTest() {
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
    public void testCpf() {
        assertTrue( Validator.validateCPF("14256772740") );
        assertTrue( !Validator.validateCPF("") );
        assertTrue( !Validator.validateCPF("jducjaodje#") );
        assertTrue( !Validator.validateCPF("123") );
        assertTrue( !Validator.validateCPF("31513231213") );
    }
    
    @Test
    public void testNumeric(){
        assertTrue( Validator.validateNumber("9") );
        assertTrue( Validator.validateNumber("1413") );
        assertTrue( !Validator.validateNumber("pt246848") );
        assertTrue( !Validator.validateNumber("dsadassa") );
        assertTrue( !Validator.validateNumber("%&#") );
        assertTrue( !Validator.validateNumber("") );
    }
    
    @Test 
    public void testPassword(){
        assertTrue( !Validator.validatePassword("12341234") );
        assertTrue( !Validator.validatePassword("dsadassa") );
        assertTrue( !Validator.validatePassword("2134a") );
        assertTrue( !Validator.validatePassword("") );
        assertTrue( Validator.validatePassword("pt246848") );
    }
    
    @Test
    public void testTelephone(){
        assertTrue( Validator.validateTelephone("999999999") );
        assertTrue( Validator.validateTelephone("88888888") );
        assertTrue( !Validator.validateTelephone("a9999dacr") );
        assertTrue( !Validator.validateTelephone("99999999999") );
        assertTrue( !Validator.validateTelephone("%#%994242r") );
        assertTrue( !Validator.validateTelephone("") );
    }
    
    @Test
    public void testEmail(){
        assertTrue( Validator.validateEmail("joaovec@gmail.com") );
        assertTrue( Validator.validateEmail("joaovec@dcc.ufrj.br") );
        assertTrue( !Validator.validateEmail("joaovec") );
        assertTrue( !Validator.validateEmail("@de.com") );
        assertTrue( !Validator.validateEmail("joao@gmail") );
        assertTrue( !Validator.validateEmail("%#$#@dcc.ufrj.br") );
        assertTrue( !Validator.validateEmail("") );
    }
    
    @Test
    public void testDate(){
        assertTrue( Validator.validateDate("30/11/1993") );
        assertTrue( !Validator.validateDate("30/30/1993") );
        assertTrue( !Validator.validateDate("70/11/1993") );
        assertTrue( !Validator.validateDate("30301993") );
        assertTrue( !Validator.validateDate("sduhaashuas4324") );
        assertTrue( !Validator.validateDate("%$#%#$") );
        assertTrue( !Validator.validateDate("") );
    }
    
    @Test
    public void testLink(){
        assertTrue( Validator.validateLink("http://hsuahusa.com") );
        assertTrue( Validator.validateLink("dsahudashd.com.br") );
        assertTrue( Validator.validateLink("dhusadhsuad.com") );
        assertTrue( Validator.validateLink("http://hushuasha.com") );
        assertTrue( !Validator.validateLink("5$#%#$%.com.br") );
        assertTrue( !Validator.validateLink("dhausdhsaud89") );
        assertTrue( !Validator.validateLink("") );
    }
    
}
