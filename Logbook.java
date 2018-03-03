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

    }

    // get all the entries in the logbook
    public List<LogbookEntry> getEntries() {
        return entries;
    }

    // set entries
    public void setEntries(ArrayList<LogbookEntry> entries) {
        this.entries = entries;
    }
    
    // add new entry to the logbook
    public synchronized static void addEntry(LogbookEntry e){
        entries.add(e);
    }
}
