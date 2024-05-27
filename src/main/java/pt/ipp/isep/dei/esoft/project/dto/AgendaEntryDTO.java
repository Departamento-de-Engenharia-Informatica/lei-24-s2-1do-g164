package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatusENUM;
import pt.ipp.isep.dei.esoft.project.repository.enums.UrgencyDegreeENUM;

import java.time.LocalDate;
import java.util.ArrayList;

public class AgendaEntryDTO {

    public String description;
    public int expectedDuration;
    public GreenSpace greenSpace;
    public UrgencyDegreeENUM urgencyDegree;
    public EntryStatusENUM entryStatus;
    public LocalDate date;

    public Team team;

    public ArrayList<Vehicle> vehicles;


    public AgendaEntryDTO(ToDoEntryDTO toDoEntryDTO, LocalDate date){
        this.description = toDoEntryDTO.description;
        this.expectedDuration = toDoEntryDTO.expectedDuration;
        this.greenSpace = toDoEntryDTO.greenSpace;
        this.urgencyDegree = toDoEntryDTO.urgencyDegree;
        this.date = date;
        this.entryStatus = EntryStatusENUM.PLANNED;
    }

    public AgendaEntryDTO(String description, int expectedDuration, GreenSpace greenSpace, UrgencyDegreeENUM urgencyDegree, EntryStatusENUM entryStatus, LocalDate date, Team team, ArrayList<Vehicle> vehicles) {
        this.description = description;
        this.expectedDuration = expectedDuration;
        this.greenSpace = greenSpace;
        this.urgencyDegree = urgencyDegree;
        this.date = date;
        this.team = team;
        this.vehicles = vehicles;
        this.entryStatus = EntryStatusENUM.PLANNED;
    }

    public AgendaEntryDTO(String description, GreenSpace greenSpace, EntryStatusENUM entryStatus){
        this.description = description;
        this.greenSpace = greenSpace;
        this.entryStatus = entryStatus;
    }
    public AgendaEntryDTO(String description, GreenSpace greenSpace, Team team){
        this.description=description;
        this.greenSpace= greenSpace;
        this.team=team;
    }


}
