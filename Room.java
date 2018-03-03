package objects;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class Room{
    // Set room, food, water and shampoo capacity
    private final int CAPACITY = 10;
    private final int FOOD = 100;
    private final int WATER = 100;
    private final int SHAMPOO = 100;
    
    private int id;
    private AtomicInteger occupancy;

	private AtomicInteger availFood;
    private AtomicInteger availWater;
    private AtomicInteger availShampoo;
	
	
/*     private int availFood;
    private int availWater;
    private int availShampoo; */

    private List<Dog> guestsDogs;
    private List<Dog> groomedDogs;
	private List<Dog> notGroomedDogs;
    
    // intialises empty room and full supply
    public Room(int id){
        this.id = id;
        this.occupancy = new AtomicInteger(0);
        this.availFood = new AtomicInteger(this.FOOD);
        this.availWater = new AtomicInteger(this.WATER);
        this.availShampoo = new AtomicInteger(this.SHAMPOO);
        this.groomedDogs = Collections.synchronizedList(new ArrayList<Dog>());
        this.notGroomedDogs = Collections.synchronizedList(new ArrayList<Dog>());
		this.guestsDogs = Collections.synchronizedList(new ArrayList<Dog>());
    }
    

    // get the room ID
    public int getId(){
        return this.id;
    }

    public void resetGroomedDogs(){
        this.groomedDogs = Collections.synchronizedList(new ArrayList<Dog>());
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
        this.availShampoo.set(this.SHAMPOO);
    }
    public void restoreFood(){
        this.availFood.set(this.FOOD);
    }
    public void restoreWater(){
        this.availWater.set(this.WATER);
    }

    // Decrease the available food, water and shampoo by 1  
    public void decreaseFood(){
        this.availFood.getAndDecrement();
    }
    public void decreaseWater(){
        this.availWater.getAndDecrement();
    }
    public void decreaseShampoo(){
        this.availShampoo.getAndDecrement();
    }
	
    // Get available food, water and shampoo
    public int getAvailableFood(){
        return this.availFood.get();
    }
    public int getAvailableWater(){
        return this.availWater.get();
    }
    public int getAvailableShampoo(){
        return this.availShampoo.get();
    }

    // increase room occupancy by 1
    public void increaseOccupancy(){
        this.occupancy.getAndIncrement();
    }
    // decerase room occupancy by 1
    public void decreaseOccupancy(){
        this.occupancy.getAndDecrement();
    }
    // get room occupancy
    public int getOccupancy(){
        return this.occupancy.get();
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
        if(this.CAPACITY == this.occupancy.get()){
            return true;
        }else{
            return false;
        }
    }

}