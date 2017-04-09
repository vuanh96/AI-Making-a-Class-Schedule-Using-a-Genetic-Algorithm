/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import Model.InputFromMySQL;
import View.ViewSchedule;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author KING
 */
public class Schedule {

    private double fitness = 0;
    private final int MAX_SCORE = 5;
    private final int numberOfCrossoverPoints = 5;
    private final int mutationSize = 5;

    private static final int DAY_HOURS = ViewSchedule.getDAY_HOURS();
    private static final int DAY_NUM = ViewSchedule.getDAY_NUM();
    private static final int ROOM_NUM = ViewSchedule.getROOM_NUM();

    private Vector< ArrayList<CourseClass>> slots;

    private Vector< Integer> classes;

    private Vector<Boolean> criteria;

    public Schedule() {
        slots = new Vector();
        classes = new Vector();
        criteria = new Vector();

    }

    public int getNumberOfCrossoverPoints() {
        return numberOfCrossoverPoints;
    }

    public int getMutationSize() {
        return mutationSize;
    }

    public double getFitness() {
        return fitness;
    }

    public Vector<ArrayList<CourseClass>> getSlots() {
        return slots;
    }

    public Vector<Integer> getClasses() {
        return classes;
    }

    public Vector<Boolean> getCriteria() {
        return criteria;
    }

    public void setClasses(Vector<Integer> classes) {
        this.classes = classes;
    }

