package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.ToDoEntry;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.io.Serializable;
import java.util.ArrayList;

public class ToDoEntryRepository implements Serializable {
    private ArrayList<ToDoEntry> toDoEntryList = new ArrayList<>();

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

    public ArrayList<ToDoEntry> getToDoEntryList(String email) {
        System.out.println(toDoEntryList);
        ArrayList<ToDoEntry> toDoEntryListGSM = new ArrayList<>();
        for (ToDoEntry td : this.toDoEntryList) {
            if (td.getGreenSpace().getEmailGSM().equals(email)) {
                toDoEntryListGSM.add(td);
            }
        }
        return toDoEntryListGSM;
    }

    public boolean assignVehicles(ToDoEntry toDoEntry, ArrayList<Vehicle> vehiclesList) {
        if (toDoEntry == null || vehiclesList == null) {
            return false;
        }

        return toDoEntry.addVehicles(vehiclesList);
    }
}
