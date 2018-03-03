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
import main.PetHotel;
import objects.*;
import java.util.*;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

import stopwatch.*;

public class WorkerThread extends Thread {

	public WorkerThread() {

	}

	@Override
	public void run() {
		while (StopWatch.getTime() < 60000) {
			for (Room r : Hotel.roomList) {
				while (r.getNotGroomedDogsSize() != 0) {
					Dog d = null;
					synchronized (r) {
						if (r.getNotGroomedDogsSize() != 0) {
							d = r.getNotGroomedDogs(r.getNotGroomedDogsSize() - 1);

							//remove the dog from the notGroomedDogs list 
							r.removeNotGroomedDogs(d);

						} else {
							continue;
						}
					}
					//get the list of requirements that the dog needs to use for food, water and shampoo
					ArrayList<String> reference = Hotel.dogGuide.get(d.getSize());

					// while grooming, the worker will reduce the supply of food
					int food = Integer.parseInt(reference.get(0));
					for (int i = 0; i < food; i++) {
						r.decreaseFood();
					}

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
					int sleep = Integer.parseInt(reference.get(4)) * 1000;
					try {
						Thread.sleep(sleep);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					// add the groomed dogs into the arraylist in each room to keep track of the dogs that are groomed already.
					r.addGroomedDogs(d);

					//System.out.println(r.getId());
					// break;
				} //end for

				//}//end while
				if (StopWatch.getTime() >= 60000) {
					break;
				}
			} //end for
			if (StopWatch.getTime() >= 60000) {
				break;
			}
		}
	}
}
