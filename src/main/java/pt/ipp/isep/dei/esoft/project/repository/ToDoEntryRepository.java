package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.ToDoEntry;

import java.util.ArrayList;

public class ToDoEntryRepository {
    private ArrayList<ToDoEntry> toDoEntryList= new ArrayList<>();

    public boolean registerToDoEntry(String name, String description, int expectedDuration, EntryStatus entryStatus, GreenSpace greenSpace){
        ToDoEntry td = new ToDoEntry(name, description, expectedDuration, entryStatus, greenSpace);
        if(toDoEntryIsUnique(td)){
            toDoEntryList.add(td);
            return true;
        }
        return false;
    }

    private boolean toDoEntryIsUnique(ToDoEntry td1) {
        for (ToDoEntry td : toDoEntryList) {
            if (td.equals(td1)) {
                return false;
            }
        }
        return true;
    }
}
