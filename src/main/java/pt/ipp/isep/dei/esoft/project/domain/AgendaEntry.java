package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatus;
import pt.ipp.isep.dei.esoft.project.repository.enums.UrgencyDegree;

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

    public AgendaEntry(String description, int expectedDuration, GreenSpace greenSpace, UrgencyDegree urgencyDegree, EntryStatus entryStatus, LocalDate date, Team team, ArrayList<Vehicle> vehicles) {
        super(description, expectedDuration, greenSpace, urgencyDegree);
        this.associatedTeam = team;
        this.associatedVehicles = vehicles;
        this.setEntryStatus(EntryStatus.PLANNED);
        this.date = date;
    }

    public void cancelEntry() {
        this.setEntryStatus(EntryStatus.CANCELLED);
    }
}
