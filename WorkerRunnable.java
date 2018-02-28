/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hui Min
 */
package runnable;
public class WorkerRunnable implements Runnable{
    
    public WorkerRunnable(){
        
    }
    
    @Override
    public void run(){
        // to check if there is any dog in the logbook
        // if there is any dog, worker starts grooming
        // worker thread will sleep according to the size of the dog while grooming to simulate that the worker is occupied.
        // while grooming, the worker will reduce the supply of food, water and shampoo within a room according to the requirement of the different dog sizes
        // add the groomed dogs into the arraylist in each room to keep track of the dogs that are groomed already.
    }
}
