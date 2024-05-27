package pt.ipp.isep.dei.esoft.project.repository;

import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;

import java.io.Serializable;

/**
 * AuthenticationRepository - Repository for managing user authentication.
 */
public class AuthenticationRepository implements Serializable {

    private final AuthFacade authenticationFacade;

    /**
     * Constructs a new AuthenticationRepository instance.
     */
    public AuthenticationRepository() {
        authenticationFacade = new AuthFacade();
    }

    /**
     * Performs user login.
     *
     * @param email the user's email
     * @param pwd   the user's password
     * @return true if login is successful, false otherwise
     */
    public boolean doLogin(String email, String pwd) {
        return authenticationFacade.doLogin(email, pwd).isLoggedIn();
    }

    /**
     * Performs user logout.
     */
    public void doLogout() {
        authenticationFacade.doLogout();
    }

    /**
     * Retrieves the current user session.
     *
     * @return the current user session
     */
    public UserSession getCurrentUserSession() {
        return authenticationFacade.getCurrentUserSession();
    }

    public String getCurrentUserEmail() {
        return getCurrentUserSession().getUserId().getEmail();
    }

    /**
     * Adds a new user role.
     *
     * @param id          the role ID
     * @param description the role description
     * @return true if the role is added successfully, false otherwise
     */
    public boolean addUserRole(String id, String description) {
        return authenticationFacade.addUserRole(id, description);
    }

    /**
     * Adds a new user with a specific role.
     *
     * @param name   the user's name
     * @param email  the user's email
     * @param pwd    the user's password
     * @param roleId the role ID assigned to the user
     * @return true if the user is added successfully, false otherwise
     */
    public boolean addUserWithRole(String name, String email, String pwd, String roleId) {
        return authenticationFacade.addUserWithRole(name, email, pwd, roleId);
    }
}
