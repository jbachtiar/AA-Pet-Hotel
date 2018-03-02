package main;

import dao.*;
import objects.*;
import runnable.*;

import stopwatch.*;
import java.util.*;
import java.util.concurrent.*;

import com.sun.javafx.binding.ListExpressionHelper;

import java.io.*;
import java.nio.file.*;

public class PetHotel {
    public static List<Dog> incomingDog1 = new ArrayList<Dog>();

    public static void main(String[] args) throws InterruptedException {
        // Read from csv files the order in day1
        try {
            incomingDog1 = getDogs("Dog_Entries.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        int noOfAdmin = 2;
        int totalOrder = incomingDog1.size();
        int eachAdmin = totalOrder / noOfAdmin;

        // initialises hotel
        Hotel hotel = new Hotel(10);

        //StopWatch stopWatch = new StopWatch();
        StopWatch.start();

        System.out.println("Pet Hotel Simulation begins...");
        System.out.println("");
        hotel.printRoomsReport();
        hotel.printTotalNumberOfDogsInHotel();
        System.out.println("");

        ExecutorService pool = Executors.newFixedThreadPool(1);

        AdminThread admin1 = new AdminThread(0, eachAdmin);
        AdminThread admin2 = new AdminThread(eachAdmin, totalOrder);

        // pool.execute(admin);
        admin1.start();
        admin2.start();

        admin2.join();
        admin1.join();

        WorkerRunnable wr1 = new WorkerRunnable();
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

        try {
            wr1.join();
            wr2.join();
            wr3.join();
            wr4.join();
            wr5.join();
            wr6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // pool.shutdown();
        System.out.println("Time taken is : " + StopWatch.print());
        System.out.println("Pet Hotel Simulation ends...");
        System.out.println("");
        
        hotel.printRoomsReport();
        hotel.printTotalNumberOfDogsInHotel();

        	//exec.shutdown();
		for(int i = 0; i < hotel.getRoomList().size(); i++){
			Room r = hotel.getRoomList().get(i);
			System.out.println(i + " " + r.getNotGroomedDogs().size() + " supposed to be 0");
			if(i == 0){
				for(Dog d : r.getNotGroomedDogs()){
					System.out.println(d.getName());
				}
			}
			System.out.println(i + " " + r.getGroomedDogs().size() + " supposed to have " + r.getOccupancy());
			//System.out.println(i + " " + r.getAvailableFood());	
		}
		
        // System.out.println(dogs);
    
    }

    public static List<Dog> getDogs(String fileName) throws IOException {
        ArrayList<Dog> dogs = new ArrayList<Dog>();
        Files.lines(Paths.get(fileName)).forEach(line -> {
            // System.out.println(line);
            dogs.add(new Dog(line));
        });
        return dogs;
    }

    
	
}