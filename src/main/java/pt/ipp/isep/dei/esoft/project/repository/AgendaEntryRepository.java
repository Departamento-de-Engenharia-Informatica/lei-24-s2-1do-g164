package pt.ipp.isep.dei.esoft.project.repository;


import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatusENUM;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The type Agenda entry repository.
 */
public class AgendaEntryRepository implements Serializable {

    private final ArrayList<AgendaEntry> agendaEntryList = new ArrayList<>();

    /**
     * Add entry to agenda boolean.
     *
     * @param ag the ag
     * @return the boolean
     */
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

    /**
     * Gets agenda entry list.
     *
     * @param email the email
     * @return the agenda entry list
     */
    public ArrayList<AgendaEntry> getAgendaEntryList(String email) {
        ArrayList<AgendaEntry> agendaEntryListGSM = new ArrayList<>();
        for (AgendaEntry agendaEntry : this.agendaEntryList) {
            if (agendaEntry.getGreenSpace().getEmailGSM().equals(email)) {
                agendaEntryListGSM.add(agendaEntry);
            }
        }
        return agendaEntryListGSM;
    }

    /**
     * Gets agenda entry list without team.
     *
     * @param email the email
     * @return the agenda entry list without team
     */
    public ArrayList<AgendaEntry> getAgendaEntryListWithoutTeam(String email) {
        ArrayList<AgendaEntry> agendaEntryListGSM = new ArrayList<>();
        for (AgendaEntry agendaEntry : this.agendaEntryList) {
            if (agendaEntry.getGreenSpace().getEmailGSM().equals(email) &&
                    agendaEntry.getAssociatedTeam().getCollaborators().isEmpty() &&
                    (agendaEntry.getEntryStatus().equals(EntryStatusENUM.PLANNED) ||
                            agendaEntry.getEntryStatus().equals(EntryStatusENUM.POSTPONED)))
            {
                agendaEntryListGSM.add(agendaEntry);
            }
        }
        return agendaEntryListGSM;
    }

    /**
     * Gets agenda entry list without cancelled.
     *
     * @param email the email
     * @return the agenda entry list without cancelled
     */
    public ArrayList<AgendaEntry> getAgendaEntryListWithoutCancelled(String email) {
        ArrayList<AgendaEntry> agendaEntryListGSM = new ArrayList<>();
        for (AgendaEntry agendaEntry : this.agendaEntryList) {
            if (agendaEntry.getGreenSpace().getEmailGSM().equals(email) &&
                    !agendaEntry.getEntryStatus().equals(EntryStatusENUM.CANCELLED) &&
                    !agendaEntry.getEntryStatus().equals(EntryStatusENUM.PENDING)) {
                agendaEntryListGSM.add(agendaEntry);
            }
        }
        return agendaEntryListGSM;
    }

    /**
     * Update date boolean.
     *
     * @param updatedEntry the updated entry
     * @param date         the date
     * @return the boolean
     */
    public boolean updateDate(AgendaEntry updatedEntry, LocalDate date) {
        for (AgendaEntry entry : agendaEntryList) {
            if (entry.equals(updatedEntry)) {
                entry.setDate(date);
                return true;
            }
        }
        return false;
    }

    /**
     * Gets agenda entry by description and greenspace.
     *
     * @param description the description
     * @param greenSpace  the green space
     * @return the agenda entry by description and greenspace
     */
    public AgendaEntry getAgendaEntryByDescriptionAndGreenspace(String description, GreenSpace greenSpace) {
        for (AgendaEntry ag : agendaEntryList) {
            if (ag.getDescription().equals(description) && ag.getGreenSpace().equals(greenSpace))
                return ag;
        }
        return null;
    }

    /**
     * Update status boolean.
     *
     * @param updatedEntry the updated entry
     * @param status       the status
     * @return the boolean
     */
    public boolean updateStatus(AgendaEntry updatedEntry, EntryStatusENUM status) {
        for (AgendaEntry entry : agendaEntryList) {
            if (entry.equals(updatedEntry)) {
                entry.setEntryStatus(status);
                return true;
            }
        }
        return false;
    }

    /**
     * Assign team boolean.
     *
     * @param agendaEntry the agenda entry
     * @param team        the team
     * @return the boolean
     */
    public boolean assignTeam(AgendaEntry agendaEntry, Team team) {
        if (!agendaEntry.getAssociatedTeam().getCollaboratorsNames().isEmpty()){
            return false;
        }

        agendaEntry.setAssociatedTeam(team);
        return true;
    }


    /**
     * Gets agenda entry.
     *
     * @param description the description
     * @param greenSpace  the green space
     * @return the agenda entry
     */
    public AgendaEntry getAgendaEntry(String description, GreenSpace greenSpace) {
        for (AgendaEntry ag : agendaEntryList) {
            if (ag.getDescription().equals(description) && ag.getGreenSpace().equals(greenSpace))
                return ag;
        }
        return null;
    }
}