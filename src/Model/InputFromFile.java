/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import AI.Course;
import AI.CourseClass;
import AI.Professor;
import AI.Room;
import AI.StudentsGroup;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author KING
 */
public class InputFromFile {

    private static Scanner sc;
    private static final ArrayList<Professor> profList = new ArrayList();
    private static final ArrayList<CourseClass> classList = new ArrayList();
    private static final ArrayList<Course> courseList = new ArrayList();
    private static final ArrayList<StudentsGroup> groupList = new ArrayList();
    private static final ArrayList<Room> roomList = new ArrayList();

    public static ArrayList<Professor> getProfList() {
        return profList;
    }

    public static ArrayList<CourseClass> getClassList() {
        return classList;
    }

    public static ArrayList<Course> getCourseList() {
        return courseList;
    }

    public static ArrayList<StudentsGroup> getGroupList() {
        return groupList;
    }

    public static ArrayList<Room> getRoomList() {
        return roomList;
    }

    

    public static Room getRoomById(int id) {
        for (Room r : roomList) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    private static void readProfessor() {
        /**
         * @status: done
         */
        String temp;
        Professor i = new Professor();
        while (sc.hasNext()) {
            temp = sc.nextLine().trim();
            if (temp.equals("#end")) {
                break;
            }
            ArrayList<String> attr = new ArrayList(Arrays.asList(temp.split(" ")));

            switch (attr.get(0)) {
                case "id":
                    i.setId(Integer.parseInt(attr.get(2)));
                    break;
                case "name":
                    ArrayList<String> tmpName = new ArrayList();
                    for (int j = 2; j < attr.size(); j++) {
                        tmpName.add(attr.get(j));
                    }
                    i.setName(String.join(" ", tmpName));
                    break;

            }
        }

        profList.add(i);
    }

    private static void readStudentsGroup() {
        /**
         * @status: done
         */
        String temp;
        StudentsGroup i = new StudentsGroup();
        while (sc.hasNext()) {
            temp = sc.nextLine().trim();
            if (temp.equals("#end")) {
                break;
            }

            ArrayList<String> attr = new ArrayList(Arrays.asList(temp.split(" ")));

            switch (attr.get(0)) {
                case "id":
                    i.setId(Integer.parseInt(attr.get(2)));
                    break;
                case "name":
                    i.setName(attr.get(2));
                    break;
                case "size":
                    i.setNumberOfStudents(Integer.parseInt(attr.get(2)));
                    break;
            }
        }

        groupList.add(i);
    }

    private static void readCourse() {
        /**
         * @status: done
         */
        String temp;
        Course i = new Course();
        while (sc.hasNext()) {
            temp = sc.nextLine().trim();
            if (temp.equals("#end")) {
                break;
            }

            ArrayList<String> attr = new ArrayList(Arrays.asList(temp.split(" ")));

            switch (attr.get(0)) {
                case "id":
                    i.setId(Integer.parseInt(attr.get(2)));
                    break;
                case "name":
                    ArrayList<String> tmpName = new ArrayList();
                    for (int j = 2; j < attr.size(); j++) {
                        tmpName.add(attr.get(j));
                    }
                    i.setName(String.join(" ", tmpName));
                    break;
            }
        }

        courseList.add(i);
    }

    private static void readCourseClass() {
        String temp;
        int id = 0;
        Professor tempProf = new Professor();
        Course tempCourse = new Course();
        ArrayList<StudentsGroup> tempGroups = new ArrayList();
        boolean requiresLab = false;
        int duration = 0;
        while (sc.hasNext()) {
            temp = sc.nextLine().trim();
            if (temp.equals("#end")) {
                break;
            }

            ArrayList<String> attr = new ArrayList(Arrays.asList(temp.split(" ")));

            switch (attr.get(0)) {
                case "id":
                    id = Integer.parseInt(attr.get(2));
                    break;
                case "professor":
                    int idProf = Integer.parseInt(attr.get(2));
                    for (Professor i : profList) {
                        if (i.getId() == idProf) {
                            tempProf = i;
                        }
                    }
                    break;
                case "course":
                    int idCourse = Integer.parseInt(attr.get(2));
                    for (Course i : courseList) {
                        if (i.getId() == idCourse) {
                            tempCourse = i;
                        }
                    }
                    break;
                case "lab":
                    requiresLab = Boolean.parseBoolean(attr.get(2));
                    break;
                case "duration":
                    duration = Integer.parseInt(attr.get(2));
                    break;
                case "group":
                    int idGroup = Integer.parseInt(attr.get(2));
                    groupList.stream().filter((i) -> (i.getId() == idGroup)).forEach((i) -> {
                        tempGroups.add(i);
            });
                    break;
            }

        }
        classList.add(new CourseClass(id,tempProf, tempCourse, tempGroups, requiresLab, duration));
    }

    private static void readRoom() {
        /**
         * @status: done
         */
        String temp;
        String temp_name = new String();
        boolean temp_lab = false;
        int temp_size = 0;
        int temp_distance = 0;
        while (sc.hasNext()) {
            temp = sc.nextLine().trim();
            if (temp.equals("#end")) {
                break;
            }

            ArrayList<String> attr = new ArrayList(Arrays.asList(temp.split(" ")));

            switch (attr.get(0)) {
                case "name":
                    temp_name = attr.get(2);
                    break;
                case "lab":
                    temp_lab = Boolean.parseBoolean(attr.get(2));
                    break;
                case "size":
                    temp_size = Integer.parseInt(attr.get(2));
                    break;
                case "distance":
                    temp_distance = Integer.parseInt(attr.get(2));
            }
        }

        roomList.add(new Room(temp_name,temp_lab,temp_size,temp_distance) );
    }

    public static void readFile() throws FileNotFoundException {
        System.setIn(new FileInputStream("src//Model//ex.txt"));
        sc = new Scanner(System.in);
        String temp;

        while (sc.hasNext()) {
            temp = sc.nextLine();
            switch (temp) {
                case "#prof":
                    readProfessor();
                    break;
                case "#course":
                    readCourse();
                    break;
                case "#group":
                    readStudentsGroup();
                    break;
                case "#room":
                    readRoom();
                    break;
                case "#class":
                    readCourseClass();
                    break;
                default:
                    break;
            }

        }
    }
}
