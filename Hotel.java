package dao;

import java.util.ArrayList;

import objects.*;

public class Hotel{
    private ArrayList<Room> roomList;
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
    }

    // retrieve room list
    public ArrayList<Room> GetRoomList(){
        return this.roomList;
    }
}