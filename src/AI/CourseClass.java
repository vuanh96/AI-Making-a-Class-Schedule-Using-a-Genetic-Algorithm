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
public class CourseClass {

    private int id;
    private Professor professor;
    private Course course;
    private ArrayList < StudentsGroup> groups;
    private int numberOfSeats=0;
    private boolean requiresLab; //co doi hoi Lab khong?
    private int duration;        //thoi gian hoc

    public CourseClass(int id, Professor professor, Course course, ArrayList<StudentsGroup> groups, boolean requiresLab, int duration) {
        this.id = id;
        this.professor = professor;
        this.course = course;
        this.groups = groups;
        this.requiresLab = requiresLab;
        this.duration = duration;
        
        //Gan CourseClass nay cho professor
        professor.addCourseClass(this);
        
        //Gan CourseClass nay cho cac nhom sinh vien tham gia va numberOfSeats = tong numberOfStudents
        for ( int i=0; i< groups.size(); i++) {
            StudentsGroup studentsGroup = groups.get(i);
            studentsGroup.addCourseClass(this);
            numberOfSeats += studentsGroup.getNumberOfStudents();
        } 
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

    public Course getCourse() {
        return course;
    }

    public ArrayList<StudentsGroup> getGroups() {
        return groups;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public boolean isLabRequired() {
        return requiresLab;
    }

    public int getDuration() {
        return duration;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setGroups(ArrayList<StudentsGroup> groups) {
        this.groups = groups;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public void setRequiresLab(boolean requiresLab) {
        this.requiresLab = requiresLab;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    
    
    //Returns TRUE neu lop nay trung it nhat 1 nhom SV voi lop c
    public boolean groupsOverlaps(CourseClass c) {
        for( int i=0; i< groups.size(); i++)
            for(int j=0; j< c.groups.size(); j++)
                if( groups.get(i) == c.getGroups().get(j))
                    return true;
        return false;
    }
    
    //Return TRUE neu lop nay trung giao vien p voi lop khac it nhat 1 tiet nao do
    public boolean professorOverlaps(CourseClass c) {
        return professor == c.getProfessor();
    }
    
    
    
    
}
