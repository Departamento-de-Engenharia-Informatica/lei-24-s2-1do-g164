package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class VehicleRepositoryTest {
    private VehicleRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new VehicleRepository();
    }

    @Test
    public void testRegisterVehicle() {
        assertTrue(repository.registerVehicle("Toyota", "Corolla", "AB-11-EE", VehicleTypeRepository.TRACTOR, 1500.0, 1200.0, 50000,
                "01-01-2022", "01-01-2022", 10000));
        assertEquals(1, repository.size());
    }

    @Test
    public void testRegisterDuplicateVehicle() {
        repository.registerVehicle("Toyota", "Corolla", "AB-22-KL", VehicleTypeRepository.TRACTOR, 1500.0, 1200.0, 50000,
                "01-01-2022", "01-01-2022", 10000);
        assertFalse(repository.registerVehicle("Toyota", "Corolla", "AB-22-KL", VehicleTypeRepository.TRACTOR, 1500.0, 1200.0, 50000,
                "01-01-2022", "01-01-2022", 10000));
        assertEquals(1, repository.size());
    }

    @Test
    public void testGetVehiclesNeedingCheckup() {
        repository.registerVehicle("Toyota", "Corolla", "AB-23-KL", VehicleTypeRepository.LIGHT_VEHICLE, 1500.0, 1200.0, 50000,
                "01-01-2022", "01-01-2022", 10000);
        repository.registerVehicle("Ford", "Fiesta", "AB-22-KL", VehicleTypeRepository.HEAVY_VEHICLE, 1300.0, 1100.0, 35000,
                "01-02-2022", "01-02-2022", 8000);

        ArrayList<Vehicle> vehiclesNeedingCheckup = repository.getVehiclesNeedingCheckup();
        assertEquals(2, vehiclesNeedingCheckup.size());
    }

    @Test
    public void testGetVehiclesWithoutBookedCheckup() {
        repository.registerVehicle("Toyota", "Corolla", "AB-22-KL", VehicleTypeRepository.LIGHT_VEHICLE, 1500.0, 1200.0, 50000,
                "01-01-2022", "01-01-2022", 10000);
        repository.registerVehicle("Ford", "Fiesta", "AB-12-KL", VehicleTypeRepository.TRACTOR, 1300.0, 1100.0, 35000,
                "01-02-2022", "01-02-2022", 8000);
        repository.createVehicleCheckup(repository.getVehicleList().get(1), LocalDate.now(), 40000);
        ArrayList<Vehicle> vehiclesWithoutBookedCheckup = repository.getVehiclesWithoutBookedCheckup();
        assertEquals(1, vehiclesWithoutBookedCheckup.size());
    }

    // Add more tests for other methods if needed
}
