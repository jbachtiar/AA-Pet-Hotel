package main;

import dao.*;
import runnable.*;
import objects.*;
import java.util.concurrent.*;
import java.util.*;

public class PetHotel{
    public static void main(String[] args){
    // initialises hotel with 10 rooms
       Hotel hotel = new Hotel(10); 
	    Logbook logbook = new Logbook();
		logbook.entries.add(new LogbookEntry(new Dog("Fifi", "Medium", 3), 8, 1, 3));
		//get the executor with the number of workers in 
	    ExecutorService exec = Executors.newFixedThreadPool(1);
		WorkerRunnable wr1 = new WorkerRunnable();
/* 		WorkerRunnable wr2 = new WorkerRunnable();
		WorkerRunnable wr3 = new WorkerRunnable();
		WorkerRunnable wr4 = new WorkerRunnable();
		WorkerRunnable wr5 = new WorkerRunnable();
		WorkerRunnable wr6 = new WorkerRunnable(); */
		exec.submit(wr1);
/* 		exec.submit(wr2);
		exec.submit(wr3);
		exec.submit(wr4);
		exec.submit(wr5);
		exec.submit(wr6); */
		
		for(Room r : hotel.roomList){
			System.out.println(r.getNotGroomedDogs().size() + " supposed to be 0");
			System.out.println(r.getGroomedDogs().size() + "supposed to have 1 dog");
			System.out.println(r.getAvailableFood());
		}
		exec.shutdown();
    }
}