package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatusENUM;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class AgendaEntryRepository implements Serializable {

    private final ArrayList<AgendaEntry> agendaEntryList = new ArrayList<>();

    public boolean addEntryToAgenda(AgendaEntry ag) {
        if (agendaEntryIsUnique(ag)) {
            ag.setEntryStatus(EntryStatusENUM.PLANNED);
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
        return this.agendaEntryList;
    }

    public ArrayList<AgendaEntry> getAgendaEntryWithoutDoneList(String email) {
        ArrayList<AgendaEntry> agendaEntryListGSM = new ArrayList<>();
        for (AgendaEntry agendaEntry : this.agendaEntryList) {
            if (agendaEntry.getGreenSpace().getEmailGSM().equals(email) && !agendaEntry.getEntryStatus().equals(EntryStatusENUM.DONE)) {
                agendaEntryListGSM.add(agendaEntry);
            }
        }
        return agendaEntryListGSM;
    }

    public ArrayList<AgendaEntry> getAgendaEntryListWithoutTeam(String email) {
        ArrayList<AgendaEntry> agendaEntryListGSM = new ArrayList<>();
        for (AgendaEntry agendaEntry : this.agendaEntryList) {
            if (agendaEntry.getGreenSpace().getEmailGSM().equals(email) && agendaEntry.getAssociatedTeam().getCollaborators().isEmpty() && (agendaEntry.getEntryStatus().equals(EntryStatusENUM.PLANNED) || agendaEntry.getEntryStatus().equals(EntryStatusENUM.POSTPONED))) {
                agendaEntryListGSM.add(agendaEntry);
            }
        }
        return agendaEntryListGSM;
    }


    public ArrayList<AgendaEntry> getAgendaEntryListWithoutVehicles(String email) {
        ArrayList<AgendaEntry> agendaEntryListGSM = new ArrayList<>();
        for (AgendaEntry agendaEntry : this.agendaEntryList) {
            if (agendaEntry.getGreenSpace().getEmailGSM().equals(email)) {
                agendaEntryListGSM.add(agendaEntry);
            }
        }
        return agendaEntryListGSM;
    }


    public ArrayList<AgendaEntry> getAgendaEntryListWithoutCancelled(String email) {
        ArrayList<AgendaEntry> agendaEntryListGSM = new ArrayList<>();
        for (AgendaEntry agendaEntry : this.agendaEntryList) {
            if (agendaEntry.getGreenSpace().getEmailGSM().equals(email) && !agendaEntry.getEntryStatus().equals(EntryStatusENUM.CANCELLED) && !agendaEntry.getEntryStatus().equals(EntryStatusENUM.PENDING) && !agendaEntry.getEntryStatus().equals(EntryStatusENUM.DONE)) {
                agendaEntryListGSM.add(agendaEntry);
            }
        }
        return agendaEntryListGSM;
    }


    public boolean updateDate(AgendaEntry updatedEntry, LocalDate date) {
        for (AgendaEntry entry : agendaEntryList) {
            if (entry.equals(updatedEntry)) {
                entry.setDate(date);
                return true;
            }
        }
        return false;
    }

    public AgendaEntry getAgendaEntryByDescriptionAndGreenspace(String description, GreenSpace greenSpace) {
        for (AgendaEntry ag : agendaEntryList) {
            if (ag.getDescription().equals(description) && ag.getGreenSpace().equals(greenSpace)) return ag;
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

    public boolean assignTeam(AgendaEntry agendaEntry, Team team) {
        for (AgendaEntry entry : agendaEntryList) {
            if (entry.equals(agendaEntry)) {
                if (entry.getAssociatedTeam() == null || entry.getAssociatedTeam().getCollaborators().isEmpty()) {
                    entry.setAssociatedTeam(team);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public AgendaEntry getAgendaEntry(String description, GreenSpace greenSpace) {
        for (AgendaEntry ag : agendaEntryList) {
            if (ag.getDescription().equals(description) && ag.getGreenSpace().equals(greenSpace)) return ag;
        }
        return null;
    }

    public boolean assignVehicles(AgendaEntry agendaEntry, ArrayList<Vehicle> vehicleList) {
        for (AgendaEntry entry : agendaEntryList) {
            if (entry.equals(agendaEntry)) {
                entry.setAssociatedVehicles(vehicleList);
                return true;
            }
        }
        return false;
    }
        public ArrayList<AgendaEntry> getEntriesByCollaborator(String currentUserEmail){
            ArrayList<AgendaEntry> agendaEntryListCollaborator = new ArrayList<>();
            for (AgendaEntry agendaEntry : this.agendaEntryList) {
                Team team = agendaEntry.getAssociatedTeam();
                for (Collaborator c : team.getCollaborators()) {
                    if (c.getEmail().equals(currentUserEmail) && !agendaEntry.getEntryStatus().equals(EntryStatusENUM.CANCELLED) && !agendaEntry.getEntryStatus().equals(EntryStatusENUM.DONE)) {
                        agendaEntryListCollaborator.add(agendaEntry);
                    }
                }

            }
            return agendaEntryListCollaborator;
        }
    }
