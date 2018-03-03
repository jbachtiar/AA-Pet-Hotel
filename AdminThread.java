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
import stopwatch.StopWatch;
import dao.*;

public class AdminThread extends Thread {

    private int start;
    private int end;

    // intialises admin thread with start and end index of the incomingDog array list
    public AdminThread(int s, int e) {
        this.start = s;
        this.end = e;
    }

    @Override
    public void run() {
        //read each row of csv to retrieve each order
        //identify the dog size and find an available room
        while (StopWatch.getTime() < 60000) {
            HashMap<String, ArrayList<String>> dogGuide = Hotel.dogGuide;
            for (int i = this.start; i < this.end; i++) {
                Dog dog = PetHotel.incomingDog1.get(i);
                String dogSize = dog.getSize();
                String rooms = dogGuide.get(dogSize).get(3);
                String[] roomIdString = rooms.split(",");

                int[] roomId = Arrays.stream(roomIdString).mapToInt(Integer::parseInt).toArray();
                for (int id : roomId) {
                    Room room = Hotel.getRoomById(id);
                    synchronized (room) {
                        if (!room.isOccupied()) {
                            try {
                                insertDog(dog, room);
                            } catch (InterruptedException e) {

                            }
                            break;
                        }
                    }
                }
                
				// check to ensure that the thread stops at 60000ms or more
                if (StopWatch.getTime() >= 60000) {
                    break;
                }
            }
            
			    // check to ensure that the thread stops at 60000ms or more
            if (StopWatch.getTime() >= 60000) {
                break;
            }
            return;
        }

      
    }

    // insert dog into a room in the hotel, increase the room occupancy, add dog to the guest and not groomed dog list
    // also add dog into the logbook
    public void insertDog(Dog dog, Room room) throws InterruptedException {
        room.increaseOccupancy();
        room.addGuestsDogs(dog);
        room.addNotGroomedDogs(dog);
        synchronized (dog) {
            LogbookEntry entry = new LogbookEntry(dog, room.getId(), PetHotel.day, PetHotel.day + dog.getDuration());
            Logbook.addEntry(entry);
        }

        Thread.sleep(500);
    }
}
