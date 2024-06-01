package pt.ipp.isep.dei.esoft.project.application.controller.authorization;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.List;

/**
 * Controller class responsible for managing user authentication.
 */
public class AuthenticationController {

    public static final String ROLE_HRM = "HRM";
    public static final String ROLE_VFM = "VFM";
    public static final String ROLE_GSM = "GSM";
    public static final String ROLE_COL = "COL";

    //private final ApplicationSession applicationSession;
    private final AuthenticationRepository authenticationRepository;

    /**
     * Constructs an AuthenticationController.
     */
    public AuthenticationController() {
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    /**
     * Attempts to log in with the given credentials.
     *
     * @param email The user's email.
     * @param pwd   The user's password.
     * @return True if the login was successful, false otherwise.
     */
    public boolean doLogin(String email, String pwd) {
        try {
            return authenticationRepository.doLogin(email, pwd);
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    /**
     * Retrieves the roles of the current user.
     *
     * @return The roles of the current user.
     */
    public List<UserRoleDTO> getUserRoles() {
        if (authenticationRepository.getCurrentUserSession().isLoggedIn()) {
            return authenticationRepository.getCurrentUserSession().getUserRoles();
        }
        return null;
    }

    /**
     * Retrieves the email of the currently logged-in user.
     *
     * @return The email of the currently logged-in user.
     */
    public String getCurrentUserEmail() {
        if (authenticationRepository.getCurrentUserSession().isLoggedIn()) {
            return authenticationRepository.getCurrentUserEmail();
        } else {
            return "aa";
        }
    }

    /**
     * Logs out the current user.
     */
    public void doLogout() {
        authenticationRepository.doLogout();
    }
}
