package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.SystemUser;
import pt.ipp.isep.dei.esoft.project.domain.Organization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Organization repository.
 */
public class OrganizationRepository implements Serializable {

    private final List<Organization> organizations;

    /**
     * Instantiates a new Organization repository.
     */
    public OrganizationRepository() {
        organizations = new ArrayList<>();
    }

    /**
     * Gets organization by employee.
     *
     * @param systemUser the system user
     * @return the organization by employee
     */
    public Optional<Organization> getOrganizationByEmployee(SystemUser systemUser) {

        Optional<Organization> returnOrganization = Optional.empty();

        for (Organization organization : organizations) {
            if (organization.employs(systemUser)) {
                returnOrganization = Optional.of(organization);
            }
        }

        return returnOrganization;
    }

    /**
     * Gets organization by employee email.
     *
     * @param email the email
     * @return the organization by employee email
     */
    public Optional<Organization> getOrganizationByEmployeeEmail(String email) {

        Optional<Organization> returnOrganization = Optional.empty();

        for (Organization organization : organizations) {
            if (organization.anyEmployeeHasEmail(email)) {
                returnOrganization = Optional.of(organization);
            }
        }

        return returnOrganization;
    }

    /**
     * Add optional.
     *
     * @param organization the organization
     * @return the optional
     */
    public Optional<Organization> add(Organization organization) {

        Optional<Organization> newOrganization = Optional.empty();
        boolean operationSuccess = false;

        if (validateOrganization(organization)) {
            newOrganization = Optional.of(organization.clone());
            operationSuccess = organizations.add(newOrganization.get());
        }

        if (!operationSuccess) {
            newOrganization = Optional.empty();
        }

        return newOrganization;

    }

    private boolean validateOrganization(Organization organization) {
        boolean isValid = !organizations.contains(organization);

        return isValid;
    }
}