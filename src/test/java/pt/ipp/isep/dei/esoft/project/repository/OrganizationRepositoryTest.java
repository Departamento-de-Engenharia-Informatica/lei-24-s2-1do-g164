package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.SystemUser;
import pt.ipp.isep.dei.esoft.project.domain.Organization;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class
OrganizationRepositoryTest {

    @Test
    void testAddOrganization() {
        OrganizationRepository organizationRepository = new OrganizationRepository();
        Organization organization = new Organization("123456789");

        Optional<Organization> returnOrganization = organizationRepository.add(organization);

        assertEquals(organization, returnOrganization.get());
    }

    @Test
    void ensureGetOrganizationByEmployeeWorks() {
        OrganizationRepository organizationRepository = new OrganizationRepository();
        Organization organization = new Organization("123456789");
        SystemUser systemUser = new SystemUser("john.doe@this.company.com");
        organization.addEmployee(systemUser);
        organizationRepository.add(organization);

        Optional<Organization> result = organizationRepository.getOrganizationByEmployee(systemUser);

        assertEquals(organization, result.get());
    }

    @Test
    void ensureGetOrganizationByEmployeeFails() {
        OrganizationRepository organizationRepository = new OrganizationRepository();
        Organization organization = new Organization("123456789");
        SystemUser systemUser = new SystemUser("john.doe@this.company.com");
        organization.addEmployee(systemUser);
        organizationRepository.add(organization);

        SystemUser systemUser2 = new SystemUser("jane.doe@this.company.com");
        Optional<Organization> result = organizationRepository.getOrganizationByEmployee(systemUser2);

        assertTrue(result.isEmpty());
    }

    @Test
    void ensureGetOrganizationByEmailWorks() {
        OrganizationRepository organizationRepository = new OrganizationRepository();
        Organization organization = new Organization("123456789");
        SystemUser systemUser = new SystemUser("john.doe@this.company.com");
        organization.addEmployee(systemUser);
        organizationRepository.add(organization);

        Optional<Organization> result =
                organizationRepository.getOrganizationByEmployeeEmail("john.doe@this.company.com");

        assertEquals(organization, result.get());
    }

    @Test
    void ensureAddOrganizationWorks() {
        OrganizationRepository organizationRepository = new OrganizationRepository();
        Organization organization = new Organization("123456789");
        SystemUser systemUser = new SystemUser("john.doe@this.company.com");
        organization.addEmployee(systemUser);

        organizationRepository.add(organization);

        Optional<Organization> returnOrganization =
                organizationRepository.getOrganizationByEmployeeEmail("john.doe@this" + ".company.com");

        //Assert
        //Make sure both represents the same object
        assertEquals(organization, returnOrganization.get());
        //Make sure it is a clone (different memory addresses)
        assertNotSame(organization, returnOrganization.get());
    }

    @Test
    void ensureAddOrganizationDuplicateFails() {
        OrganizationRepository organizationRepository = new OrganizationRepository();
        Organization organization = new Organization("123456789");
        SystemUser systemUser = new SystemUser("john.doe@this.company.com");
        organization.addEmployee(systemUser);
        organizationRepository.add(organization);

        Optional<Organization> result = organizationRepository.add(organization);

        assertTrue(result.isEmpty());
    }
}