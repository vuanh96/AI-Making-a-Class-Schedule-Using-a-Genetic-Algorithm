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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KING
 */
public class InputFromMySQL {

    private static final String className = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/ai";
    private static final String user = "root";
    private static final String pass = "VuAnh123";

    private static ArrayList<Professor> profList;
    private static ArrayList<Course> courseList;
    private static ArrayList<CourseClass> classList;
    private static ArrayList<Room> roomList;
    private static ArrayList<StudentsGroup> groupList;

    private static Connection con;

    public static ArrayList<Professor> getProfList() {
        return profList;
    }

    public static ArrayList<Course> getCourseList() {
        return courseList;
    }

    public static ArrayList<CourseClass> getClassList() {
        return classList;
    }

    public static ArrayList<Room> getRoomList() {
        return roomList;
    }

    public static ArrayList<StudentsGroup> getGroupList() {
        return groupList;
    }
    

    // connect to database
    public static void connect() {
        try {
            Class.forName(className);
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Connect success!");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found!");
        } catch (SQLException e) {
            System.out.println("Error connection!");
        }
    }

    //Lay du lieu cua giao vien
    public static void getDataProfList() {
        try {
            String st = "select * from professor";

            profList = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement(st);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Professor p = new Professor(id, name);
                profList.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InputFromMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Lay du lieu mon hoc
    public static void getDataCourseList() {
        try {
            String st = "select * from course";

            courseList = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement(st);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Course c = new Course(id, name);
                courseList.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InputFromMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Lay du lieu cua phong
    public static void getDataRoomList() {
        try {
            String st = "select * from room";

            roomList = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement(st);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString(1);
                boolean lab = rs.getBoolean(2);
                int size = rs.getInt(3);
                int distance = rs.getInt(4);
                Room r = new Room(name, lab, size,distance);
                roomList.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InputFromMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public static Room getRoomById(int id) {
        for (Room r : roomList) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    //Lay du lieu cua cac group
    public static void getDataGroupList() {
        try {
            String st = "select * from studentsGroup";

            groupList = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement(st);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int size = rs.getInt(3);
                StudentsGroup g = new StudentsGroup(id, name, size);
                groupList.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InputFromMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Lay du lieu cua lop
    public static void getDataClassList() {
        String st = "select * from class";
        try {
            classList = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement(st);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //khoi tao pNew de luu giao vien cua lop
                Professor pNew = null;

                //khoi tao cNew de luu mon hoc cua lop
                Course cNew = null;

                //grooupListForClass de luu danh sach cac group cua class
                ArrayList<StudentsGroup> groupListForClass = new ArrayList<>();

                //Lay cac du lieu ve giao vien, mon hoc, yeu cau lab, thoi gian
                int classID = rs.getInt(1);
                int profID = rs.getInt(2);
                int courseID = rs.getInt(3);
                int duration = rs.getInt(4);
                boolean lab = rs.getBoolean(5);
                for (Professor p : profList) {
                    if (p.getId() == profID) {
                        pNew = p;
                    }
                }
                for (Course c : courseList) {
                    if (c.getId() == courseID) {
                        cNew = c;
                    }
                }

                //Lay danh sach group tu co so du lieu va add vao groupListForClass
                String st2 = "select group_id from register where class_id = ?";
                PreparedStatement ps2 = con.prepareStatement(st2);
                ps2.setInt(1, classID);
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    int groupID = rs2.getInt(1);
                    groupList.stream().filter((sg) -> (sg.getId() == groupID)).forEach((sg) -> {
                        groupListForClass.add(sg);
                    });
                }

                CourseClass courseClass = new CourseClass(classID, pNew, cNew, groupListForClass, lab, duration);
                classList.add(courseClass);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InputFromMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void getData() {
        //Lay du lieu tu database
        connect();
        getDataProfList();
        getDataCourseList();
        getDataRoomList();
        getDataGroupList();
        getDataClassList();
    }
}