    public void setSlots(Vector<ArrayList<CourseClass>> slots) {
        this.slots = slots;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public void setCriteria(Vector<Boolean> criteria) {
        this.criteria = criteria;
    }
    

    //Chuyen tu (HashMap) classes sang (Vector) slots
    private void fromClassesToSlots() {
        for (int i = 0; i < DAY_HOURS * DAY_NUM * ROOM_NUM; i++) {
            slots.add(new ArrayList());
        }
        //Duyet cac phan tu cua HashMap
        ArrayList<CourseClass> listClass = InputFromMySQL.getClassList();
        for (int i = 0; i < listClass.size(); i++) {
            CourseClass cc = (CourseClass) listClass.get(i);
            int duration = cc.getDuration();
            int possion = (int) classes.get(i);
            //Duyet cac tiet cua lop tu tiet bat dau den het thoi gian hoc cua lop
            for (int j = 0; j < duration; j++) {                
                    slots.get(possion + j).add(cc);
            }
        }
    }
        //---Basic init
//    public void initializeSchedule() {
//        ArrayList<CourseClass> listClass = InputFromMySQL.getClassList();
//        Random rd = new Random();
//        for( int i=0; i < listClass.size(); i++) {
//            int dur = listClass.get(i).getDuration();
//            int day = rd.nextInt(DAY_NUM);
//            int room = rd.nextInt(ROOM_NUM);
//            int time = rd.nextInt(DAY_HOURS/2 + 1 - dur);
//            switch (rd.nextInt(2)) {
//                case 0 : 
//                    break;
//                case 1:
//                    time += DAY_HOURS/2;
//                    break;
//            }
//            int pos = day * ROOM_NUM * DAY_HOURS + room * DAY_HOURS + time;
//            classes.add(pos);
//        }
//        fromClassesToSlots();
//        Fitness();
//    }
    //---Advanced init
    //Tao ra NST ngau nhien : Cac lop xep sat nhau sao cho 1 lop chi hoc 1 phong trong pham vi 6 tiet mot
    public void initializeSchedule() {
        //Tao danh sach cac lop voi so tiet bang nhau, cac day 4 ,3 , 2 tiet
        ArrayList<CourseClass> listClass = InputFromMySQL.getClassList();
        ArrayList<CourseClass> cc4 = new ArrayList();
        ArrayList<CourseClass> cc3 = new ArrayList();
        ArrayList<CourseClass> cc2 = new ArrayList();
        for (CourseClass i : listClass) {
            switch (i.getDuration()) {
                case 4:
                    cc4.add(i);
                    break;
                case 3:
                    cc3.add(i);
                    break;
                case 2:
                    cc2.add(i);
                    break;
            }
        }

        //Xao tron cac danh sach lop hoc
        Collections.shuffle(cc4);
        Collections.shuffle(cc3);
        Collections.shuffle(cc2);
        
        int size4 = cc4.size();
        int size3 = cc3.size();
        int size2 = cc2.size();

        int i = 0, j = 0, k = 0; // i,j,k la vi tri dang xet trong danh sach cac lop tren
        int next = 0;            // next la vi tri dang xet trong slots
        listClass.stream().forEach((_item) -> {
            classes.add(null);
        });
        Random rd = new Random();
        //Lay ngau nhien lop tu cac danh sach tren 
        for (int x = 0; x < listClass.size();) {
            switch (rd.nextInt(3) + 2) {
                case 2:
                    //Neu random so 2 thi kiem tra xem con lop 2 tiet khong, neu khong con thi break de random lop khac 
                    if (i < size2) {
                        classes.set(listClass.indexOf(cc2.get(i++)), next);
                        next += 2;
                        x++;
                        //Kiem tra xem con lop 4 tiet khong, neu con thi set vao ngay sau lop 2 tiet tren
                        if (k < size4) {
                            classes.set(listClass.indexOf(cc4.get(k++)), next);
                            next += 4;
                            x++;
                            break;
                        //Neu khong con lop 4 tiet thi kiem tra neu trong ds con 2 lop 2 tiet thi set 2 lop nay vao ngay sau lop 2 tiet ban dau
                        } else if (i < size2 - 1) {
                            classes.set(listClass.indexOf(cc2.get(i++)), next);
                            next += 2;
                            x++;
                            classes.set(listClass.indexOf(cc2.get(i++)), next);
                            next += 2;
                            x++;
                            break;
                        //Neu ko con lop 4 tiet hay 2 lop 2 tiet thi kiem tra con lop 3 tiet khong ...
                        } else if (j < size3) {
                            classes.set(listClass.indexOf(cc3.get(j++)), next);
                            next += 4;
                            x++;
                            break;
                        //Neu cung khong thi kiem tra neu con 1 lop 2 tiet thi lai set vao ngay sau lop 2 tiet bd
                        } else if (i < size2) {
                            classes.set(listClass.indexOf(cc2.get(i++)), next);
                            next += 4;
                            x++;
                        //Neu khong con TH nao thi break
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                case 3:
                    if (j < size3) {
                        classes.set(listClass.indexOf(cc3.get(j++)), next);
                        next += 3;
                        x++;
                        if (j < size3) {
                            classes.set(listClass.indexOf(cc3.get(j++)), next);
                            next += 3;
                            x++;
                            break;
                        } else if (i < size2) {
                            classes.set(listClass.indexOf(cc2.get(i++)), next);
                            next += 3;
                            x++;
                            break;
                        } else {
                            next += 3;
                            break;
                        }
                    } else {
                        break;
                    }
                case 4:
                    if (k < size4) {
                        classes.set(listClass.indexOf(cc4.get(k++)), next);
                        next += 4;
                        x++;
                        if (i < size2) {
                            classes.set(listClass.indexOf(cc2.get(i++)), next);
                            next += 2;
                            x++;
                            break;
                        } else {
                            next += 2;
                            break;
                        }
                    } else {
                        break;
                    }

            }
        }
        //Tao vector tu HashMap
        fromClassesToSlots();
        Fitness();
    }

    public void Fitness() {
        int score = 0;
        int daySize = DAY_HOURS * ROOM_NUM;
        
        int ci = 0;

        ArrayList<CourseClass> listClass = InputFromMySQL.getClassList();
        for (int x = 0; x < listClass.size(); x++) {
            int p = (int) classes.get(x);
            int day = p / daySize;
            int time = p % daySize;
            int room = time / DAY_HOURS;
            time = time % DAY_HOURS;

            CourseClass cc = (CourseClass) listClass.get(x);
            int dur = cc.getDuration();

            //Kiem tra phong trong (+1)
            boolean ro = false;
            for (int i = 0; i < dur; i++) {
                if (slots.get(p + i).size() > 1) {
                    ro = true;
                    break;
                }
            }
            if (!ro) {
                score++;
            }
            criteria.add(ci + 0, !ro);

            //Kiem tra phong co du cho khong? (+1)
            Room r = InputFromMySQL.getRoomById(room);
            criteria.add(ci + 1, r.getNumberOfSeats() >= cc.getNumberOfSeats());
            if (criteria.get(ci + 1)) {
                score++;
            }

            //Kiem tra phong co lab khong (+1)
            criteria.add(ci + 2, !cc.isLabRequired() || (cc.isLabRequired() && r.isLab()));
            if (criteria.get(ci + 2)) {
                score++;
            }

            //Kiem tra lop co bi trung giao vien tiet nao khong (+1)
            //va bi trung nhom SV tiet nao khong (+1)
            boolean po = false, go = false;
            total_overlap:
            for (int i = 0, t = day * daySize + time; i < ROOM_NUM; i++, t += DAY_HOURS) {
                for (int j = 0; j < dur; j++) {
                    //Kiem tra trung lap voi cac lop khac tung tiet 
                    ArrayList<CourseClass> lc = slots.get(t + j);
                    if (lc.isEmpty()) {
                        continue;
                    }
                    for (int k = 0; k < lc.size(); k++) {
                        if (cc != lc.get(k)) {

                            if (!po && cc.professorOverlaps(lc.get(k))) {
                                po = true;
                            }
                            if (!go && cc.groupsOverlaps(lc.get(k))) {
                                go = true;
                            }
                            if (po && go) {
                                break total_overlap;
                            }
                        }
                    }
                }
            }

            if (!po) {
                score++;
            }
            criteria.add(ci + 3, !po);
            if (!go) {
                score++;
            }
            criteria.add(ci + 4, !go);
            ci += 5;
        }
        fitness = (float) score / ((float) listClass.size() * MAX_SCORE);
    }
    
    //Tinh so lan vi pham rang buoc mem ( O day chi xet rang buoc mem la giao vien day o 2 noi xa nhau trong cung 1 ngay )
    public int softConflict() {
        int soft_conflict = 0;
        int daySize = DAY_HOURS * ROOM_NUM;
        ArrayList<CourseClass> classList = InputFromMySQL.getClassList();
        //Xet tung giao vien xem co vi pham rang buoc mem khong?
        for(Professor pr : InputFromMySQL.getProfList()) {
            ArrayList<CourseClass> class_list = pr.getCourseClasses();
            for(int i=0; i < class_list.size()-1; i++) {
                
                int index1 = classList.indexOf(class_list.get(i));
                int day1 = this.getClasses().get(index1) / daySize;
                int room1 = ( this.getClasses().get(index1) % daySize ) / DAY_HOURS;
                
                for( int j=i+1; j < class_list.size(); j++) {
                    
                    int index2 = classList.indexOf(class_list.get(j));
                    int day2 = this.getClasses().get(index2) / daySize;
                    int room2 = ( this.getClasses().get(index2) % daySize ) / DAY_HOURS;
                    
                    if((day2 == day1) && ( InputFromMySQL.getRoomById(room1).getDistance() != InputFromMySQL.getRoomById(room2).getDistance())) {
                        soft_conflict ++;
                    }
                }
            }
        }
        return soft_conflict;
    }
    public void showSchedule() {
        for (int i = 0; i < DAY_HOURS * DAY_NUM * ROOM_NUM; i++) {
            System.out.println("************" + i);
            for (CourseClass cc : slots.get(i)) {
                System.out.println("Class:" + cc.getId());
//                    System.out.println(cc.getProfessor().getName());
//                    System.out.println(cc.getCourse().getName());
//                    cc.getGroups().stream().forEach((sg) -> {
//                        System.out.println(sg.getName());
//                    });
//                    if (cc.isLabRequired()) {
//                        System.out.println("lab");
//                    }
                System.out.println("Duration :" + cc.getDuration());
                System.out.println(cc.getNumberOfSeats());
                System.out.println("##");
            }

        }
    }
    
    //---Basic Crossover
//    public Schedule Crossover(Schedule parent2) {
//        if (this == parent2) {
//            return this;
//        } else {
//            Random rd = new Random();
//            //tao con moi    
//            Schedule child = new Schedule();
//            int size = classes.size();
//            Vector<Boolean> cp = new Vector();
//            for (int i = 0; i < size; i++) {
//                cp.add(false);
//            }
//
//            //tao cac diem lai ghep danh dau TRUE
//            for (int i = numberOfCrossoverPoints; i > 0; i--) {
//                while (true) {
//                    int p = rd.nextInt(size);
//                    if (!cp.get(p)) {
//
//                        cp.set(p, Boolean.TRUE);
//                        break;
//                    }
//                }
//            }
//            boolean first = rd.nextBoolean();
//            for (int i = 0; i < size; i++) {
//                if (first) {
//                    child.classes.add(classes.get(i));
//                } else {
//
//                    child.classes.add(parent2.classes.get(i));
//                }
//                if (cp.get(i)) {
//                    first = !first;
//                }
//            }           
//            child.fromClassesToSlots();
//            child.Fitness();
//
//            return child;
//        }
//    }
    //---Advanced Crossover
    public Schedule Crossover(Schedule parent2) {
        if (this == parent2) {
            return this;
        } else {
            //tao con moi    
            Schedule child = new Schedule();
            int size = classes.size();
            Vector<Boolean> cp = new Vector();
            for (int i = 0; i < size; i++) {
                cp.add(false);
            }
            //Cac diem co score_class == 5 dc giu lai, cac diem con lai lai ghep
            for (int i = 0; i < size; i++) {
                int score_class = 0;
                for (int j = 0; j < 5; j++) {
                    if (criteria.get(5 * i + j)) {
                        score_class++;
                    }
                }
                if (score_class == 5) {
                    cp.set(i, Boolean.TRUE);
                }
            }

            for (int i = 0; i < size; i++) {
                if (cp.get(i)) {
                    child.classes.add(classes.get(i));
                } else {

                    child.classes.add(parent2.classes.get(i));
                }
            }
            child.fromClassesToSlots();
            child.Fitness();

            return child;
        }
    }

    //---Mutation
//    public void Mutation() {
//        Random rd = new Random();
//        ArrayList<CourseClass> listClass = InputFromMySQL.getClassList();
//
//        int numberOfClasses = listClass.size();
//
//        for (int i = mutationSize; i > 0; i--) {
//            int mpos = rd.nextInt(numberOfClasses);
//            //current
//            CourseClass cc1 = listClass.get(mpos);
//
//            // determine position of class randomly
//            int dur = cc1.getDuration();
//            int day = rd.nextInt(DAY_NUM);
//            int room = rd.nextInt(ROOM_NUM);
//            int time = rd.nextInt(DAY_HOURS/2 + 1 - dur);
//            switch (rd.nextInt(2)) {
//                case 0 : 
//                    break;
//                case 1:
//                    time += DAY_HOURS/2;
//                    break;
//            }
//            int pos = day * ROOM_NUM * DAY_HOURS + room * DAY_HOURS + time;
//            classes.set(mpos, pos);
//            this.fromClassesToSlots();
//        }
//        Fitness();
//    }
    
    public void Mutation() {
        Random rd = new Random();
        ArrayList<CourseClass> listClass = InputFromMySQL.getClassList();

        int numberOfClasses = listClass.size();

        for (int i = mutationSize; i > 0; i--) {
            int mpos = rd.nextInt(numberOfClasses);
            //current
            CourseClass cc1 = listClass.get(mpos);

            // determine position of class randomly
            int dur = cc1.getDuration();
            int day = rd.nextInt(DAY_NUM);
            int room = rd.nextInt(ROOM_NUM);
            int time = rd.nextInt(DAY_HOURS/2 + 1 - dur);
            switch (rd.nextInt(2)) {
                case 0 : 
                    break;
                case 1:
                    time += DAY_HOURS/2;
                    break;
            }
            int pos = day * ROOM_NUM * DAY_HOURS + room * DAY_HOURS + time;
            classes.set(mpos, pos);
        }
        this.fromClassesToSlots();
        Fitness();
    }
}

class RandomNotRepeat {

    //Tao ra mang bat ki cac so trong [0;k] khong trung lap
    public int[] nextInt(int k) {
        int[] randomArray = new int[k];
        Random rd = new Random();
        Vector v = new Vector();
        int iNew = 0;
        for (int i = 0; i < k;) {
            iNew = rd.nextInt(k);
            if (!v.contains(iNew)) {
                v.add(iNew);
                randomArray[i] = iNew;
                i++;
            }
        }
        return randomArray;
    }

}
