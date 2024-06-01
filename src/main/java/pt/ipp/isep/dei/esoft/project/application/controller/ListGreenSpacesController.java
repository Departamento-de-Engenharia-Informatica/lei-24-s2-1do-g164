package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.application.session.sorting.SortingAlgorithm;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.mappers.AgendaEntryMapper;
import pt.ipp.isep.dei.esoft.project.mappers.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;

public class ListGreenSpacesController {

    private GreenSpaceRepository greenSpaceRepository;
    private GreenSpaceMapper greenSpaceMapper;
    private final AuthenticationController authenticationController= new AuthenticationController();


    public ListGreenSpacesController() {
        greenSpaceRepository= Repositories.getInstance().getGreenSpaceRepository();
        greenSpaceMapper = new GreenSpaceMapper();
    }
    /**
     * Retrieves a list of green spaces associated with the current user.
     *
     * @return An ArrayList of GreenSpace objects representing the green spaces associated with the current user.
     */
    public ArrayList<GreenSpace> getGreenSpaceList(){
        return greenSpaceRepository.getGreenSpaceList(authenticationController.getCurrentUserEmail());
    }

    /**
     * Retrieves a list of green space DTOs associated with the current user.
     *
     * @return An ArrayList of GreenSpaceDTO objects representing the green spaces associated with the current user.
     */
    public ArrayList<GreenSpaceDTO> getGreenSpaceDTOsList(){
        return greenSpaceMapper.toDTOList(getGreenSpaceList());
    }

    public void sortGreenSpaces(ArrayList<GreenSpaceDTO> greenSpaceDTOsList) {

        SortingAlgorithm sortingAlgorithm = ApplicationSession.getSortingAlgorithm();
        sortingAlgorithm.sort(greenSpaceDTOsList);
    }




}


