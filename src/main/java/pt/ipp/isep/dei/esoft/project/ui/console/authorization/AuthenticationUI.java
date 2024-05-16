package pt.ipp.isep.dei.esoft.project.ui.console.authorization;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.HrmUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.VfmUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Manages the authentication user interface.
 */
public class AuthenticationUI implements Runnable {
    private final AuthenticationController ctrl;

    /**
     * Initializes a new instance of AuthenticationUI.
     */
    public AuthenticationUI() {
        ctrl = new AuthenticationController();
    }

    /**
     * Runs the authentication user interface.
     */
    public void run() {
        boolean success = doLogin();

        if (success) {
            List<UserRoleDTO> roles = this.ctrl.getUserRoles();
            if ((roles == null) || (roles.isEmpty())) {
                System.out.println("No role assigned to user.");
            } else {
                UserRoleDTO role = selectsRole(roles);
                if (!Objects.isNull(role)) {
                    List<MenuItem> rolesUI = getMenuItemForRoles();
                    this.redirectToRoleUI(rolesUI, role);
                } else {
                    System.out.println("No role selected.");
                }
            }
        }
        this.logout();
    }

    /**
     * Gets the menu items for different user roles.
     *
     * @return The list of menu items.
     */
    private List<MenuItem> getMenuItemForRoles() {
        List<MenuItem> rolesUI = new ArrayList<>();
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_HRM, new HrmUI()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_VFM, new VfmUI()));

        //TODO: Complete with other user roles and related RoleUI
        return rolesUI;
    }

    /**
     * Performs the login process.
     *
     * @return True if login is successful, false otherwise.
     */
    private boolean doLogin() {
        System.out.println("\n\n--- LOGIN UI ---------------------------");

        int maxAttempts = 3;
        boolean success = false;
        do {
            maxAttempts--;
            String id = Utils.readLineFromConsole("Enter UserId/Email: ");
            String pwd = Utils.readLineFromConsole("Enter Password: ");

            success = ctrl.doLogin(id, pwd);
            if (!success) {
                System.out.println("Invalid UserId and/or Password. \n You have  " + maxAttempts + " more attempt(s).");
            }

        } while (!success && maxAttempts > 0);
        return success;
    }

    /**
     * Logs out the user.
     */
    private void logout() {
        ctrl.doLogout();
    }

    /**
     * Redirects to the UI corresponding to the user's role.
     *
     * @param rolesUI The list of menu items corresponding to user roles.
     * @param role    The selected user role.
     */
    private void redirectToRoleUI(List<MenuItem> rolesUI, UserRoleDTO role) {
        boolean found = false;
        Iterator<MenuItem> it = rolesUI.iterator();
        while (it.hasNext() && !found) {
            MenuItem item = it.next();
            found = item.hasDescription(role.getDescription());
            if (found) {
                item.run();
            }
        }
        if (!found) {
            System.out.println("There is no UI for users with role '" + role.getDescription() + "'");
        }
    }

    /**
     * Allows the user to select a role.
     *
     * @param roles The list of available roles.
     * @return The selected user role.
     */
    private UserRoleDTO selectsRole(List<UserRoleDTO> roles) {
        if (roles.size() == 1) {
            return roles.get(0);
        } else {
            return (UserRoleDTO) Utils.showAndSelectOne(roles, "Select the role you want to adopt in this session:");
        }
    }
}
