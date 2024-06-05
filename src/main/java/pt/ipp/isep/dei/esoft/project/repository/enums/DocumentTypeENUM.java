package pt.ipp.isep.dei.esoft.project.repository.enums;

/**
 * Enum representing types of identification documents used for collaborator registration.
 */
public enum DocumentTypeENUM {
    PASSPORT{
        @Override
        public String toString() {
            return "Passport";
        }
    },
    CITIZEN_CARD{
        @Override
        public String toString() {
            return "Citizen Card";
        }
    },
    DRIVERS_LICENSE{
        @Override
        public String toString() {
            return "Driver's License";
        }
    }
}
