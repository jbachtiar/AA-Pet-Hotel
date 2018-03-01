package main;

import dao.*;
import objects.*;
import runnable.AdminRunnable;

import java.util.*;
import java.util.concurrent.*;

import com.sun.javafx.binding.ListExpressionHelper;

import java.io.*;
import java.nio.file.*;

public class PetHotel {
    public static List<Dog> incomingDog1 = new ArrayList<Dog>();

    public static void main(String[] args) {
        // Read from csv files the order in day1
        try {
            incomingDog1 = getDogs("Dog_Entries.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // initialises hotel
        Hotel hotel = new Hotel(10);

        ExecutorService pool = Executors.newFixedThreadPool(1);

        AdminRunnable admin = new AdminRunnable();

        pool.submit(admin);
        
        pool.shutdown();
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