/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author dougl_000
 */
public class StudentGrade {
    
    private String course_name;
    private int course_grade;

    public StudentGrade(String course_name, int course_grade) {
        this.course_name = course_name;
        this.course_grade = course_grade;
    }
    public StudentGrade() {
        
    }
    
    /**
     * @return the course_name
     */
    public String getCourse_name() {
        return course_name;
    }

    /**
     * @param course_name the course_name to set
     */
    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    /**
     * @return the course_grade
     */
    public int getCourse_grade() {
        return course_grade;
    }

    /**
     * @param course_grade the course_grade to set
     */
    public void setCourse_grade(int course_grade) {
        this.course_grade = course_grade;
    }

    @Override
    public String toString() {
        return this.course_name + this.course_grade; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
