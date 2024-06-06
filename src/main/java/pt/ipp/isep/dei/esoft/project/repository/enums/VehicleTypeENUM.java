package pt.ipp.isep.dei.esoft.project.repository.enums;

/**
 * Enum representing types of vehicles used for vehicle registration.
 */
public enum VehicleTypeENUM {
    LIGHT_VEHICLE{
        @Override
        public String toString() {
            return "Light Vehicle";
        }
    }, // Represents a light vehicle
    HEAVY_VEHICLE{
        @Override
        public String toString() {
            return "Heavy Vehicle";
        }
    }, // Represents a heavy vehicle
    TRACTOR{
        @Override
        public String toString() {
            return "Tractor";
        }
    }        // Represents a tractor
}
