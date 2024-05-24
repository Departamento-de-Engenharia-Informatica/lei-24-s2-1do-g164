package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatus;
import pt.ipp.isep.dei.esoft.project.repository.enums.UrgencyDegree;

public class ToDoEntry {

    private String description;
    private int expectedDuration;
    private EntryStatus entryStatus;
    private GreenSpace greenSpace;
    private UrgencyDegree urgencyDegree;

    public ToDoEntry(String description, int expectedDuration, GreenSpace greenSpace, UrgencyDegree urgencyDegree){
        this.description = description;
        this.expectedDuration = expectedDuration;
        this.entryStatus = EntryStatus.PENDING;
        this.urgencyDegree = urgencyDegree;
        this.greenSpace = greenSpace;

    }

    @Override
    public String toString() {
        return description + " - " +
                "Expected Duration: " + expectedDuration + " - " +
                "Status: " + entryStatus + " - " +
                "Green Space: " + greenSpace.getName() + " - " +
                "Urgency Degree: " + urgencyDegree;
    }

    public boolean equals(ToDoEntry td1) {
        return this.description.equals(td1.getDescription()) && this.greenSpace.equals(td1.getGreenSpace());
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

    public EntryStatus getEntryStatus() {
        return entryStatus;
    }

    public void setEntryStatus(EntryStatus entryStatus) {
        this.entryStatus = entryStatus;
    }

    public GreenSpace getGreenSpace() {
        return greenSpace;
    }

    public void setGreenSpace(GreenSpace greenSpace) {
        this.greenSpace = greenSpace;
    }

    public UrgencyDegree getUrgencyDegree() {
        return urgencyDegree;
    }

    public void setUrgencyDegree(UrgencyDegree urgencyDegree) {
        this.urgencyDegree = urgencyDegree;
    }
}
