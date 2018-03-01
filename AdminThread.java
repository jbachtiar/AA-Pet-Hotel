/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hui Min
 */

 // as of now read the csv file multithreadedly
package runnable;
import java.util.*;
import main.*;
import objects.*;
import dao.*;
public class AdminThread extends Thread{

    private int start;
    private int end;

    public AdminThread(int s, int e){
        this.start = s;
        this.end = e;
    }

    @Override
    public void run(){
        //read each row of csv to retrieve each order
        //identify the dog size and find an available room

        HashMap<String, ArrayList<String>> dogGuide = Hotel.dogGuide;

        for(int i = this.start; i < this.end; i++){
            Dog dog = PetHotel.incomingDog1.get(i);
            String dogSize = dog.getSize();
            String rooms = dogGuide.get(dogSize).get(3);
            String[] roomIdString = rooms.split(",");

            int[] roomId = Arrays.stream(roomIdString).mapToInt(Integer::parseInt).toArray();
            for(int id : roomId){
                // System.out.println(id);
                Room room = Hotel.getRoomById(id);
                if(!room.isOccupied()){
                    // System.out.println("Inserting dog...");
                    try{
                        insertDog(dog, room);
                    }catch(InterruptedException e){

                    }
                    break;
                }
            }
        }

    
        
        //if room is available, allocate a dog to a room and increase the room occupancy
        //ad log book entry to log book for each successful orders
        // if room is unvailable, order is rejected.

        //at the end of the day, check which dog needs to leave on the next day
    }

    public void insertDog(Dog dog, Room room) throws InterruptedException{
        room.increaseOccupancy();
        room.addGuestsDogs(dog);
        room.addNotGroomedDogs(dog);
    
        //Thread.sleep(500);
    }
}
