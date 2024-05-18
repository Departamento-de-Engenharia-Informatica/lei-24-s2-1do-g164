package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.EntryStatus;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceType;

public class ToDoEntry {
    private String name;
    private String description;
    private int expectedDuration;
    private EntryStatus entryStatus;
    private GreenSpace greenSpace;

    public ToDoEntry(String name, String description, int expectedDuration, EntryStatus entryStatus, GreenSpace greenSpace){
        this.name = name;
        this.description = description;
        this.expectedDuration = expectedDuration;
        this.entryStatus = entryStatus;
        this.greenSpace = greenSpace;
    }
    public boolean equals(ToDoEntry td1) {
        return this.name.equals(td1.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
