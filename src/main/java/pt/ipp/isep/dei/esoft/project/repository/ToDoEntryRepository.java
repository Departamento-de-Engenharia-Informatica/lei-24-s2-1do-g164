package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.ToDoEntry;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatusENUM;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type To do entry repository.
 */
public class ToDoEntryRepository implements Serializable {
    private ArrayList<ToDoEntry> toDoEntryList = new ArrayList<>();

    /**
     * Register to do entry boolean.
     *
     * @param td the td
     * @return the boolean
     */
    public boolean registerToDoEntry(ToDoEntry td) {
        System.out.println(td);
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

    /**
     * Update status boolean.
     *
     * @param toDoEntry the to do entry
     * @param status    the status
     * @return the boolean
     */
    public boolean updateStatus(ToDoEntry toDoEntry, EntryStatusENUM status) {
        for (ToDoEntry entry : toDoEntryList) {
            if (entry.equals(toDoEntry)) {
                entry.setEntryStatus(status);
                return true;
            }
        }
        return false;
    }

    /**
     * Gets to do entry list.
     *
     * @param email the email
     * @return the to do entry list
     */
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

    /**
     * Gets to do entry list by status.
     *
     * @param email  the email
     * @param status the status
     * @return the to do entry list by status
     */
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

    /**
     * Assign vehicles boolean.
     *
     * @param toDoEntry    the to do entry
     * @param vehiclesList the vehicles list
     * @return the boolean
     */
    public boolean assignVehicles(ToDoEntry toDoEntry, ArrayList<Vehicle> vehiclesList) {
        if (toDoEntry == null || vehiclesList == null) {
            return false;
        }

        return toDoEntry.addVehicles(vehiclesList);
    }
}
