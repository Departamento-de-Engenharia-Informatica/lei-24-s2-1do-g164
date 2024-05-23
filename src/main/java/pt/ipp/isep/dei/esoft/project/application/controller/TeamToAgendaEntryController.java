package pt.ipp.isep.dei.esoft.project.application.controller;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.application.session.emailService.EmailService;
import pt.ipp.isep.dei.esoft.project.repository.AgendaEntryRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;

import java.util.ArrayList;

public class TeamToAgendaEntryController {

    private AgendaEntryRepository agendaEntryRepository;
    private EmailService emailService;

    private TeamRepository teamRepository;



    public TeamToAgendaEntryController() {
        this.agendaEntryRepository= Repositories.getInstance().getAgendaEntryRepository();
        this.emailService = ApplicationSession.createEmailService();
        this.teamRepository= Repositories.getInstance().getTeamRepository();
    }

    private ArrayList<Team> ShowAvailableTeams() {
        return teamRepository.getTeams();
    }

    public boolean assignTeamToAgendaEntry(AgendaEntry agendaEntry, Team team) {
        try {
            agendaEntry.setAssociatedTeam(team);
            agendaEntryRepository.updateTeam(agendaEntry);
            sendNotificationEmails(team);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void sendNotificationEmails(Team team) {
            var collaborators = team.getCollaborators();
            for (Collaborator collaborator : collaborators) {
                String email = collaborator.getEmail();
                String body = "Hello " + collaborator.getName() + ",\n\nYou have been assigned to a new agenda entry!";
                emailService.sendEmail(email, body);
            }
        }
    }

