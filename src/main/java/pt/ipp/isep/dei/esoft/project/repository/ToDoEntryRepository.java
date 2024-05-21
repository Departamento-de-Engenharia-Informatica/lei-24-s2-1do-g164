package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.ToDoEntry;
import pt.ipp.isep.dei.esoft.project.repository.ENUM.EntryStatus;
import pt.ipp.isep.dei.esoft.project.repository.ENUM.UrgencyDegree;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ToDoEntryRepository {
    private ArrayList<ToDoEntry> toDoEntryList= new ArrayList<>();

    public boolean registerToDoEntry(ToDoEntry td) {
        if(toDoEntryIsUnique(td)){
            System.out.println(toDoEntryList);
            return toDoEntryList.add(td);
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

    public ArrayList<ToDoEntry> getToDoEntryList() {
        return toDoEntryList;
    }
}
