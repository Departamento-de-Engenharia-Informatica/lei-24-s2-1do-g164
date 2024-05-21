package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.ENUM.UrgencyDegree;

public class RegisterToDoEntryDTO {

    public String description;
    public int expectedDuration;
    public GreenSpace greenSpace;
    public UrgencyDegree urgencyDegree;

    public RegisterToDoEntryDTO(String description, int expectedDuration, GreenSpace greenSpace) {
        this.description = description;
        this.expectedDuration = expectedDuration;
        this.greenSpace = greenSpace;
        this.urgencyDegree = urgencyDegree;
    }

}
