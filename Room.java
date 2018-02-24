public class Room{
    private final int CAPACITY = 10;
    private final int FOOD = 10;
    private final int WATER = 10;
    private final int SHAMPOO = 10;
    
    private int id;
    private int occupancy;

    private int availFood;
    private int availWater;
    private int availShampoo;
    

    public Room(int id){
        this.id = id;
        this.occupancy = 0;
        this.availFood = this.FOOD;
        this.availWater = this.WATER;
        this.availShampoo = this.SHAMPOO;
    }

    public void restoreShampoo(){
        this.availShampoo = this.SHAMPOO;
    }
    public void restoreFood(){
        this.availFood = this.FOOD;
    }
    public void restoreWater(){
        this.availWater = this.WATER;
    }

    public void decreaseFood(){
        this.availFood--;
    }
    public void decreaseWater(){
        this.availWater--;
    }
    public void decreaseShampoo(){
        this.availShampoo--;
    }

    public void increaseOccupancy(){
        this.occupancy++;
    }

    public void decreaseOccupancy(){
        this.occupancy++;
    }

    public int getOccupancy(){
        return this.occupancy;
    }
}