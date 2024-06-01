package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatusENUM;
import pt.ipp.isep.dei.esoft.project.repository.enums.UrgencyDegreeENUM;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type To do entry.
 */
public class ToDoEntry implements Serializable {

    private String description;
    private int expectedDuration;
    private EntryStatusENUM entryStatus;
    private GreenSpace greenSpace;
    private UrgencyDegreeENUM urgencyDegree;
    private List<Vehicle> assignedVehicles;

    /**
     * Instantiates a new To do entry.
     *
     * @param description      the description
     * @param expectedDuration the expected duration
     * @param greenSpace       the green space
     * @param urgencyDegree    the urgency degree
     */
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


    /**
     * Equals boolean.
     *
     * @param obj the obj
     * @return the boolean
     */
    public boolean equals(ToDoEntry obj) {
        return description.equals(obj.getDescription()) && greenSpace.equals(obj.getGreenSpace());
    }

    @Override
    public int hashCode() {
        return description.hashCode() + greenSpace.hashCode();
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets expected duration.
     *
     * @return the expected duration
     */
    public int getExpectedDuration() {
        return expectedDuration;
    }

    /**
     * Sets expected duration.
     *
     * @param expectedDuration the expected duration
     */
    public void setExpectedDuration(int expectedDuration) {
        this.expectedDuration = expectedDuration;
    }

    /**
     * Gets entry status.
     *
     * @return the entry status
     */
    public EntryStatusENUM getEntryStatus() {
        return entryStatus;
    }

    /**
     * Sets entry status.
     *
     * @param entryStatus the entry status
     */
    public void setEntryStatus(EntryStatusENUM entryStatus) {
        this.entryStatus = entryStatus;
    }

    /**
     * Gets green space.
     *
     * @return the green space
     */
    public GreenSpace getGreenSpace() {
        return greenSpace;
    }

    /**
     * Sets green space.
     *
     * @param greenSpace the green space
     */
    public void setGreenSpace(GreenSpace greenSpace) {
        this.greenSpace = greenSpace;
    }

    /**
     * Gets urgency degree.
     *
     * @return the urgency degree
     */
    public UrgencyDegreeENUM getUrgencyDegree() {
        return urgencyDegree;
    }

    /**
     * Sets urgency degree.
     *
     * @param urgencyDegree the urgency degree
     */
    public void setUrgencyDegree(UrgencyDegreeENUM urgencyDegree) {
        this.urgencyDegree = urgencyDegree;
    }

    /**
     * Add vehicles boolean.
     *
     * @param vehicles the vehicles
     * @return the boolean
     */
    public boolean addVehicles(List<Vehicle> vehicles) {
        if (vehicles == null) {
            return false;
        }
        return assignedVehicles.addAll(vehicles);
    }

    /**
     * Gets assigned vehicles.
     *
     * @return the assigned vehicles
     */
    public List<Vehicle> getAssignedVehicles() {
        return assignedVehicles;
    }
}
