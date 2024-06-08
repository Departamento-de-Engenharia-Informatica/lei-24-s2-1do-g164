package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatusENUM;
import pt.ipp.isep.dei.esoft.project.repository.enums.UrgencyDegreeENUM;

import java.time.LocalDate;
import java.util.ArrayList;


/**
 * The type Agenda entry.
 */
public class AgendaEntry extends ToDoEntry {
    private LocalDate date;
    private Team associatedTeam;
    private ArrayList<Vehicle> associatedVehicles;

    /**
     * Gets date.
     *
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets associated team.
     *
     * @return the associated team
     */
    public Team getAssociatedTeam() {
        return associatedTeam;
    }

    /**
     * Sets associated team.
     *
     * @param associatedTeam the associated team
     */
    public void setAssociatedTeam(Team associatedTeam) {
        this.associatedTeam = associatedTeam;
    }

    /**
     * Gets associated vehicles.
     *
     * @return the associated vehicles
     */
    public ArrayList<Vehicle> getAssociatedVehicles() {
        return associatedVehicles;
    }

    /**
     * Sets associated vehicles.
     *
     * @param associatedVehicles the associated vehicles
     */
    public void setAssociatedVehicles(ArrayList<Vehicle> associatedVehicles) {
        this.associatedVehicles = associatedVehicles;
    }

    /**
     * Instantiates a new Agenda entry.
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
    public AgendaEntry(String description, int expectedDuration, GreenSpace greenSpace, UrgencyDegreeENUM urgencyDegree, EntryStatusENUM entryStatus, LocalDate date, Team team, ArrayList<Vehicle> vehicles) {
        super(description, expectedDuration, greenSpace, urgencyDegree);
        this.associatedTeam = team;
        this.associatedVehicles = vehicles;
        this.setEntryStatus(entryStatus);
        this.date = date;
    }

    /**
     * Instantiates a new Agenda entry.
     *
     * @param description      the description
     * @param expectedDuration the expected duration
     * @param greenSpace       the green space
     * @param urgencyDegree    the urgency degree
     * @param entryStatus      the entry status
     * @param date             the date
     * @param vehicles         the vehicles
     */
    public AgendaEntry(String description, int expectedDuration, GreenSpace greenSpace, UrgencyDegreeENUM urgencyDegree, EntryStatusENUM entryStatus, LocalDate date,  ArrayList<Vehicle> vehicles) {
        super(description, expectedDuration, greenSpace, urgencyDegree);
        this.associatedVehicles = vehicles;
        this.setEntryStatus(entryStatus);
        this.date = date;
    }
}