package objects;
import java.util.*;
import java.util.concurrent.*;

public class Room{
    // Set room, food, water and shampoo capacity
    private final int CAPACITY = 10;
    private final int FOOD = 100;
    private final int WATER = 100;
    private final int SHAMPOO = 100;
    
    private int id;
    private int occupancy;

    private int availFood;
    private int availWater;
    private int availShampoo;

    private List<Dog> guestsDogs;
    private List<Dog> groomedDogs;
	private List<Dog> notGroomedDogs;
    
    // intialises empty room and full supply
    public Room(int id){
        this.id = id;
        this.occupancy = 0;
        this.availFood = this.FOOD;
        this.availWater = this.WATER;
        this.availShampoo = this.SHAMPOO;
        this.groomedDogs = Collections.synchronizedList(new ArrayList<Dog>());
        this.notGroomedDogs = Collections.synchronizedList(new ArrayList<Dog>());
		this.guestsDogs = Collections.synchronizedList(new ArrayList<Dog>());
    }
    

    // get the room ID
    public int getId(){
        return this.id;
    }

    public synchronized void addGroomedDogs(Dog dog){
        this.groomedDogs.add(dog);
    }
    public synchronized void addGuestsDogs(Dog dog){
        this.guestsDogs.add(dog);
    }
    public synchronized void addNotGroomedDogs(Dog dog){
        this.notGroomedDogs.add(dog);
    }

    public synchronized void removeGroomedDogs(Dog dog){
        this.groomedDogs.remove(dog);
    }
    public synchronized void removeGuestsDogs(Dog dog){
        this.guestsDogs.remove(dog);
    }
    public synchronized void removeNotGroomedDogs(Dog dog){
        this.notGroomedDogs.remove(dog);
    }
	
	public synchronized Dog getGroomedDogs(int index){
        return this.groomedDogs.get(index);
    }
    public synchronized Dog getGuestsDogs(int index){
        return this.guestsDogs.get(index);
    }
    public synchronized Dog getNotGroomedDogs(int index){
        return this.notGroomedDogs.get(index);
    }

	public synchronized int getGroomedDogsSize(){
        return this.groomedDogs.size();
    }
    public synchronized int getGuestsDogsSize(){
        return this.guestsDogs.size();
    }
    public synchronized int getNotGroomedDogsSize(){
        return this.notGroomedDogs.size();
    }


    // Restore the shampoo, food, and water supply
    public void restoreShampoo(){
        this.availShampoo = this.SHAMPOO;
    }
    public void restoreFood(){
        this.availFood = this.FOOD;
    }
    public void restoreWater(){
        this.availWater = this.WATER;
    }

    // Decrease the available food, water and shampoo by 1  
    public synchronized void decreaseFood(){
        this.availFood--;
    }
    public synchronized  void decreaseWater(){
        this.availWater--;
    }
    public synchronized void decreaseShampoo(){
        this.availShampoo--;
    }
    // Get available food, water and shampoo
    public int getAvailableFood(){
        return this.availFood;
    }
    public int getAvailableWater(){
        return this.availWater;
    }
    public int getAvailableShampoo(){
        return this.availShampoo;
    }

    // increase room occupancy by 1
    public synchronized void increaseOccupancy(){
        this.occupancy++;
    }
    // decerase room occupancy by 1
    public synchronized void decreaseOccupancy(){
        this.occupancy--;
    }
    // get room occupancy
    public int getOccupancy(){
        return this.occupancy;
    }
	
	public synchronized List<Dog> getGroomedDogs(){
		return this.groomedDogs;
	}
	
	public synchronized boolean setGroomedDogs(List<Dog> groomedDogs){
		this.groomedDogs = groomedDogs;
		return true;
	}
	
	public synchronized List<Dog> getNotGroomedDogs(){
		return this.notGroomedDogs;
	}
	
	public synchronized boolean setNotGroomedDogs(List<Dog> notGroomedDogs){
		this.notGroomedDogs = notGroomedDogs;
		return true;
	}
	
	public synchronized List<Dog> getGuestsDogs(){
		return this.guestsDogs;
	}
	
	public synchronized void setGuestsDogs(List<Dog> guestsDogs){
		this.guestsDogs = guestsDogs;
    }
    
    // check if the room is fully occupied
    public synchronized boolean isOccupied(){
        if(this.CAPACITY == this.occupancy){
            return true;
        }else{
            return false;
        }
    }

}