package dao;

import java.util.*;
import main.*;
import objects.*;

public class Hotel{
    public static ArrayList<Room> roomList = new ArrayList<Room>();
	public static HashMap<String, ArrayList<String>> dogGuide;
	public static HashMap<Integer, ArrayList<Integer>> roomGuide;
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
		dogGuide.put("Extra Small", new ArrayList<String>(){{add("2"); add("1"); add("1"); add("1,2,3"); add("2");}});
		dogGuide.put("Small", new ArrayList<String>(){{add("3"); add("2"); add("1"); add("4,5,6,7"); add("2");}});
		dogGuide.put("Medium", new ArrayList<String>(){{add("5"); add("3"); add("2"); add("8,9"); add("4");}});
		dogGuide.put("Large", new ArrayList<String>(){{add("7"); add("6"); add("3"); add("10"); add("5");}});
		
		roomGuide = new HashMap<Integer, ArrayList<Integer>>();
		// roomID, food, water, shampoo
		roomGuide.put(1, new ArrayList<Integer>(){{add(2); add(1); add(1);}});
		roomGuide.put(2, new ArrayList<Integer>(){{add(2); add(1); add(1);}});
		roomGuide.put(3, new ArrayList<Integer>(){{add(2); add(1); add(1);}});
		roomGuide.put(4, new ArrayList<Integer>(){{add(3); add(2); add(1);}});
		roomGuide.put(5, new ArrayList<Integer>(){{add(3); add(2); add(1);}});
		roomGuide.put(6, new ArrayList<Integer>(){{add(3); add(2); add(1);}});
		roomGuide.put(7, new ArrayList<Integer>(){{add(3); add(2); add(1);}});
		roomGuide.put(8, new ArrayList<Integer>(){{add(5); add(3); add(2);}});
		roomGuide.put(9, new ArrayList<Integer>(){{add(5); add(3); add(2);}});
		roomGuide.put(10, new ArrayList<Integer>(){{add(7); add(6); add(3);}});
    }

    // retrieve room list
    public ArrayList<Room> getRoomList(){
        return this.roomList;
    }

    // return room by id
    public static Room getRoomById(int id){
        for(Room room : roomList){
            if(room.getId() == id){
                return room;
            }
        }
        return null;
    }

    public void printRoomsReport(){
		
        for(Room room: this.roomList){
            System.out.print("Room " + room.getId() + "\t:\t" + room.getOccupancy() + " dogs");
			System.out.print("\t| Food Inventory: " + room.getAvailableFood());
			System.out.print("\t| Water Inventory: " + room.getAvailableWater());
			System.out.print("\t| Shampoo Inventory: " + room.getAvailableShampoo());
			System.out.println("\n");
        }
        System.out.println("");
    }

    public void printTotalNumberOfDogsInHotel(){
        int total = 0;
        for(Room room: this.roomList){
            total += room.getOccupancy();
        }
        System.out.println("Hotel has " + total + " dogs staying currently.");
    }
	
	public String checkNumDogsEntered(){
		//check no. of dogs that checked in the hotel at Day 1. 
		int total = 0;
        for(Room room: this.roomList){
            total += room.getOccupancy();
        }
		int day = PetHotel.day;
		int expectedDogs = 0;
		
		switch(day){
			case 1:
			expectedDogs = 64;
			break;
			
			case 2: 
			expectedDogs = 100;
			break;
			
			case 3: 
			expectedDogs = 100;
			break;
		}
		if(total == expectedDogs){
			return "OK";
		}
		return "Day " + day + " expects that " + expectedDogs + " dogs to be checked in. Actual: " + total;
	}
	
	public String checkNumGroomedDogs(){
		String returnText = "";
        for(Room room: this.roomList){
			if(room.getNotGroomedDogsSize() != 0 ){
				returnText += "\nRoom " + room.getId() + " : " + room.getNotGroomedDogsSize() + " dogs are not groomed. \n";
				if(room.getGroomedDogsSize() != room.getGuestsDogsSize()){
					returnText += "Room " + room.getId() + " : " + room.getGroomedDogsSize() + " dogs are groomed. \n";
					returnText += "Room " + room.getId() + " : " + room.getGuestsDogsSize() + " dogs are in the room. \n";
				}
			}
		}
		if(returnText.equals("")){
			returnText = "OK";
		}
		return returnText;
	}
	
	public String checkInventory(){
		int occupancy = 0;
		int foodLeft = 0;
		int waterLeft = 0;
		int shampooLeft = 0;
		String returnText = "";
		for(Room room: this.roomList){
			occupancy = room.getOccupancy();
			foodLeft = 100 - (occupancy * roomGuide.get(room.getId()).get(0));
			waterLeft = 100 - (occupancy * roomGuide.get(room.getId()).get(1));
			shampooLeft = 100 - (occupancy * roomGuide.get(room.getId()).get(2));
			if(room.getAvailableFood() != foodLeft ){
				returnText += "\n Room " + room.getId() + ": \t\t Expected  \t Actual \n Food Inventory: \t " + foodLeft + "\t \t " + room.getAvailableFood();
				if(room.getAvailableWater() != waterLeft){
					returnText += "\n Water Inventory: \t " + waterLeft + "\t \t " + room.getAvailableWater();
					if(room.getAvailableShampoo() != shampooLeft){
						returnText += "\n Shampoo Inventory: \t " + shampooLeft + "\t \t " + room.getAvailableShampoo() + "\n";
					}
				}
			}
		}
		
		if(returnText.equals("")){
			returnText = "OK";
		}
		return returnText;
	}
}

