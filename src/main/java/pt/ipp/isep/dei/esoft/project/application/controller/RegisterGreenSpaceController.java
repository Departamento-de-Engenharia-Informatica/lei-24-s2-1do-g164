package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.ToDoEntry;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.dto.ToDoEntryDTO;
import pt.ipp.isep.dei.esoft.project.mappers.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.mappers.ToDoEntryMapper;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.ToDoEntryRepository;

import java.util.ArrayList;

public class RegisterGreenSpaceController {
    private GreenSpaceRepository greenSpaceRepository;
    private GreenSpaceMapper greenSpaceMapper;


    public RegisterGreenSpaceController() {
        getGreenSpaceRepository();
        greenSpaceMapper = new GreenSpaceMapper();
    }

    public boolean registerGreenSpace(GreenSpaceDTO greenSpaceDTO) {
        return greenSpaceRepository.registerGreenSpace(greenSpaceMapper.toEntity(greenSpaceDTO));
    }

    public ArrayList<GreenSpace> getGreenSpaceList(){
        return greenSpaceRepository.getGreenSpaceList();
    }

    public ArrayList<GreenSpaceDTO> getGreenSpaceDTOsList(){
        return greenSpaceMapper.toDTOList(getGreenSpaceList());
    }



    private GreenSpaceRepository getGreenSpaceRepository() {
        if (greenSpaceRepository == null) {
            Repositories repositories = Repositories.getInstance();
            greenSpaceRepository = repositories.getGreenSpaceRepository();
        }
        return greenSpaceRepository;
    }
    
}
