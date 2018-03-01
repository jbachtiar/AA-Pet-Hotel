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
import dao.*;
import objects.*;
import java.util.*;

public class WorkerRunnable extends Thread{
    

    public WorkerRunnable(){
        
    }
    
    @Override
    public void run(){
		// if there is any dog, worker starts grooming
		for(Room r: Hotel.roomList){
			//System.out.println("testing");
			//check if there is any dogs in the notGroomedDogs list
			while(r.getGroomedDogs().size() != r.getGuestsDogs().size()){
				//System.out.println("testing1");
				for(Dog d: r.getNotGroomedDogs()){
					//System.out.println("testing2");
					synchronized(r){
						//get the 1st dog in the list
						d = r.getNotGroomedDogs().get(0);
						System.out.println(d.getName());
						
						//remove the dog from the notGroomedDogs list 
						ArrayList<Dog> ungroomedDogs = r.getNotGroomedDogs();
						ungroomedDogs.remove(d);
					}
					
					//get the list of requirements that the dog needs to use for food, water and shampoo
					ArrayList<String> reference = Hotel.dogGuide.get(d.getSize());
					System.out.println(reference.get(0));
					
					
					// while grooming, the worker will reduce the supply of food
					int food = Integer.parseInt(reference.get(0));
					for(int i = 0; i < food; i++){
						r.decreaseFood();
					}
					System.out.println("food: " + r.getAvailableFood());
					//reduce water
					int water = Integer.parseInt(reference.get(1));
					for(int i = 0; i < water; i++){
						r.decreaseWater();
					}
					
					//reduce shampoo within a room according to the requirement of the different dog sizes
					int shampoo = Integer.parseInt(reference.get(2));
					for(int i = 0; i < shampoo; i++){
						r.decreaseShampoo();
					}
					
					// worker thread will sleep according to the size of the dog while grooming to simulate that the worker is occupied.
					int sleep = Integer.parseInt(reference.get(4)) * 1000;
					try{
						Thread.sleep(sleep);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
					
					synchronized(r){
						// add the groomed dogs into the arraylist in each room to keep track of the dogs that are groomed already.
						ArrayList<Dog> groomedDogs = r.getGroomedDogs();
						groomedDogs.add(d);
						r.setGroomedDogs(groomedDogs);
					}
					break;
				}
			}//end while
		}//end for
    }
}
