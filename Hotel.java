package dao;

import java.util.*;

import objects.*;

public class Hotel{
    public static ArrayList<Room> roomList = new ArrayList<Room>();
	public static HashMap<String, ArrayList<String>> dogGuide;
    private int noOfRooms;

    // create 10 rooms from id 1-10
    public Hotel(int noOfRooms){
        this.roomList = new ArrayList<Room>();
        this.noOfRooms = noOfRooms;
        for(int i = 1; i <= this.noOfRooms; i++){
            // System.out.println(i);
            Room room = new Room(i);
            // System.out.println(room.getId());
            roomList.add(room);
        }
		
		dogGuide = new HashMap<String, ArrayList<String>>();
		// size, food, water, shampoo, roomIDs for these dogs, time taken
		dogGuide.put("Extra Small", new ArrayList<String>(){{add("2"); add("1"); add("1"); add("1, 2, 3"); add("2");}});
		dogGuide.put("Small", new ArrayList<String>(){{add("3"); add("2"); add("1"); add("4, 5, 6, 7"); add("2");}});
		dogGuide.put("Medium", new ArrayList<String>(){{add("5"); add("3"); add("2"); add("8, 9"); add("4");}});
		dogGuide.put("Large", new ArrayList<String>(){{add("7"); add("6"); add("3"); add("10"); add("5");}});
    }

    // retrieve room list
    public ArrayList<Room> GetRoomList(){
        return this.roomList;
    }
}