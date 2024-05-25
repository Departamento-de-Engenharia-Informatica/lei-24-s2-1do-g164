package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.ToDoEntry;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDTO;
import pt.ipp.isep.dei.esoft.project.dto.ToDoEntryDTO;
import pt.ipp.isep.dei.esoft.project.mappers.AgendaEntryMapper;
import pt.ipp.isep.dei.esoft.project.mappers.ToDoEntryMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgendaEntryRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.ToDoEntryRepository;

import java.util.ArrayList;

public class RegisterAgendaEntryController {

    private AgendaEntryRepository agendaEntryRepository = new AgendaEntryRepository();
    private ToDoEntryRepository toDoEntryRepository = new ToDoEntryRepository();
    private final AgendaEntryMapper agendaEntryMapper;
    private final ToDoEntryMapper toDoEntryMapper;


    public RegisterAgendaEntryController(){
        getAgendaEntryRepository();
        getToDoEntryRepository();
        toDoEntryMapper = new ToDoEntryMapper();
        agendaEntryMapper = new AgendaEntryMapper();
    }

    public boolean registerAgendaEntry(AgendaEntryDTO agendaEntryDTO){
        return agendaEntryRepository.addEntryToAgenda(agendaEntryMapper.toEntity(agendaEntryDTO));
    }

    public ArrayList<AgendaEntryDTO> getAgendaEntryListDTO() {
        var entries= agendaEntryRepository.getAgendaEntryList();
        return agendaEntryMapper.toDtoList(entries);
    }

    private ArrayList<ToDoEntry> getAvailableToDoEntryList(){
        return toDoEntryRepository.getToDoEntryList();
    }

    public ArrayList<ToDoEntryDTO> getToDoEntryDTOsList() {
        return toDoEntryMapper.toDTOList(getAvailableToDoEntryList());
    }


    private void getToDoEntryRepository() {
        if (toDoEntryRepository == null) {
            Repositories repositories = Repositories.getInstance();
            toDoEntryRepository = repositories.getToDoEntryRepository();
        }
    }

    private void getAgendaEntryRepository() {
        if (agendaEntryRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agendaEntryRepository = repositories.getAgendaEntryRepository();
        }
    }


}
