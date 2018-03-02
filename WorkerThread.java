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
import stopwatch.*;

public class WorkerThread extends Thread {

	public WorkerThread() {

	}

	@Override
	public void run() {
		// if there is any dog, worker starts grooming
		while (StopWatch.getTime() != 60000) {
			// System.out.print(StopWatc	h.getTime());
			for (Room r : Hotel.roomList) {
				//check if there is any dogs in the notGroomedDogs list
				//while(r.getGroomedDogs().size() != r.getGuestsDogs().size()){
				//if(r.getNotGroomedDogs().size() != 0){

				while (r.getNotGroomedDogs().size() != 0) {
					Dog d = null;
					synchronized (r) {
						d = r.getNotGroomedDogs().get(r.getNotGroomedDogs().size() - 1);

						//remove the dog from the notGroomedDogs list 
						List<Dog> ungroomedDogs = r.getNotGroomedDogs();
						ungroomedDogs.remove(d);
						r.setNotGroomedDogs(ungroomedDogs);
					}

					//get the list of requirements that the dog needs to use for food, water and shampoo
					ArrayList<String> reference = Hotel.dogGuide.get(d.getSize());
					//System.out.println(reference.get(0));

					// while grooming, the worker will reduce the supply of food
					int food = Integer.parseInt(reference.get(0));
					for (int i = 0; i < food; i++) {
						r.decreaseFood();
					}
					//System.out.println("food: " + r.getAvailableFood());
					//reduce water
					int water = Integer.parseInt(reference.get(1));
					for (int i = 0; i < water; i++) {
						r.decreaseWater();
					}

					//reduce shampoo within a room according to the requirement of the different dog sizes
					int shampoo = Integer.parseInt(reference.get(2));
					for (int i = 0; i < shampoo; i++) {
						r.decreaseShampoo();
					}

					// worker thread will sleep according to the size of the dog while grooming to simulate that the worker is occupied.
					// int sleep = Integer.parseInt(reference.get(4)) * 1000;
					// try{
					// 	Thread.sleep(sleep);
					// }catch(InterruptedException e){
					// 	e.printStackTrace();
					// }

					synchronized (r) {
						// add the groomed dogs into the arraylist in each room to keep track of the dogs that are groomed already.
						List<Dog> groomedDogs = r.getGroomedDogs();
						groomedDogs.add(d);
						r.setGroomedDogs(groomedDogs);
					}
					//System.out.println(r.getId());
					break;
				} //end for

				//}//end while

			} //end for
		}

	}
}
