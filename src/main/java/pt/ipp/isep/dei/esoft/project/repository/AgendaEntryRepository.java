package pt.ipp.isep.dei.esoft.project.repository;


import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.domain.ToDoEntry;
import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatusENUM;
import java.io.Serializable;
import java.util.ArrayList;

public class AgendaEntryRepository implements Serializable {

    private final ArrayList<AgendaEntry> agendaEntryList = new ArrayList<>();
    private Team team;

    public boolean addEntryToAgenda(AgendaEntry ag) {
        if (agendaEntryIsUnique(ag)) {
            return agendaEntryList.add(ag);
        }
        return false;
    }

    private boolean agendaEntryIsUnique(AgendaEntry ag1) {
        for (AgendaEntry ag : agendaEntryList) {
            if (ag.equals(ag1)) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<AgendaEntry> getAgendaEntryList(String email) {
        ArrayList<AgendaEntry> agendaEntryListGSM = new ArrayList<>();
        for (AgendaEntry agendaEntry : this.agendaEntryList) {
            if (agendaEntry.getGreenSpace().getEmailGSM().equals(email)) {
                agendaEntryListGSM.add(agendaEntry);
            }
        }
        return agendaEntryListGSM;
    }

    public boolean postponeEntryInAgenda(AgendaEntry entity) {
        for (AgendaEntry ag : agendaEntryList) {
            if (ag.equals(entity)) {
                ag.setEntryStatus(EntryStatusENUM.POSTPONED);
                ag.setDate(entity.getDate());
                return true;
            }
        }
        return false;
    }

    public AgendaEntry getAgendaEntryByDescriptionAndGreenspace(String description, GreenSpace greenSpace) {
        for (AgendaEntry ag : agendaEntryList) {
            if (ag.getDescription().equals(description) && ag.getGreenSpace().equals(greenSpace))
                return ag;
        }
        return null;
    }

    public boolean updateStatus(AgendaEntry updatedEntry, EntryStatusENUM status) {
        for (AgendaEntry entry : agendaEntryList) {
            if (entry.equals(updatedEntry)) {
                entry.setEntryStatus(status);
                return true;
            }
        }
        return false;
    }

    public boolean updateTeam(AgendaEntry agendaEntry, Team team) {

        if (agendaEntry.getAssociatedTeam().equals(team)|| agendaEntry.getAssociatedTeam() != null){
            return false;
        }
            agendaEntry.setAssociatedTeam(team);
            return true;
            }



    public AgendaEntry getAgendaEntry(String description, GreenSpace greenSpace) {
        for (AgendaEntry ag : agendaEntryList) {
            if (ag.getDescription().equals(description) && ag.getGreenSpace().equals(greenSpace))
                return ag;
        }
        return null;
    }
}