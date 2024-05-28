package pt.ipp.isep.dei.esoft.project.application.controller.authorization;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.AgendaEntryRepository;
import pt.ipp.isep.dei.esoft.project.mappers.AgendaEntryMapper;

import java.util.ArrayList;

public class MarkTaskCompletedController {
    private AgendaEntryRepository agendaEntryRepository;
    private AuthenticationController authenticationController;
    private AgendaEntryMapper agendaEntryMapper;

    public MarkTaskCompletedController(){
        this.agendaEntryRepository = getAgendaEntryRepository();
        this.authenticationController = new AuthenticationController();
        this.agendaEntryMapper = new AgendaEntryMapper();
    }

    private AgendaEntryRepository getAgendaEntryRepository() {
        if (agendaEntryRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agendaEntryRepository = repositories.getAgendaEntryRepository();
        }
        return agendaEntryRepository;
    }

    public ArrayList<AgendaEntryDTO> getAgendaEntryDTOList() {
        String currentUserEmail = authenticationController.getCurrentUserEmail();
        ArrayList<AgendaEntry> agendaEntryList = agendaEntryRepository.getAgendaEntryList(currentUserEmail);
        return agendaEntryMapper.toDtoList(agendaEntryList);
    }
}
