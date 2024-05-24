package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatus;
import pt.ipp.isep.dei.esoft.project.repository.enums.UrgencyDegree;

import java.time.LocalDate;
import java.util.ArrayList;

public class AgendaDescriptionAndGreenspaceDTO {
    public String description;
    public GreenSpace greenSpace;
    public EntryStatus status;

    public AgendaDescriptionAndGreenspaceDTO(String description, GreenSpace greenSpace, EntryStatus status){
        this.description = description;
        this.greenSpace = greenSpace;
        this.status = status;
    }
}
