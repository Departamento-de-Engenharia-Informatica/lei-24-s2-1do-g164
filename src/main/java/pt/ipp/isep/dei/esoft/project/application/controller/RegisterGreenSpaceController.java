package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.mappers.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;

public class RegisterGreenSpaceController {
    private GreenSpaceRepository greenSpaceRepository;
    private GreenSpaceMapper greenSpaceMapper;
    private final AuthenticationController authenticationController= new AuthenticationController();


    /**
     * Initializes a new instance of RegisterGreenSpaceController.
     */
    public RegisterGreenSpaceController() {
        getGreenSpaceRepository();
        greenSpaceMapper = new GreenSpaceMapper();
    }

    /**
     * Registers a new green space with the provided details.
     *
     * @param greenSpaceDTO The GreenSpaceDTO object representing the green space to be registered.
     * @return {@code true} if the green space is successfully registered, {@code false} otherwise.
     */
    public boolean registerGreenSpace(GreenSpaceDTO greenSpaceDTO) {
        return greenSpaceRepository.registerGreenSpace(greenSpaceMapper.toEntity(greenSpaceDTO));
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



    private GreenSpaceRepository getGreenSpaceRepository() {
        if (greenSpaceRepository == null) {
            Repositories repositories = Repositories.getInstance();
            greenSpaceRepository = repositories.getGreenSpaceRepository();
        }
        return greenSpaceRepository;
    }

}
