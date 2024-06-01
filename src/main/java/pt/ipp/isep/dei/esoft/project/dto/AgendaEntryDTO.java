package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.mappers.TeamMapper;
import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatusENUM;
import pt.ipp.isep.dei.esoft.project.repository.enums.TeamStatusENUM;
import pt.ipp.isep.dei.esoft.project.repository.enums.UrgencyDegreeENUM;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The type Agenda entry dto.
 */
public class AgendaEntryDTO {

    /**
     * The Description.
     */
    public String description;
    /**
     * The Expected duration.
     */
    public int expectedDuration;
    /**
     * The Green space.
     */
    public GreenSpace greenSpace;
    /**
     * The Urgency degree.
     */
    public UrgencyDegreeENUM urgencyDegree;
    /**
     * The Entry status.
     */
    public EntryStatusENUM entryStatus;
    /**
     * The Date.
     */
    public LocalDate date;

    /**
     * The Team.
     */
    public Team team;

    /**
     * The Vehicles.
     */
    public ArrayList<Vehicle> vehicles;

    /**
     * The Team vazia.
     */
    public final ArrayList<Collaborator> teamVazia = new ArrayList<>();
    /**
     * The Skills vazias.
     */
    public final ArrayList<Skill> skillsVazias = new ArrayList<>();
    /**
     * The Team omissao.
     */
    public final Team teamOmissao = new Team(teamVazia, skillsVazias, TeamStatusENUM.ACCEPTED );
    /**
     * The Vehicles omissao.
     */
    public final ArrayList<Vehicle> vehiclesOmissao = new ArrayList<>();
    private TeamMapper teamMapper = new TeamMapper();

    /**
     * Instantiates a new Agenda entry dto.
     *
     * @param toDoEntryDTO the to do entry dto
     * @param date         the date
     */
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

    /**
     * Instantiates a new Agenda entry dto.
     *
     * @param description      the description
     * @param expectedDuration the expected duration
     * @param greenSpace       the green space
     * @param urgencyDegree    the urgency degree
     * @param entryStatus      the entry status
     * @param date             the date
     * @param team             the team
     * @param vehicles         the vehicles
     */
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

    /**
     * Instantiates a new Agenda entry dto.
     *
     * @param description the description
     * @param greenSpace  the green space
     * @param entryStatus the entry status
     */
    public AgendaEntryDTO(String description, GreenSpace greenSpace, EntryStatusENUM entryStatus){
        this.description = description;
        this.greenSpace = greenSpace;
        this.entryStatus = entryStatus;
    }

    /**
     * Instantiates a new Agenda entry dto.
     *
     * @param description the description
     * @param greenSpace  the green space
     * @param team        the team
     */
    public AgendaEntryDTO(String description, GreenSpace greenSpace, Team team){
        this.description=description;
        this.greenSpace= greenSpace;
        this.team= team;
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
     * Gets expected duration.
     *
     * @return the expected duration
     */
    public int getExpectedDuration() {
        return expectedDuration;
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
     * Gets urgency degree.
     *
     * @return the urgency degree
     */
    public UrgencyDegreeENUM getUrgencyDegree() {
        return urgencyDegree;
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
     * Gets date.
     *
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets team.
     *
     * @return the team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Gets vehicles.
     *
     * @return the vehicles
     */
    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public String toString() {
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

        String vehiclesString = "";
        for (Vehicle vehicle : vehicles){
            vehiclesString = vehiclesString + vehicle.getBrand() + " " + vehicle.getModel() + " ";
        }
        System.out.println(vehiclesString);
         return  description + " - " +
                "Expected Duration: " + expectedDuration + " - " +
                "Status: " + entryStatus + " - " +
                "Green Space: " + greenSpace.getName() + " - " +
                "Urgency Degree: " + urgencyDegree + " - " +
                "Date: " + date.toString() + " - " +
                "Team: " + teamMapper.toDto(team).toString() + " - " +
                "Vehicles: " + vehiclesString;

    }
}
