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
    private Vehicle vehicle;

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


    @Test
    public void testHasVehicleToRegisterVehicleCheckUp() {
        repository.registerVehicle("Toyota", "Corolla", "AB-22-KL", VehicleTypeRepository.LIGHT_VEHICLE, 1500.0, 1200.0, 50000,
                "01-01-2022", "01-01-2022", 10000);
        vehicle = repository.getVehicleList().get(0);
        LocalDate date = LocalDate.of(2024, 4, 30);
        int currentKm = 50000;
        boolean result = repository.createVehicleCheckup(vehicle, date, currentKm);
        assertTrue(result);

    }

    @Test
    public void testNoVehiclesToRegisterVehicleCheckUp() {
        LocalDate date = LocalDate.of(2024, 4, 30);
        int currentKm = 50000;
        boolean result = repository.createVehicleCheckup(null, date, currentKm);
        assertFalse(result);

    }
}
