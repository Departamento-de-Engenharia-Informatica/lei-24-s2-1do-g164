package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationTest {

    @Test
    void testEqualsSameObject() {
        Organization organization = new Organization("123456789");
        assertEquals(organization, organization);
    }

    @Test
    void testEqualsDifferentClass() {
        Organization organization = new Organization("123456789");
        assertNotEquals("", organization);
    }

    @Test
    void testEqualsNull() {
        Organization organization = new Organization("123456789");
        assertNotEquals(null, organization);
    }

    @Test
    void testEqualsDifferentObject() {
        Organization organization = new Organization("123456789");
        Organization organization1 = new Organization("123456789");
        assertEquals(organization, organization1);
    }

    @Test
    void testHashCodeSameObject() {
        Organization organization = new Organization("123456789");
        assertEquals(organization.hashCode(), organization.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        Organization organization = new Organization("123456789");
        Organization organization1 = new Organization("123456789");
        assertEquals(organization.hashCode(), organization1.hashCode());
    }

    @Test
        //same hashcode
    void testHashCodeSameObjectSameVATNumber() {
        Organization organization = new Organization("123456789");
        Organization organization1 = new Organization("123456789");
        assertEquals(organization.hashCode(), organization1.hashCode());
    }

    @Test
    void ensureHashCodeFailsForDifferentVatNumbers() {
        Organization organization = new Organization("123456789");
        Organization organization1 = new Organization("123456788");
        assertNotEquals(organization.hashCode(), organization1.hashCode());
    }

    @Test
    void ensureEqualsFailsForDifferentObjectType() {
        Organization organization = new Organization("123456789");
        Organization organization1 = new Organization("123456788");
        assertNotEquals(organization, organization1);
    }

    @Test
    void ensureEqualsFailsWhenComparingNull() {
        Organization organization = new Organization("123456789");
        assertNotEquals(organization, null);
    }

    @Test
    void ensureEqualsSuccessWhenComparingSameObject() {
        Organization organization = new Organization("123456789");
        assertEquals(organization, organization);
    }

    @Test
    void testThatCreateTaskWorks() {
        Organization organization = new Organization("123456789");

        SystemUser systemUser = new SystemUser("john.doe@this.company.com");
        TaskCategory taskCategory = new TaskCategory("Task Category Description");

        Task expected = new Task("Task Description", "Task Category Description", "informal description",
                "technical description", 1, 1d, taskCategory, systemUser);

        Optional<Task> task =
                organization.createTask("Task Description", "Task Category Description", "informal description",
                        "technical description", 1, 1d, taskCategory, systemUser);

        assertNotNull(task);
        assertTrue(task.isPresent());
        assertEquals(expected, task.get());
    }

    @Test
    void ensureAddingDuplicateTaskFails() {
        //Arrange
        Organization organization = new Organization("123456789");
        SystemUser systemUser = new SystemUser("john.doe@this.company.com");
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        //Add the first task
        Optional<Task> originalTask =
                organization.createTask("Task Description", "Task Category Description", "informal description",
                        "technical description", 1, 1d, taskCategory, systemUser);

        //Act
        Optional<Task> duplicateTask =
                organization.createTask("Task Description", "Task Category Description", "informal description",
                        "technical description", 1, 1d, taskCategory, systemUser);

        //Assert
        assertTrue(duplicateTask.isEmpty());
    }


    @Test
    void ensureEmploysFails() {
        Organization organization = new Organization("123456789");
        SystemUser systemUser = new SystemUser("john.doe@this.company.com");

        assertFalse(organization.employs(systemUser));

    }

    @Test
    void ensureEmploysSuccess() {
        Organization organization = new Organization("123456789");
        SystemUser systemUser = new SystemUser("john.doe@this.company.com");
        organization.addEmployee(systemUser);
        assertTrue(organization.employs(systemUser));
    }

    @Test
    void ensureAnyEmployeeHasEmailFails() {
        Organization organization = new Organization("123456789");
        SystemUser systemUser = new SystemUser("john.doe@this.company.com");
        organization.addEmployee(systemUser);
        assertFalse(organization.anyEmployeeHasEmail("jane.doe@this.company.com"));
    }

    @Test
    void ensureAnyEmployeeHasEmailWorks() {
        Organization organization = new Organization("123456789");
        SystemUser systemUser = new SystemUser("john.doe@this.company.com");
        organization.addEmployee(systemUser);
        assertTrue(organization.anyEmployeeHasEmail("john.doe@this.company.com"));
    }

    @Test
    void ensureAddDuplicateEmployeeFails() {
        Organization organization = new Organization("123456789");
        SystemUser systemUser = new SystemUser("john.doe@this.company.com");
        assertTrue(organization.addEmployee(systemUser));
        assertFalse(organization.addEmployee(systemUser));
    }

    @Test
    void ensureAddEmployeeWorks() {
        Organization organization = new Organization("123456789");
        SystemUser systemUser = new SystemUser("john.doe@this.company.com");
        assertTrue(organization.addEmployee(systemUser));
    }

    @Test
    void ensureCloneWorks() {
        Organization organization = new Organization("123456789");
        SystemUser systemUser = new SystemUser("john.doe@this.company.com");
        organization.addEmployee(systemUser);
        organization.createTask("Task Description", "Task Category Description", "informal description",
                "technical description", 1, 1d, new TaskCategory("Task Category Description"), systemUser);

        Organization clone = organization.clone();
        assertEquals(organization, clone);
    }
}