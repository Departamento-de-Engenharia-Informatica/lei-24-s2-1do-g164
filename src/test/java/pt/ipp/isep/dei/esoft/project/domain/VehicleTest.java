package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.repository.VehicleTypeRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {

    private static VehicleTypeRepository type = VehicleTypeRepository.LIGHT_VEHICLE;
    private Vehicle vehicle;

    @BeforeEach
    public void setUp() {
        vehicle = new Vehicle("Toyota", "Corolla", "ABC123", type, 1500.0, 1200.0, 10000, "01-01-2020", "01-01-2021", 5000);
    }

    @Test
    public void testNeedsCheckup_NoPreviousCheckup() {
        assertTrue(vehicle.needsCheckup());
    }

    @Test
    public void testNeedsCheckup_WithPreviousCheckup() {
        VehicleCheckup checkup = new VehicleCheckup(LocalDate.of(2024, 6, 1), 12000);
        vehicle.addCheckup(checkup);
        assertFalse(vehicle.needsCheckup());
    }

    @Test
    public void testAddCheckup() {
        VehicleCheckup checkup = new VehicleCheckup(LocalDate.of(2024, 6, 1), 120000);
        vehicle.addCheckup(checkup);
        assertEquals(120000, vehicle.getCurrentKm());
        assertEquals(120000, vehicle.getLastCheckup().getCurrentKms());
    }

    @Test
    public void testEquals_SameVehicleID() {
        Vehicle anotherVehicle = new Vehicle("Toyota", "Camry", "ABC123", type, 1600.0, 1300.0, 12000, "01-01-2020", "01-01-2021", 5000);
        assertTrue(vehicle.equals(anotherVehicle));
    }

    @Test
    public void testEquals_DifferentVehicleID() {
        Vehicle anotherVehicle = new Vehicle("Toyota", "Camry", "XYZ789", type, 1600.0, 1300.0, 12000, "01-01-2020", "01-01-2021", 5000);
        assertFalse(vehicle.equals(anotherVehicle));
    }

}
