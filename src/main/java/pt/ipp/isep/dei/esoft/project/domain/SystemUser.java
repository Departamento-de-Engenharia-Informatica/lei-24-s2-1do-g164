package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 * Represents a system user.
 */
public class SystemUser {
    private final String email;
    private String name;
    private String phone;

    /**
     * Initializes a new instance of SystemUser.
     *
     * @param email The email of the user.
     */
    public SystemUser(String email) {
        this.email = email;
    }

    /**
     * Checks if this user has the specified email.
     *
     * @param email The email to check.
     * @return True if the user has the specified email, false otherwise.
     */
    public boolean hasEmail(String email) {
        return this.email.equals(email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SystemUser)) {
            return false;
        }
        SystemUser systemUser = (SystemUser) o;
        return email.equals(systemUser.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public SystemUser clone() {
        return new SystemUser(this.email);
    }
}
