/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hui Min
 */
public class LogbookEntry {
    private Dog dog;
    private int roomID;
    private int startDay;
    private int endDay;

    public LogbookEntry(Dog dog, int roomID, int startDay, int endDay) {
        this.dog = dog;
        this.roomID = roomID;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public int getEndDay() {
        return endDay;
    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }
    
    
}
