package pt.ipp.isep.dei.esoft.project.repository;


import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;

import java.util.ArrayList;

public class AgendaEntryRepository {

    private final ArrayList<AgendaEntry> agendaEntryList = new ArrayList<>();

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

    public ArrayList<AgendaEntry> getAgendaEntryList() {
        return agendaEntryList;
    }

    public boolean postponeEntryInAgenda(AgendaEntry entity) {
        return true;
    }

    public void updateStatus(AgendaEntry updatedEntry) {
        for (AgendaEntry entry : agendaEntryList) {
            if (entry.getDescription().equals(updatedEntry.getDescription())) {
                entry.setEntryStatus(updatedEntry.getEntryStatus());
            }
        }
    }

    public void updateTeam(AgendaEntry updatedEntry) {
        for (AgendaEntry entry : agendaEntryList) {
            if (entry.getDescription().equals(updatedEntry.getDescription())) {
                entry.setAssociatedTeam(updatedEntry.getAssociatedTeam());
            }
        }
    }
}