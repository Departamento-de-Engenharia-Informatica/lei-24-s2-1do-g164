package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void ensureTaskIsCreatedSuccessfully() {
        SystemUser systemUser = new SystemUser("john.doe@this.company.com");
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        Task task = new Task("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory, systemUser);
    }

    @Test
    void ensureTaskReferenceIsNotNull() {
        //Arrange
        SystemUser systemUser = new SystemUser("john.doe@this.company.com");
        TaskCategory taskCategory = new TaskCategory("Task Category Description");

        //Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> new Task(null, "description", "informal description", "technical description", 1, 1d,
                        taskCategory, systemUser));
    }

    @Test
    void testEqualsSameObject() {
        SystemUser systemUser = new SystemUser("john.doe@this.company.com");
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        Task task = new Task("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory, systemUser);

        assertEquals(task, task);
    }

    @Test
    void testEqualsDifferentClass() {
        SystemUser systemUser = new SystemUser("john.doe@this.company.com");
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        Task task = new Task("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory, systemUser);

        assertNotEquals(task, new Object());
    }

    @Test
    void testEqualsNull() {
        SystemUser systemUser = new SystemUser("john.doe@this.company.com");
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        Task task = new Task("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory, systemUser);

        assertNotEquals(task, null);
    }

    @Test
    void testEqualsDifferentObject() {
        SystemUser systemUser = new SystemUser("john.doe@this.company.com");
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        Task task = new Task("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory, systemUser);
        Task task1 = new Task("reference1", "description1", "informal description1", "technical description1", 2, 2d,
                taskCategory, systemUser);

        assertNotEquals(task, task1);
    }

    @Test
    void testEqualsSameObjectDifferentDescription() {
        SystemUser systemUser = new SystemUser("john.doe@this.company.com");
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        Task task = new Task("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory, systemUser);
        Task task1 = new Task("reference1", "description", "informal description1", "technical description1", 2, 2d,
                taskCategory, systemUser);

        assertNotEquals(task, task1);
    }

    @Test
    void testEqualsSameObjectSameDescription() {
        SystemUser systemUser = new SystemUser("john.doe@this.company.com");
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        Task task = new Task("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory, systemUser);
        Task task1 = new Task("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory, systemUser);

        assertEquals(task, task1);
    }

    @Test
    void testHashCodeSameObject() {
        SystemUser systemUser = new SystemUser("john.doe@this.company.com");
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        Task task = new Task("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory, systemUser);

        assertEquals(task.hashCode(), task.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        SystemUser systemUser = new SystemUser("john.doe@this.company.com");
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        Task task = new Task("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory, systemUser);
        Task task1 = new Task("reference1", "description1", "informal description1", "technical description1", 2, 2d,
                taskCategory, systemUser);

        assertNotEquals(task.hashCode(), task1.hashCode());
    }

    @Test
    void ensureCloneWorks() {
        SystemUser systemUser = new SystemUser("john.doe@this.company.org");
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        Task task = new Task("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory, systemUser);
        Task clone = task.clone();
        assertEquals(task, clone);
    }
}