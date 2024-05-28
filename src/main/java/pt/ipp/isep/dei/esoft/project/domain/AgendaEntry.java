package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatusENUM;
import pt.ipp.isep.dei.esoft.project.repository.enums.UrgencyDegreeENUM;

import java.time.LocalDate;
import java.util.ArrayList;


public class AgendaEntry extends ToDoEntry {
    private LocalDate date;
    private Team associatedTeam;
    private ArrayList<Vehicle> associatedVehicles;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Team getAssociatedTeam() {
        return associatedTeam;
    }

    public void setAssociatedTeam(Team associatedTeam) {
        this.associatedTeam = associatedTeam;
    }

    public ArrayList<Vehicle> getAssociatedVehicles() {
        return associatedVehicles;
    }

    public void setAssociatedVehicles(ArrayList<Vehicle> associatedVehicles) {
        this.associatedVehicles = associatedVehicles;
    }

    public AgendaEntry(String description, int expectedDuration, GreenSpace greenSpace, UrgencyDegreeENUM urgencyDegree, EntryStatusENUM entryStatus, LocalDate date, Team team, ArrayList<Vehicle> vehicles) {
        super(description, expectedDuration, greenSpace, urgencyDegree);
        this.associatedTeam = team;
        this.associatedVehicles = vehicles;
        this.setEntryStatus(EntryStatusENUM.PENDING);
        this.date = date;
    }

    public AgendaEntry(String description, int expectedDuration, GreenSpace greenSpace, UrgencyDegreeENUM urgencyDegree, EntryStatusENUM entryStatus, LocalDate date,  ArrayList<Vehicle> vehicles) {
        super(description, expectedDuration, greenSpace, urgencyDegree);
        this.associatedVehicles = vehicles;
        this.setEntryStatus(EntryStatusENUM.PENDING);
        this.date = date;
    }


    public void cancelEntry() {
        this.setEntryStatus(EntryStatusENUM.CANCELLED);
    }
}
