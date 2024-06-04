package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.mappers.TeamMapper;
import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatusENUM;
import pt.ipp.isep.dei.esoft.project.repository.enums.TeamStatusENUM;
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
    public final ArrayList<Collaborator> teamVazia = new ArrayList<>();
    public final ArrayList<Skill> skillsVazias = new ArrayList<>();
    public final Team teamOmissao = new Team(teamVazia, skillsVazias, TeamStatusENUM.ACCEPTED );
    public final ArrayList<Vehicle> vehiclesOmissao = new ArrayList<>();
    private TeamMapper teamMapper = new TeamMapper();

    public AgendaEntryDTO(ToDoEntryDTO toDoEntryDTO, LocalDate date){
        this.description = toDoEntryDTO.description;
        this.expectedDuration = toDoEntryDTO.expectedDuration;
        this.greenSpace = toDoEntryDTO.greenSpace;
        this.urgencyDegree = toDoEntryDTO.urgencyDegree;
        this.date = date;
        this.team = teamOmissao;
        this.vehicles = vehiclesOmissao;
        this.entryStatus = toDoEntryDTO.entryStatus;
    }

    public AgendaEntryDTO(String description, int expectedDuration, GreenSpace greenSpace, UrgencyDegreeENUM urgencyDegree, EntryStatusENUM entryStatus, LocalDate date, Team team, ArrayList<Vehicle> vehicles) {
        this.description = description;
        this.expectedDuration = expectedDuration;
        this.greenSpace = greenSpace;
        this.urgencyDegree = urgencyDegree;
        this.date = date;
        this.team = team;
        this.vehicles = vehicles;
        this.entryStatus = entryStatus;
    }

    public AgendaEntryDTO(String description, GreenSpace greenSpace, EntryStatusENUM entryStatus){
        this.description = description;
        this.greenSpace = greenSpace;
        this.entryStatus = entryStatus;
    }

    public AgendaEntryDTO(String description, GreenSpace greenSpace, Team team){
        this.description = description;
        this.greenSpace = greenSpace;
        this.team = team;
    }

    public String getDescription() {
        return description;
    }

    public int getExpectedDuration() {
        return expectedDuration;
    }

    public GreenSpace getGreenSpace() {
        return greenSpace;
    }

    public UrgencyDegreeENUM getUrgencyDegree() {
        return urgencyDegree;
    }

    public EntryStatusENUM getEntryStatus() {
        return entryStatus;
    }

    public LocalDate getDate() {
        return date;
    }

    public Team getTeam() {
        return team;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public String toString() {
        String vehiclesString = "";
        for (Vehicle vehicle : vehicles){
            vehiclesString = vehiclesString + vehicle.getVehicleID() + " " + vehicle.getModel() + " (" + vehicle.getBrand() + "), ";
            vehiclesString = removeLastCharacter(vehiclesString);
        }
        if (team.getCollaborators().isEmpty() && vehicles.isEmpty()){
            return  description + " - " +
                    "Expected Duration: " + expectedDuration + " - " +
                    "Status: " + entryStatus + " - " +
                    "Green Space: " + greenSpace.getName() + " - " +
                    "Urgency Degree: " + urgencyDegree + " - " +
                    "Date: " + date.toString() + " - " +
                    "Team: No Team" + " - " +
                    "Vehicles: No Vehicles";
        }
        if(team.getCollaborators().isEmpty()){
            return  description + " - " +
                    "Expected Duration: " + expectedDuration + " - " +
                    "Status: " + entryStatus + " - " +
                    "Green Space: " + greenSpace.getName() + " - " +
                    "Urgency Degree: " + urgencyDegree + " - " +
                    "Date: " + date.toString() + " - " +
                    "Team: No Team" + " - " +
                    "Vehicles: " + vehiclesString;
        }
        System.out.println(vehiclesString);
        if(vehicles.isEmpty()){
            return  description + " - " +
                    "Expected Duration: " + expectedDuration + " - " +
                    "Status: " + entryStatus + " - " +
                    "Green Space: " + greenSpace.getName() + " - " +
                    "Urgency Degree: " + urgencyDegree + " - " +
                    "Date: " + date.toString() + " - " +
                    "Team: " + teamMapper.toDto(team).toString() + " - " +
                    "Vehicles: No Vehicles";
        }
        return  description + " - " +
                "Expected Duration: " + expectedDuration + " - " +
                "Status: " + entryStatus + " - " +
                "Green Space: " + greenSpace.getName() + " - " +
                "Urgency Degree: " + urgencyDegree + " - " +
                "Date: " + date.toString() + " - " +
                "Team: " + teamMapper.toDto(team).toString() + " - " +
                "Vehicles: " + vehiclesString;

    }

    private String removeLastCharacter(String str) {
        if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == 'x') {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }
}
