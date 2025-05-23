package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type Task.
 */
public class Task implements Serializable {
    private final String reference;
    private String description;
    private String informalDescription;
    private String technicalDescription;
    private int duration;
    private double cost;

    private TaskCategory taskCategory;

    private SystemUser systemUser;

    /**
     * Instantiates a new Task.
     *
     * @param reference            the reference
     * @param description          the description
     * @param informalDescription  the informal description
     * @param technicalDescription the technical description
     * @param duration             the duration
     * @param cost                 the cost
     * @param taskCategory         the task category
     * @param systemUser           the system user
     */
    public Task(String reference, String description, String informalDescription, String technicalDescription,
                int duration, double cost, TaskCategory taskCategory, SystemUser systemUser) {

        validateReference(reference);
        this.reference = reference;
        this.description = description;
        this.informalDescription = informalDescription;
        this.technicalDescription = technicalDescription;
        this.duration = duration;
        this.cost = cost;
        this.taskCategory = taskCategory;
        this.systemUser = systemUser;
    }

    private void validateReference(String reference) {
        //TODO: missing from the diagrams
        if (reference == null || reference.isEmpty()) {
            throw new IllegalArgumentException("Reference cannot be null or empty.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return reference.equals(task.reference) && systemUser.equals(task.systemUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference, systemUser);
    }


    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public Task clone() {
        return new Task(this.reference, this.description, this.informalDescription, this.technicalDescription,
                this.duration, this.cost, this.taskCategory, this.systemUser);
    }
}