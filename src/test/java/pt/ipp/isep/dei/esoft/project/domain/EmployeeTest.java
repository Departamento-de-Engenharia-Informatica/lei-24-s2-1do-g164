package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void ensureTwoEmployeesWithSameEmailEquals() {
        SystemUser systemUser1 = new SystemUser("john.doe@this.company.com");
        SystemUser systemUser2 = new SystemUser("john.doe@this.company.com");
        assertEquals(systemUser1, systemUser2);
    }

    @Test
    void ensureEmployeeWithDifferentEmailNotEquals() {
        SystemUser systemUser1 = new SystemUser("john.doe@this.company.com");
        SystemUser systemUser2 = new SystemUser("jane.doe@this.company.com");
        assertNotEquals(systemUser1, systemUser2);
    }

    @Test
    void ensureEmployeeDoesNotEqualNull() {
        SystemUser systemUser1 = new SystemUser("john.doe@this.company.com");
        assertNotEquals(systemUser1, null);
    }

    @Test
    void ensureEmployeeDoesNotEqualOtherObject() {
        SystemUser systemUser1 = new SystemUser("john.doe@this.company.com");
        assertNotEquals(systemUser1, new Object());
    }

    @Test
    void ensureTheSameObjectIsEqual() {
        SystemUser systemUser1 = new SystemUser("john.doe@this.company.com");
        assertEquals(systemUser1, systemUser1);
    }

    @Test
    void ensureHashCodeIsEqualForEqualObjects() {
        String email = "john.doe@this.company.com";
        SystemUser systemUser1 = new SystemUser(email);
        SystemUser systemUser2 = new SystemUser(email);
        assertEquals(systemUser1.hashCode(), systemUser2.hashCode());
    }

    @Test
    void ensureHashCodeIsNotEqualForDifferentObjects() {

        SystemUser systemUser1 = new SystemUser("john.doe@this.company.com");
        SystemUser systemUser2 = new SystemUser("jane.doe@this.company.com");
        assertNotEquals(systemUser1.hashCode(), systemUser2.hashCode());
    }

    @Test
    void ensureHasEmailWorksForTheSameEmail() {
        String email = "john.doe@this.compay.org";
        SystemUser systemUser = new SystemUser(email);
        assertTrue(systemUser.hasEmail(email));

    }

    @Test
    void ensureHasEmailFailsForDifferentEmails() {
        String email = "john.doe@this.company.com";
        SystemUser systemUser = new SystemUser(email);
        assertFalse(systemUser.hasEmail("jane.doe@this.company.com"));

    }

    @Test
    void ensureCloneWorks() {
        String email = "john.doe@this.company.com";
        SystemUser systemUser = new SystemUser(email);
        SystemUser clone = systemUser.clone();
        assertEquals(systemUser, clone);
    }
}