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
            incomingDog1 = getDogs("Dog_Entries_1.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        int noOfAdmin = 4;
        int noOfWorker = 6;
        int totalOrder = incomingDog1.size();
        int eachAdmin = totalOrder / noOfAdmin;

        int start = 0;
        int end = eachAdmin;

        // initialises hotel
        Hotel hotel = new Hotel(10);

        StopWatch stopWatch = new StopWatch();
        Scanner scanner = new Scanner(System.in);
        // for(int i = 0; i <= 12; i++){
        //     System.out.println("        |                                                          |");
        // }
        // System.out.println("        ============================================================");
        System.out.println("\n________________________________________________________________________\n");
        System.out.println("Click enter to begin Pet Hotel Simulation.");
        String readString = scanner.nextLine();

        if (readString.equals("")) {

            stopWatch.start();

            System.out.println("Pet Hotel Simulation begins...\n");
            System.out.println("---- DAY 1 ----");
            System.out.println("");
            hotel.printRoomsReport();
            hotel.printTotalNumberOfDogsInHotel();
            System.out.println("");

            Thread[] adminThread = new Thread[noOfAdmin];
            // create all adminthreads
            for (int i = 0; i < noOfAdmin; i++) {
                if (i != (noOfAdmin - 1)) {
                    adminThread[i] = new AdminThread(start, end); // pass to constructor, start and end index of incoming dog array
                    start = end;
                    end = start + eachAdmin;
                    adminThread[i].start();
                } else {
                    adminThread[i] = new AdminThread(start, totalOrder);
                    adminThread[i].start();
                }
            }
            Thread[] workerThread = new Thread[noOfWorker];
            for (int i = 0; i < noOfWorker; i++) {
                workerThread[i] = new WorkerThread();
                workerThread[i].start();
            }
            // main thread will join all Admin threads
            for (int i = 0; i < noOfAdmin; i++) {
                adminThread[i].join();
                //System.out.print("hello");
            }
            // while (stopWatch.getTime() != 60000) {
            //     Thread.sleep(1);
            // }
            
            for (int i = 0; i < noOfWorker; i++) {
                workerThread[i].join();
                //System.out.print("hello");
            }

        
            //exec.shutdown();
            for (int i = 0; i < hotel.getRoomList().size(); i++) {
                Room r = hotel.getRoomList().get(i);
                System.out.println(i + " " + r.getNotGroomedDogs().size() + " supposed to be 0");
                if (i == 0) {
                    for (Dog d : r.getNotGroomedDogs()) {
                        System.out.println(d.getName());
                    }
                }
                System.out.println(i + " " + r.getGroomedDogs().size() + " supposed to have " + r.getOccupancy());
                //System.out.println(i + " " + r.getAvailableFood());	
            }

            stopWatch.stop();
            System.out.println("Time taken is : " + StopWatch.print());
            System.out.println("\n---- END OF DAY 1 ----");
            System.out.println("");

            hotel.printRoomsReport();
            hotel.printTotalNumberOfDogsInHotel();

        }

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