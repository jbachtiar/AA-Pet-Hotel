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
public class AdminRunnable implements Runnable{
    
    public AdminRunnable(){
    
    }
    


    @Override
    public void run(){
        //read each row of csv to retrieve each order
        //identify the dog size and find an available room
        
        //if room is available, allocate a dog to a room and increase the room occupancy
        //ad log book entry to log book for each successful orders
        // if room is unvailable, order is rejected.

        //at the end of the day, check which dog needs to leave on the next day
    }
}
