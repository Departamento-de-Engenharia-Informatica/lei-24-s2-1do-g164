package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;
import pt.ipp.isep.dei.esoft.project.dto.ToDoEntryDTO;
import pt.ipp.isep.dei.esoft.project.mappers.AgendaEntryMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgendaEntryRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;

public class PostponeAgendaEntryController {

    private AgendaEntryRepository agendaEntryRepository;
    private AgendaEntryMapper agendaEntryMapper;
    AuthenticationController authenticationController = new AuthenticationController();

    public PostponeAgendaEntryController(){
        getAgendaEntryRepository();
        agendaEntryMapper = new AgendaEntryMapper();
    }

    public boolean postponeAgendaEntry(AgendaEntryDTO agendaEntryDTO){
        return agendaEntryRepository.postponeEntryInAgenda(agendaEntryMapper.toEntity(agendaEntryDTO));
    }

    private ArrayList<AgendaEntry> getAvailableAgendaEntryList(){
        return agendaEntryRepository.getAgendaEntryList(authenticationController.getCurrentUserEmail());
    }

    public ArrayList<AgendaEntryDTO> getToDoEntryDTOsList() {
        return agendaEntryMapper.toDtoList(getAvailableAgendaEntryList());
    }

    private void getAgendaEntryRepository() {
        if (agendaEntryRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agendaEntryRepository = repositories.getAgendaEntryRepository();
        }
    }
}
