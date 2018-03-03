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

	//resets the GroomedDogsList to an empty List 
    public void resetGroomedDogs(){
        this.groomedDogs = Collections.synchronizedList(new ArrayList<Dog>());
    }
	
	//adds a Dog object into the GroomedDogsList
    public synchronized void addGroomedDogs(Dog dog){
        this.groomedDogs.add(dog);
    }
	
	//adds a Dog object into the GuestsDogsList
    public synchronized void addGuestsDogs(Dog dog){
        this.guestsDogs.add(dog);
    }
	
	//adds a Dog object into the NotGroomedDogsList
    public synchronized void addNotGroomedDogs(Dog dog){
        this.notGroomedDogs.add(dog);
    }

	//removes a Dog object from the GroomedDogsList
    public synchronized void removeGroomedDogs(Dog dog){
        this.groomedDogs.remove(dog);
    }
	
	//removes a Dog object from the GuestsDogsList
    public synchronized void removeGuestsDogs(Dog dog){
        this.guestsDogs.remove(dog);
    }
	
	//removes a Dog object from the NotGroomedDogsList
    public synchronized void removeNotGroomedDogs(Dog dog){
        this.notGroomedDogs.remove(dog);
    }
	
	//get a Dog object from GroomedDogsList
	public synchronized Dog getGroomedDogs(int index){
        return this.groomedDogs.get(index);
    }
	
	//get a Dog object from GuestsDogsList
    public synchronized Dog getGuestsDogs(int index){
        return this.guestsDogs.get(index);
    }
	
	//get a Dog object from NotGroomedDogsList
    public synchronized Dog getNotGroomedDogs(int index){
        return this.notGroomedDogs.get(index);
    }

	//get the size of the GroomedDogsList
	public synchronized int getGroomedDogsSize(){
        return this.groomedDogs.size();
    }
	
	//get the size of the GuestsDogsList
    public synchronized int getGuestsDogsSize(){
        return this.guestsDogs.size();
    }
	
	//get the size of the NotGroomedDogsList
    public synchronized int getNotGroomedDogsSize(){
        return this.notGroomedDogs.size();
    }

    // Restore the shampoo supply in the room
    public void restoreShampoo(){
        this.availShampoo.set(this.SHAMPOO);
    }
	
	// Restore the food supply in the room
    public void restoreFood(){
        this.availFood.set(this.FOOD);
    }
	
	// Restore the water supply in the room
    public void restoreWater(){
        this.availWater.set(this.WATER);
    }

    // Decrease the available food in the room by 1 unit
    public void decreaseFood(){
        this.availFood.getAndDecrement();
    }
	
	// Decrease the available water in the room by 1 unit
    public void decreaseWater(){
        this.availWater.getAndDecrement();
    }
	
	// Decrease the available shampoo in the room by 1 unit
    public void decreaseShampoo(){
        this.availShampoo.getAndDecrement();
    }
	
    // Get available food within the room
    public int getAvailableFood(){
        return this.availFood.get();
    }
	
	// Get available water within the room
    public int getAvailableWater(){
        return this.availWater.get();
    }
	
	// Get available shampoo within the room
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
	
	//retrieve the GroomedDogsList in the room
	public synchronized List<Dog> getGroomedDogs(){
		return this.groomedDogs;
	}
	
	//sets the GroomedDogsList in the room
	public synchronized boolean setGroomedDogs(List<Dog> groomedDogs){
		this.groomedDogs = groomedDogs;
		return true;
	}
	
	//retrieve the NotGroomedDogsList in the room
	public synchronized List<Dog> getNotGroomedDogs(){
		return this.notGroomedDogs;
	}
	
	//set the GroomedDogsList in the room
	public synchronized boolean setNotGroomedDogs(List<Dog> notGroomedDogs){
		this.notGroomedDogs = notGroomedDogs;
		return true;
	}
	
	//retrieve the GuestsDogsList in the room
	public synchronized List<Dog> getGuestsDogs(){
		return this.guestsDogs;
	}
	
	//set the GuestsDogsList in the room
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