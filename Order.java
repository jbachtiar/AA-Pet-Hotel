/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 *
 * @author Jeremy Bachtiar
 */
public class Order {
    private String name;
    private String size;
    private int duration;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Order(String name, String size, int duration) {
        this.name = name;
        this.size = size;
        this.duration = duration;
    }

    public Order(String line) {
        String[] temp = line.split(",");
        
        this.name = temp[0];
        this.size = temp[1];
        this.duration = Integer.parseInt(temp[2]);
      }
    
}
