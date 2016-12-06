/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

/**
 *
 * @author KING
 */
public class Room {

    private static int nextRoomId = 0;
    private int id;
    private String name;
    private boolean lab = false;
    private int numberOfSeats;
    private int distance;

    public Room(String name, boolean lab, int numberOfSeats,int distance) {
        this.name = name;
        this.lab = lab;
        this.numberOfSeats = numberOfSeats;
        this.id = nextRoomId++;
        this.distance=distance;
    }

    public static int getNextRoomId() {
        return nextRoomId;
    }

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public boolean isLab() {
        return lab;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public int getDistance() {
        return distance;
    }
    

    public static void setNextRoomId(int nextRoomId) {
        Room.nextRoomId = nextRoomId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLab(boolean lab) {
        this.lab = lab;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
    
    public static void restartIDs(){
        nextRoomId = 0;
    }
    
    
}
