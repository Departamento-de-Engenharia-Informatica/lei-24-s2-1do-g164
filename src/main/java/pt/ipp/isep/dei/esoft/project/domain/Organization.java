package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Organization implements Serializable {
    private final String vatNumber;
    private final List<SystemUser> systemUsers;
    private final List<Task> tasks;
    private String name;
    private String website;
    private String phone;
    private String email;

    /**
     * This method is the constructor of the organization.
     *
     * @param vatNumber The vat number of the organization. This is the identity of the organization, therefore it
     *                  cannot be changed.
     */
    public Organization(String vatNumber) {
        this.vatNumber = vatNumber;
        systemUsers = new ArrayList<>();
        tasks = new ArrayList<>();
    }

    /**
     * This method checks if an employee works for the organization.
     *
     * @param systemUser The employee to be checked.
     * @return True if the employee works for the organization.
     */
    public boolean employs(SystemUser systemUser) {
        return systemUsers.contains(systemUser);
    }

    /**
     * This method creates a new task.
     *
     * @param reference            The reference of the task to be created.
     * @param description          The description of the task to be created.
     * @param informalDescription  The informal description of the task to be created.
     * @param technicalDescription The technical description of the task to be created.
     * @param duration             The duration of the task to be created.
     * @param cost                 The cost of the task to be created.
     * @param taskCategory         The task category of the task to be created.
     * @param systemUser             The employee of the task to be created.
     * @return
     */
    public Optional<Task> createTask(String reference, String description, String informalDescription,
                                     String technicalDescription, int duration, double cost,
                                     TaskCategory taskCategory, SystemUser systemUser) {

        //TODO: we could also check if the employee works for the organization before proceeding
        //checkIfEmployeeWorksForOrganization(employee);

        // When a Task is added, it should fail if the Task already exists in the list of Tasks.
        // In order to not return null if the operation fails, we use the Optional class.
        Optional<Task> optionalValue = Optional.empty();

        Task task = new Task(reference, description, informalDescription, technicalDescription, duration, cost,
                taskCategory, systemUser);

        if (addTask(task)) {
            optionalValue = Optional.of(task);
        }
        return optionalValue;
    }

    /**
     * This method adds a task to the list of tasks.
     *
     * @param task The task to be added.
     * @return True if the task was added successfully.
     */
    private boolean addTask(Task task) {
        boolean success = false;
        if (validate(task)) {
            // A clone of the task is added to the list of tasks, to avoid side effects and outside manipulation.
            success = tasks.add(task.clone());
        }
        return success;

    }

    /**
     * This method validates the task, checking for duplicates.
     *
     * @param task The task to be validated.
     * @return True if the task is valid.
     */
    private boolean validate(Task task) {
        return tasksDoNotContain(task);
    }

    /**
     * This method checks if the task is already in the list of tasks.
     *
     * @param task The task to be checked.
     * @return True if the task is not in the list of tasks.
     */
    private boolean tasksDoNotContain(Task task) {
        return !tasks.contains(task);
    }

    /**
     * This methos checks if the organization has an employee with the given email.
     *
     * @param email The email to be checked.
     * @return True if the organization has an employee with the given email.
     */
    public boolean anyEmployeeHasEmail(String email) {
        boolean result = false;
        for (SystemUser systemUser : systemUsers) {
            if (systemUser.hasEmail(email)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Organization)) {
            return false;
        }
        Organization that = (Organization) o;
        return vatNumber.equals(that.vatNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vatNumber);
    }

    //add employee to organization
    public boolean addEmployee(SystemUser systemUser) {
        boolean success = false;
        if (validateEmployee(systemUser)) {
            success = systemUsers.add(systemUser);
        }
        return success;
    }

    private boolean validateEmployee(SystemUser systemUser) {
        return employeesDoNotContain(systemUser);
    }

    private boolean employeesDoNotContain(SystemUser systemUser) {
        return !systemUsers.contains(systemUser);
    }

    //Clone organization
    public Organization clone() {
        Organization clone = new Organization(this.vatNumber);
        clone.name = (this.name);
        clone.website = (this.website);
        clone.phone = (this.phone);
        clone.email = (this.email);

        for (SystemUser in : this.systemUsers) {
            clone.systemUsers.add(in.clone());
        }


        for (Task in : this.tasks) {
            clone.tasks.add(in.clone());
        }

        return clone;
    }
}