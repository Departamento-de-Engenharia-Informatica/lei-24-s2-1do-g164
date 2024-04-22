package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class SystemUser {
    private final String email;
    private String name;
    private String phone;

    public SystemUser(String email) {
        this.email = email;
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

    public boolean hasEmail(String email) {
        return this.email.equals(email);
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