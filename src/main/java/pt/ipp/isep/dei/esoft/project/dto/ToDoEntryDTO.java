package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.ENUM.EntryStatus;
import pt.ipp.isep.dei.esoft.project.repository.ENUM.UrgencyDegree;

public class ToDoEntryDTO {
    public String description;
    public int expectedDuration;
    public GreenSpace greenSpace;
    public UrgencyDegree urgencyDegree;
    public EntryStatus entryStatus;

    public ToDoEntryDTO(String description, int expectedDuration, GreenSpace greenSpace, UrgencyDegree urgencyDegree, EntryStatus entryStatus) {
        this.description = description;
        this.expectedDuration = expectedDuration;
        this.greenSpace = greenSpace;
        this.urgencyDegree = urgencyDegree;
        this.entryStatus = entryStatus;
    }
}
