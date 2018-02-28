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

    private List<Dog> guestDogs;
    private List<Dog> groomedDogs;
    
    // intialises empty room and full supply
    public Room(int id){
        this.id = id;
        this.occupancy = 0;
        this.availFood = this.FOOD;
        this.availWater = this.WATER;
        this.availShampoo = this.SHAMPOO;
        this.groomedDogs = new List<Dog>();
        this.guestsDogs = new List<Dog>();
    }
    

    // get the room ID
    public int getId(){
        return this.id;
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
}