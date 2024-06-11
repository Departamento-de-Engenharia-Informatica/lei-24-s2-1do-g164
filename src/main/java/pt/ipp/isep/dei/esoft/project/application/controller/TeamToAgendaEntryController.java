package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.application.session.emailService.EmailService;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;
import pt.ipp.isep.dei.esoft.project.dto.TeamDTO;
import pt.ipp.isep.dei.esoft.project.mappers.AgendaEntryMapper;
import pt.ipp.isep.dei.esoft.project.mappers.TeamMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgendaEntryRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 * The type Team to agenda entry controller.
 */
public class TeamToAgendaEntryController {
    private AgendaEntryRepository agendaEntryRepository;
    private EmailService emailService;
    private TeamRepository teamRepository;
    private final AgendaEntryMapper agendaEntryMapper = new AgendaEntryMapper();
    private final TeamMapper teamMapper = new TeamMapper();
    private final AuthenticationController authenticationController = new AuthenticationController();

    /**
     * Instantiates a new Team to agenda entry controller.
     */
    public TeamToAgendaEntryController() {
        getAgendaEntryRepository();
        this.teamRepository= Repositories.getInstance().getTeamRepository();
    }

    /**
     * Show available teams dto list.
     *
     * @return the list
     */
    public List<TeamDTO> showAvailableTeamsDTO() {
        var teams = teamRepository.getTeams();
        return teamMapper.toDtoList(teams);
    }

    /**
     * Gets agenda entries dto without team.
     *
     * @return the agenda entries dto without team
     */
    public ArrayList<AgendaEntryDTO> getAgendaEntriesDTOWithoutTeam() {
        ArrayList<AgendaEntry> agendaEntryList = agendaEntryRepository.getAgendaEntryListWithoutTeam(authenticationController.getCurrentUserEmail());
        return agendaEntryMapper.toDtoList(agendaEntryList);
    }


    private LocalDateTime calculateEndTime(AgendaEntry entry) {
        LocalDateTime startTime = entry.getDate();
        int totalDurationInHours = entry.getExpectedDuration();
        LocalDateTime endTime = startTime;

        int workStartHour = 9;
        int workEndHour = 17;
        int workDayHours = workEndHour - workStartHour;

        while (totalDurationInHours > 0) {
            int currentHour = endTime.getHour();

            if (currentHour < workStartHour) {
                endTime = endTime.withHour(workStartHour).withMinute(0);
                currentHour = workStartHour;
            } else if (currentHour >= workEndHour) {
                endTime = endTime.plusDays(1).withHour(workStartHour).withMinute(0);
                currentHour = workStartHour;
            }

            int hoursToEndOfDay = workEndHour - currentHour;
            int hoursToAdd = Math.min(totalDurationInHours, hoursToEndOfDay);
            endTime = endTime.plusHours(hoursToAdd);
            totalDurationInHours -= hoursToAdd;

            if (totalDurationInHours > 0) {
                endTime = endTime.plusDays(1).withHour(workStartHour).withMinute(0);
            }
        }

        return endTime;
    }


    /**
     * Assign team to agenda entry boolean.
     *
     * @param dto     the dto
     * @param teamDTO the team dto
     * @return the boolean
     */
    public boolean assignTeamToAgendaEntry(AgendaEntryDTO dto, TeamDTO teamDTO) {
        var entry= agendaEntryRepository.getAgendaEntry(dto.description, dto.greenSpace);
        if (entry == null) {
            throw new InputMismatchException("Agenda Entry not found!");
        }

        var team = teamRepository.getTeamByCollaborators(teamDTO.getCollaborators());
        if (team == null) {
            throw new InputMismatchException("Team not found!");
        }

        for (Collaborator collaborator : team.getCollaborators()) {
            if (hasOverlappingEntries(entry, collaborator)) {
                return false;
            }
        }
        if (agendaEntryRepository.assignTeam(entry, team)) {
            sendNotificationEmails(entry.getAssociatedTeam());
            return true;
        }

        return false;
    }

    private void sendNotificationEmails(Team team) {
        var collaborators = team.getCollaborators();
        for (Collaborator collaborator : collaborators) {
            String email = collaborator.getEmail();
            String body = "Hello " + collaborator.getName() + ",\nYou have been assigned to a new agenda entry!";
            EmailService emailService= ApplicationSession.getEmailService();
            emailService.sendEmail(email, body);
        }
    }


    private AgendaEntryRepository getAgendaEntryRepository() {
        if (agendaEntryRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agendaEntryRepository = repositories.getAgendaEntryRepository();
        }
        return agendaEntryRepository;
    }

    private boolean hasOverlappingEntries(AgendaEntry newEntry, Collaborator collaborator) {
        LocalDateTime newEntryStart = newEntry.getDate();
        LocalDateTime newEntryEnd = calculateEndTime(newEntry);

        LocalDateTime currentDate = newEntryStart.toLocalDate().atTime(9, 0);
        LocalDateTime currentDayEnd = newEntryStart.toLocalDate().atTime(17, 0);

        while (currentDate.isBefore(newEntryEnd) || currentDate.equals(newEntryEnd)) {
            List<AgendaEntry> collaboratorEntries = new ArrayList<>();
            for (var entry : agendaEntryRepository.getAgendaEntryList()) {
                if (entry.getAssociatedTeam().getCollaborators().contains(collaborator)) {
                    collaboratorEntries.add(entry);
                }
            }

            for (var entry : collaboratorEntries) {
                LocalDateTime entryStart = entry.getDate();
                LocalDateTime entryEnd = calculateEndTime(entry);

                if (newEntryStart.isBefore(entryEnd) && newEntryEnd.isAfter(entryStart)) {
                    return true;
                }
            }

            currentDate = currentDate.plusDays(1).withHour(9).withMinute(0);
            currentDayEnd = currentDayEnd.plusDays(1).withHour(17).withMinute(0);
        }
        return false;
    }


}

