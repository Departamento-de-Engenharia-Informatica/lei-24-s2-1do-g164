package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.EntryStatus;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceType;

public class ToDoEntry {
    private String description;
    private int expectedDuration;
    private EntryStatus entryStatus;
    private GreenSpace greenSpace;

    public ToDoEntry(String description, int expectedDuration, EntryStatus entryStatus, GreenSpace greenSpace){
        this.description = description;
        this.expectedDuration = expectedDuration;
        this.entryStatus = entryStatus;
        this.greenSpace = greenSpace;
    }
    public boolean equals(ToDoEntry td1) {
        return this.description.equals(td1.getDescription());
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
}
