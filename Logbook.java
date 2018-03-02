package dao;
import objects.*;
import java.util.ArrayList;
import java.util.Collections;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hui Min
 */
import java.util.*;
import objects.*;

public class Logbook {
    public static List<LogbookEntry> entries  = Collections.synchronizedList(new ArrayList<LogbookEntry>());

    public Logbook() {
        // this.entries = Collections.synchronizedList(new ArrayList<LogbookEntry>());
    }

    public List<LogbookEntry> getEntries() {
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
    
    public synchronized static void addEntry(LogbookEntry e){
        entries.add(e);
    }
    public synchronized static void removeEntry(LogbookEntry e){
        entries.remove(e);
    }
}
