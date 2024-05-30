package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.ToDoEntry;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatusENUM;

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

    public boolean updateStatus(ToDoEntry toDoEntry, EntryStatusENUM status) {
        for (ToDoEntry entry : toDoEntryList) {
            if (entry.equals(toDoEntry)) {
                entry.setEntryStatus(status);
                return true;
            }
        }
        return false;
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

    public ArrayList<ToDoEntry> getToDoEntryListByStatus(String email, EntryStatusENUM status) {
        System.out.println(toDoEntryList);
        ArrayList<ToDoEntry> toDoEntryListGSM = new ArrayList<>();
        for (ToDoEntry td : this.toDoEntryList) {
            if (td.getGreenSpace().getEmailGSM().equals(email)) {
                if (!td.getEntryStatus().equals(status)) {
                    toDoEntryListGSM.add(td);
                }
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
