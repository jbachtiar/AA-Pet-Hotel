package objects;
import java.util.*;
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

    private ArrayList<Dog> guestsDogs;
    private ArrayList<Dog> groomedDogs;
	private ArrayList<Dog> notGroomedDogs;
    
    // intialises empty room and full supply
    public Room(int id){
        this.id = id;
        this.occupancy = 0;
        this.availFood = this.FOOD;
        this.availWater = this.WATER;
        this.availShampoo = this.SHAMPOO;
        this.groomedDogs = new ArrayList<Dog>();
        this.notGroomedDogs = new ArrayList<Dog>();
		this.guestsDogs = new ArrayList<Dog>();
    }
    

    // get the room ID
    public int getId(){
        return this.id;
    }

    public void addGroomedDogs(Dog dog){
        this.groomedDogs.add(dog);
    }
    public void addGuestsDogs(Dog dog){
        this.guestsDogs.add(dog);
    }
    public void addNotGroomedDogs(Dog dog){
        this.notGroomedDogs.add(dog);
    }

    public void removeGroomedDogs(Dog dog){
        this.groomedDogs.remove(dog);
    }
    public void removeGuestsDogs(Dog dog){
        this.guestsDogs.remove(dog);
    }
    public void removeNotGroomedDogs(Dog dog){
        this.notGroomedDogs.remove(dog);
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
    public void decreaseFood(){
        this.availFood--;
    }
    public void decreaseWater(){
        this.availWater--;
    }
    public void decreaseShampoo(){
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
    public void increaseOccupancy(){
        this.occupancy++;
    }
    // decerase room occupancy by 1
    public void decreaseOccupancy(){
        this.occupancy--;
    }
    // get room occupancy
    public int getOccupancy(){
        return this.occupancy;
    }
	
	public ArrayList<Dog> getGroomedDogs(){
		return this.groomedDogs;
	}
	
	public void setGroomedDogs(ArrayList<Dog> groomedDogs){
		this.groomedDogs = groomedDogs;
	}
	
	public ArrayList<Dog> getNotGroomedDogs(){
		return this.notGroomedDogs;
	}
	
	public void setNotGroomedDogs(ArrayList<Dog> notGroomedDogs){
		this.notGroomedDogs = notGroomedDogs;
	}
	
	public ArrayList<Dog> getGuestsDogs(){
		return this.guestsDogs;
	}
	
	public void setGuestsDogs(ArrayList<Dog> guestsDogs){
		this.guestsDogs = guestsDogs;
    }
    
    // check if the room is fully occupied
    public boolean isOccupied(){
        if(this.CAPACITY == this.occupancy){
            return true;
        }else{
            return false;
        }
    }
}