package dao;

import java.util.ArrayList;

import Room.java;

public class Hotel{
    private ArrayList<Room> roomList;

    // create 10 rooms
    public RoomList(){
        for(int i = 0; i<10; i++){
            roomList.add(new Room(i));
        }
    }

    // retrieve room list
    public ArrayList<Room> GetRoomList(){
        return this.roomList;
    }
}