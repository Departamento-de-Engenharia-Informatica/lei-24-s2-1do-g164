package pt.ipp.isep.dei.esoft.project.application.controller;
import com.kitfox.svg.A;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.application.session.emailService.EmailService;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;
import pt.ipp.isep.dei.esoft.project.mappers.AgendaEntryMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgendaEntryRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class TeamToAgendaEntryController {

    private AgendaEntryRepository agendaEntryRepository;
    private EmailService emailService;

    private TeamRepository teamRepository;

    private AgendaEntryMapper agendaEntryMapper;



    public TeamToAgendaEntryController() {
        this.agendaEntryRepository= Repositories.getInstance().getAgendaEntryRepository();
        this.emailService = ApplicationSession.createEmailService();
        this.teamRepository= Repositories.getInstance().getTeamRepository();
    }

    private ArrayList<Team> showAvailableTeams() {
        return teamRepository.getTeams();
    }

     public ArrayList<AgendaEntryDTO> getAgendaEntryDTOList() {
        ArrayList<AgendaEntry> agendaEntryList = agendaEntryRepository.getAgendaEntryList();
        return agendaEntryMapper.toDtoList(agendaEntryList);
    }


    public boolean assignTeamToAgendaEntry(AgendaEntryDTO dto) {

        var entry= agendaEntryRepository.getAgendaEntry(dto.description, dto.greenSpace);
        if (entry == null) {
            throw new InputMismatchException("Agenda Entry not found!");
        }

          if(agendaEntryRepository.updateTeam(entry, dto.team)) {
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
                emailService.sendEmail(email, body);
            }
        }
    }

