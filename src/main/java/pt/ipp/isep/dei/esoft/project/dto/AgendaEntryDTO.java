package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.ENUM.EntryStatus;
import pt.ipp.isep.dei.esoft.project.repository.ENUM.UrgencyDegree;

import java.time.LocalDate;
import java.util.ArrayList;

public class AgendaEntryDTO {

    public String description;
    public int expectedDuration;
    public GreenSpace greenSpace;
    public UrgencyDegree urgencyDegree;
    public EntryStatus entryStatus;

    public LocalDate date;

    public Team team;

    public ArrayList<Vehicle> vehicles;


    public AgendaEntryDTO(ToDoEntryDTO toDoEntryDTO, LocalDate date){
        this.description = toDoEntryDTO.description;
        this.expectedDuration = toDoEntryDTO.expectedDuration;
        this.greenSpace = toDoEntryDTO.greenSpace;
        this.urgencyDegree = toDoEntryDTO.urgencyDegree;
        this.date = date;
        this.entryStatus = EntryStatus.PLANNED;
    }

    public AgendaEntryDTO(String description, int expectedDuration, GreenSpace greenSpace, UrgencyDegree urgencyDegree, EntryStatus entryStatus, LocalDate date, Team team, ArrayList<Vehicle> vehicles) {
        this.description = description;
        this.expectedDuration = expectedDuration;
        this.greenSpace = greenSpace;
        this.urgencyDegree = urgencyDegree;
        this.date = date;
        this.team = team;
        this.vehicles = vehicles;
        this.entryStatus = EntryStatus.PLANNED;
    }
}
