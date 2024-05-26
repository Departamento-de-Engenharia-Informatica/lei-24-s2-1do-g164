package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatusENUM;
import pt.ipp.isep.dei.esoft.project.repository.enums.UrgencyDegreeENUM;

import java.util.ArrayList;
import java.util.List;

public class ToDoEntry {

    private String description;
    private int expectedDuration;
    private EntryStatusENUM entryStatus;
    private GreenSpace greenSpace;
    private UrgencyDegreeENUM urgencyDegree;
    private List<Vehicle> assignedVehicles;

    public ToDoEntry(String description, int expectedDuration, GreenSpace greenSpace, UrgencyDegreeENUM urgencyDegree) {
        this.description = description;
        this.expectedDuration = expectedDuration;
        this.entryStatus = EntryStatusENUM.PENDING;
        this.urgencyDegree = urgencyDegree;
        this.greenSpace = greenSpace;
        this.assignedVehicles = new ArrayList<>();
    }

    @Override
    public String toString() {
        return description + " - " +
                "Expected Duration: " + expectedDuration + " - " +
                "Status: " + entryStatus + " - " +
                "Green Space: " + greenSpace.getName() + " - " +
                "Urgency Degree: " + urgencyDegree;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ToDoEntry toDoEntry = (ToDoEntry) obj;
        return description.equals(toDoEntry.getDescription()) && greenSpace.equals(toDoEntry.getGreenSpace());
    }

    @Override
    public int hashCode() {
        return description.hashCode() + greenSpace.hashCode();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExpectedDuration() {
        return expectedDuration;
    }

    public void setExpectedDuration(int expectedDuration) {
        this.expectedDuration = expectedDuration;
    }

    public EntryStatusENUM getEntryStatus() {
        return entryStatus;
    }

    public void setEntryStatus(EntryStatusENUM entryStatus) {
        this.entryStatus = entryStatus;
    }

    public GreenSpace getGreenSpace() {
        return greenSpace;
    }

    public void setGreenSpace(GreenSpace greenSpace) {
        this.greenSpace = greenSpace;
    }

    public UrgencyDegreeENUM getUrgencyDegree() {
        return urgencyDegree;
    }

    public void setUrgencyDegree(UrgencyDegreeENUM urgencyDegree) {
        this.urgencyDegree = urgencyDegree;
    }

    public boolean addVehicles(List<Vehicle> vehicles) {
        if (vehicles == null) {
            return false;
        }
        return assignedVehicles.addAll(vehicles);
    }

    public List<Vehicle> getAssignedVehicles() {
        return assignedVehicles;
    }
}
