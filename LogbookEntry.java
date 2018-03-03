/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hui Min
 */
package objects;

public class LogbookEntry {
    private Dog dog;
    private int roomID;
    private int startDay;
    private int endDay;

	//initializing the LogbookEntry with values of Dog, roomID. start day and end day
    public LogbookEntry(Dog dog, int roomID, int startDay, int endDay) {
        this.dog = dog;
        this.roomID = roomID;
        this.startDay = startDay;
        this.endDay = endDay;
    }

	//get the Dog object
    public Dog getDog() {
        return dog;
    }

	//set the Dog object
    public void setDog(Dog dog) {
        this.dog = dog;
    }

	//get the room ID
    public int getRoomID() {
        return roomID;
    }

	//set the room ID
    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

	//get the start day
    public int getStartDay() {
        return startDay;
    }

	//set the start day
    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

	//get the end day
    public int getEndDay() {
        return endDay;
    }

	//set the end day
    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }
        
}
