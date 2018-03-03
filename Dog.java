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

public class Dog {

    private String name;
    private String size;
    private int duration;

    // intialises Dog from each line in the csv
    public Dog(String line) {
        String[] temp = line.split(",");
        this.name = temp[0];
        this.size = temp[1];
        this.duration = Integer.parseInt(temp[2]);
    }

    // intialises Dog with size, name and duration
    public Dog(String name, String size, int duration) {
        this.name = name;
        this.size = size;
        this.duration = duration;
    }

    // get Dog name
    public String getName() {
        return name;
    }

    // set dog name
    public void setName(String name) {
        this.name = name;
    }

    // get dog size
    public String getSize() {
        return size;
    }

    // set dog size
    public void setSize(String size) {
        this.size = size;
    }

    // get duration of stay
    public int getDuration() {
        return duration;
    }

    // set duration of stay
    public void setDuration(int duration) {
        this.duration = duration;
    }

}
