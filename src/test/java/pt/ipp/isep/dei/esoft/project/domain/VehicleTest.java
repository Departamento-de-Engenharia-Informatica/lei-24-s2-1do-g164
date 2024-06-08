package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.repository.enums.VehicleTypeENUM;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {

    private static VehicleTypeENUM type = VehicleTypeENUM.LIGHT_VEHICLE;
    private Vehicle vehicle;

    private VehicleCheckup checkup1;

    @BeforeEach
    public void setUp() {
        vehicle = new Vehicle("Toyota", "Corolla", "ABC123", type, 1500.0, 1200.0, 10000, "01-01-2020", "01-01-2021", 5000);
        checkup1 = new VehicleCheckup(LocalDate.of(2024, 6, 1), 12000);    }

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
    public void testGetBrand() {
        assertEquals("Toyota", vehicle.getBrand());
    }

    @Test
    public void testGetModel() {
        assertEquals("Corolla", vehicle.getModel());
    }

    @Test
    public void testGetVehicleID() {
        assertEquals("ABC123", vehicle.getVehicleID());
    }

    @Test
    public void testGetType() {
        assertEquals(type, vehicle.getType());
    }

    @Test
    public void testGetGrossWeight() {
        assertEquals(1500.0, vehicle.getGrossWeight());
    }

    @Test
    public void testGetTare() {
        assertEquals(1200.0, vehicle.getTare());
    }

    @Test
    public void testGetCurrentKm() {
        assertEquals(10000, vehicle.getCurrentKm());
    }

    @Test
    public void testSetCurrentKm() {
        vehicle.setCurrentKm(15000);
        assertEquals(15000, vehicle.getCurrentKm());
    }

    @Test
    public void testGetRegisterDate() {
        assertEquals("01-01-2020", vehicle.getRegisterDate());
    }

    @Test
    public void testGetAcquisitionDate() {
        assertEquals("01-01-2021", vehicle.getAcquisitionDate());
    }

    @Test
    public void testGetCheckupFrequency() {
        assertEquals(5000, vehicle.getCheckupFrequency());
    }

    @Test
    public void testGetLastCheckup_WhenNoCheckupsExist() {
        assertNull(vehicle.getLastCheckup());
    }

    @Test
    public void testEquals_DifferentVehicleID() {
        Vehicle anotherVehicle = new Vehicle("Toyota", "Camry", "XYZ789", type, 1600.0, 1300.0, 12000, "01-01-2020", "01-01-2021", 5000);
        assertFalse(vehicle.equals(anotherVehicle));
    }
    @Test
    public void testSetDate() {
        LocalDate newDate = LocalDate.of(2024, 7, 1);
        checkup1.setDate(newDate);
        assertEquals(newDate, checkup1.getDate());
    }

    @Test
    public void testSetCurrentKms() {
        int newKms = 15000;
        checkup1.setCurrentKms(newKms);
        assertEquals(newKms, checkup1.getCurrentKms());
    }

    @Test
    public void testConstructor_ValidData() {
        LocalDate date = LocalDate.of(2024, 6, 1);
        int currentKms = 12000;
        VehicleCheckup newCheckup = new VehicleCheckup(date, currentKms);
        assertEquals(date, newCheckup.getDate());
        assertEquals(currentKms, newCheckup.getCurrentKms());
    }
    @Test
    public void testGetDate() {
        LocalDate expectedDate = LocalDate.of(2024, 6, 1);
        assertEquals(expectedDate, checkup1.getDate());
    }

    @Test
    public void testGetCurrentKms() {
        int expectedKms = 12000;
        assertEquals(expectedKms, checkup1.getCurrentKms());
    }

    @Test
    public void testToString() {
        String expectedString = "2024-06-01";
        assertEquals(expectedString, checkup1.toString());
    }
}
