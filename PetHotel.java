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

    public static void main(String[] args) throws InterruptedException  {
        // Read from csv files the order in day1
        try {
            incomingDog1 = getDogs("Dog_Entries.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        int noOfAdmin = 2;
        int totalOrder = incomingDog1.size();
        int eachAdmin = totalOrder/noOfAdmin;

        // initialises hotel
        Hotel hotel = new Hotel(10);
        
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
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
        
        // pool.shutdown();
        System.out.println("Time taken is : " + stopWatch.toString());
        System.out.println("Pet Hotel Simulation ends..." );
        System.out.println("");
        
        hotel.printRoomsReport();
        hotel.printTotalNumberOfDogsInHotel();
    }

    public static List<Dog> getDogs(String fileName) throws IOException {
        ArrayList<Dog> dogs = new ArrayList<Dog>();
        Files.lines(Paths.get(fileName)).forEach(line -> {
            // System.out.println(line);
            dogs.add(new Dog(line));
        });

        // System.out.println(dogs);
        return dogs;
    }
}