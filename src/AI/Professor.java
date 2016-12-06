/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import java.util.ArrayList;

/**
 *
 * @author KING
 */
public class Professor {

    private int id;
    private String name;
    private final ArrayList < CourseClass > courseClasses;
    
    public Professor() {
        name = new String();
        courseClasses = new ArrayList() ;
    }

    public Professor(int id, String name) {
        this.id = id;
        this.name = name;
        courseClasses = new ArrayList() ;
    }
    

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    public ArrayList<CourseClass> getCourseClasses() {
        return courseClasses;
    }
    
    public void addCourseClass(CourseClass courseClass){
        courseClasses.add(courseClass);
    }
     
}
