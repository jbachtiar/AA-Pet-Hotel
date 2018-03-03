package main;

import dao.*;
import objects.*;
import runnable.*;

import stopwatch.*;
import java.util.*;
import java.util.concurrent.*;

import javax.swing.border.EtchedBorder;

import com.sun.javafx.binding.ListExpressionHelper;

import java.io.*;
import java.nio.file.*;

public class PetHotel {
    public static List<Dog> incomingDog1 = new ArrayList<Dog>();
    public static int day = 1;

    public static void main(String[] args) throws InterruptedException {
		System.out.println("Optimal number of Admins is 4");
		System.out.println("Optimal number of Workers is 8");
		
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the no. of Admin/s: ");  
		int noOfAdmin = sc.nextInt();  
		System.out.println("No. of Admin/s keyed in: " + noOfAdmin);  
		
		System.out.println("Enter the no. of Worker/s: ");  
		int noOfWorker = sc.nextInt();  
		System.out.println("No. of Worker/s keyed in: " + noOfWorker);  
		
        // Read from csv files the order in day1
        try {
            incomingDog1 = getDogs("Dog_Entries_1.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

/*         int noOfAdmin = 1;
        int noOfWorker = 1; */
        int totalOrder = incomingDog1.size();
        int eachAdmin = totalOrder / noOfAdmin;

        int start = 0;
        int end = eachAdmin;

        // initialises hotel
        Hotel hotel = new Hotel(10);

        StopWatch stopWatch = new StopWatch();
        Scanner scanner = new Scanner(System.in);
        // ===================================== START OF DAY 1 =============================================
        // for(int i = 0; i <= 12; i++){
        //     System.out.println("        |                                                          |");
        // }
        // System.out.println("        ============================================================");
        System.out.println("\n________________________________________________________________________\n");
        System.out.println("Click enter to begin Pet Hotel Simulation.");
        String readString = scanner.nextLine();
        Thread[] adminThread = new Thread[noOfAdmin];
        Thread[] workerThread = new Thread[noOfWorker];
        if (readString.equals("")) {

            StopWatch.start();

            System.out.println("Pet Hotel Simulation begins...\n");
            System.out.println("---- DAY 1 ----");
            System.out.println("");
            hotel.printRoomsReport();
            hotel.printTotalNumberOfDogsInHotel();
            System.out.println("");

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
            StopWatch.stop();
            System.out.println("Time taken is : " + StopWatch.print());
            System.out.println("\n---- END OF DAY 1 ----");
            System.out.println("");
			

            for (int i = 0; i < hotel.getRoomList().size(); i++) {
                Room r = hotel.getRoomList().get(i);
                System.out.println(i + " " + r.getNotGroomedDogsSize() + " supposed to be 0");
                if (i == 0) {
                    for (Dog d : r.getNotGroomedDogs()) {
                        System.out.println(d.getName());
                    }
                }
                System.out.println(i + " " + r.getGroomedDogsSize() + " supposed to have " + r.getOccupancy());
                //System.out.println(i + " " + r.getAvailableFood());	
            }

            System.out.println("");
            hotel.printRoomsReport();
            hotel.printTotalNumberOfDogsInHotel();
			
            //System.out.println("No of dogs in Logbook : " + Logbook.entries.size());
			if(hotel.checkNumDogsEntered().equals("OK") && hotel.checkNumGroomedDogs().equals("OK") && hotel.checkInventory().equals("OK")){
				System.out.println("Day " + PetHotel.day + " has ended! The job has been successfully completed!\n");
			}else{
				System.out.println("Error!! Day" + PetHotel.day + "'s job could not be completedly successfully!");
				System.out.println("Please try again!");
				System.out.println("Errors that occurred are at: ");
				if(!hotel.checkNumDogsEntered().equals("OK")){
					System.out.println(hotel.checkNumDogsEntered());
				}
				if(!hotel.checkNumGroomedDogs().equals("OK")){
					System.out.println(hotel.checkNumGroomedDogs());
				}
				if(!hotel.checkInventory().equals("OK")){
					System.out.println(hotel.checkInventory());
				}
			}
        }

        // ===================================== END OF DAY 1 =============================================
        
        StopWatch.reset();
		
        // System.out.println("Current time : " + StopWatch.print());
        day = 2;
        int noOfDogsRemoved = removeOverBooked(day);
        reset();

        try {
            incomingDog1 = getDogs("Dog_Entries_2.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        totalOrder = incomingDog1.size();
        eachAdmin = totalOrder / noOfAdmin;

        start = 0;
        end = eachAdmin;

        System.out.println("\n________________________________________________________________________\n");
        System.out.println("Click enter to begin Pet Hotel Simulation DAY 2.");
        readString = scanner.nextLine();

        // ===================================== START OF DAY 2 =============================================

        if (readString.equals("")) {
            StopWatch.start();
            System.out.println("\nPet Hotel Simulation begins...\n");
            System.out.println("No of dogs that left in Day 1 : " + noOfDogsRemoved);
            System.out.println("\n---- DAY 2 ----");

            System.out.println("");
            hotel.printRoomsReport();
            hotel.printTotalNumberOfDogsInHotel();

            
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
            // Thread[] workerThread = new Thread[noOfWorker];
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

            StopWatch.stop();
            System.out.println("Time taken is : " + StopWatch.print());
            System.out.println("\n---- END OF DAY 2 ----");
            System.out.println("");

            for (int i = 0; i < hotel.getRoomList().size(); i++) {
                Room r = hotel.getRoomList().get(i);
                System.out.println(i + " " + r.getNotGroomedDogsSize() + " supposed to be 0");
                if (i == 0) {
                    for (Dog d : r.getNotGroomedDogs()) {
                        System.out.println(d.getName());
                    }
                }
                System.out.println(i + " " + r.getGroomedDogsSize() + " supposed to have " + r.getOccupancy());
                //System.out.println(i + " " + r.getAvailableFood());	
            }

            System.out.println("");
            hotel.printRoomsReport();
            hotel.printTotalNumberOfDogsInHotel();
			
			//checks for the integrity of the output
			if(hotel.checkNumDogsEntered().equals("OK") && hotel.checkNumGroomedDogs().equals("OK") && hotel.checkInventory().equals("OK")){
				System.out.println("Day " + PetHotel.day + " has ended! The job has been successfully completed!\n");
			}else{
				System.out.println("Error!! Day" + PetHotel.day + "'s job could not be completedly successfully!");
				System.out.println("Please try again!");
				System.out.println("Errors that occurred are at: ");
				if(!hotel.checkNumDogsEntered().equals("OK")){
					System.out.println(hotel.checkNumDogsEntered());
				}
				if(!hotel.checkNumGroomedDogs().equals("OK")){
					System.out.println(hotel.checkNumGroomedDogs());
				}
				if(!hotel.checkInventory().equals("OK")){
					System.out.println(hotel.checkInventory());
				}
			}

        }

         // ===================================== END OF DAY 2 =============================================
        
         StopWatch.reset();
         System.out.println("Current time : " + StopWatch.print());
         day = 3;
         noOfDogsRemoved = removeOverBooked(day);
         reset();
        
         try {
             incomingDog1 = getDogs("Dog_Entries_3.csv");
         } catch (IOException e) {
             e.printStackTrace();
         }
 
         totalOrder = incomingDog1.size();
         eachAdmin = totalOrder / noOfAdmin;
 
         start = 0;
         end = eachAdmin;
 
         System.out.println("\n________________________________________________________________________\n");
         System.out.println("Click enter to begin Pet Hotel Simulation DAY 3.");
         readString = scanner.nextLine();
 
         // ===================================== START OF DAY 3 =============================================
 
         if (readString.equals("")) {
             StopWatch.start();
             System.out.println("\nPet Hotel Simulation begins...\n");
             System.out.println("No of dogs that left in Day 2 : " + noOfDogsRemoved);
             System.out.println("\n---- DAY 3 ----");
 
             System.out.println("");
             hotel.printRoomsReport();
             hotel.printTotalNumberOfDogsInHotel();
 
             
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
             // Thread[] workerThread = new Thread[noOfWorker];
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
 
             StopWatch.stop();
             System.out.println("Time taken is : " + StopWatch.print());
             System.out.println("\n---- END OF DAY 3 ----");
             System.out.println("");
 
             for (int i = 0; i < hotel.getRoomList().size(); i++) {
                 Room r = hotel.getRoomList().get(i);
                 System.out.println(i + " " + r.getNotGroomedDogsSize() + " supposed to be 0");
                 if (i == 0) {
                     for (Dog d : r.getNotGroomedDogs()) {
                         System.out.println(d.getName());
                     }
                 }
                 System.out.println(i + " " + r.getGroomedDogsSize() + " supposed to have " + r.getOccupancy());
                 //System.out.println(i + " " + r.getAvailableFood());	
             }
 
             System.out.println("");
             hotel.printRoomsReport();
             hotel.printTotalNumberOfDogsInHotel();
 
         }
        
    }

    public static void reset() {
        for (Room room : Hotel.roomList) {
            room.restoreFood();
            room.restoreShampoo();
            room.restoreWater();

            room.setNotGroomedDogs(room.getGroomedDogs());
            room.resetGroomedDogs();
        }
    }

    public static int removeOverBooked(int day) {

        int count = 0;
        // remove over due dogs
        Iterator<LogbookEntry> iter = Logbook.entries.iterator();

        while (iter.hasNext()) {
            LogbookEntry entry = iter.next();
            if (entry.getEndDay() == PetHotel.day) {
                Dog dog = entry.getDog();
                int roomId = entry.getRoomID();
                iter.remove();
                Room room = Hotel.getRoomById(roomId);
                room.removeGroomedDogs(dog);
                room.removeGuestsDogs(dog);
                room.decreaseOccupancy();
                count++;
            }
        }

        return count;

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