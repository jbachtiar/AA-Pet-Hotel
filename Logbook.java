package dao;
import objects.*;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hui Min
 */
public class Logbook {
    private ArrayList<LogbookEntry> entries;

    public Logbook(ArrayList<LogbookEntry> entries) {
        this.entries = entries;
    }

    public ArrayList<LogbookEntry> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<LogbookEntry> entries) {
        this.entries = entries;
    }
    
    public boolean deleteEntry(Dog dog){
        for(LogbookEntry e: entries){
            if(e.getDog().getName().equals(dog.getName())){
                entries.remove(e);
                return true;
            }
        }
        return false;
    }
    
    public void addEntry(LogbookEntry e){
        entries.add(e);
    }
}
