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
		for(int i = 0; i < 10; i++){
			logbook.entries.add(new LogbookEntry(new Dog("Fifi", "Medium", 3), 8, 1, 3));
		}
		//get the executor with the number of workers in 
	    //ExecutorService exec = Executors.newFixedThreadPool(1);
		WorkerRunnable wr1 = new WorkerRunnable();
		//Thread[] threads = new Thread[1];
		
/* 		while (!exec.isTerminated() && !exec.isShutdown()) {
		  // create all Printer threads and start them 
		  for (int i = 0; i < 1; i++) {
			threads[i] = new WorkerRunnable(); // pass to constructor a number as a String
			exec.submit(threads[i]);
		  }

		  // main thread will join all Printer threads
		  for (int i = 0; i < 1; i++) {
			try{
				threads[i].join();
			}catch(InterruptedException e){
				System.out.println(e);
			}
			//System.out.print("hello");
		  }
		  System.out.println("\nrunning last statement in main()...");
		  exec.shutdown();
		} */
 		WorkerRunnable wr2 = new WorkerRunnable();
		WorkerRunnable wr3 = new WorkerRunnable();
		WorkerRunnable wr4 = new WorkerRunnable();
		WorkerRunnable wr5 = new WorkerRunnable();
		WorkerRunnable wr6 = new WorkerRunnable();
		wr1.start();
		wr2.start();
		wr3.start();
		wr4.start();
		wr5.start();
		wr6.start();
		
		try{
			wr1.join();
			wr2.join();
			wr3.join();
			wr4.join();
			wr5.join();
			wr6.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		//exec.submit(wr1);
/* 		exec.submit(wr2);
		exec.submit(wr3);
		exec.submit(wr4);
		exec.submit(wr5);
		exec.submit(wr6); */
		try{
			wr1.join();
			System.out.println("wr1 join");
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		//exec.shutdown();
		for(int i = 0; i < hotel.getRoomList().size(); i++){
			Room r = hotel.getRoomList().get(i);
			System.out.println(i + " " + r.getNotGroomedDogs().size() + " supposed to be 0");
			System.out.println(i + " " + r.getGroomedDogs().size() + "supposed to have 1 dog");
			System.out.println(i + " " + r.getAvailableFood());	
		}
		
    }
}