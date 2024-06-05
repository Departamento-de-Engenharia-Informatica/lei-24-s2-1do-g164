package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.InputMismatchException;

/**
 * The VehicleCheckup class represents a checkup performed on a vehicle.
 * It stores the date of the checkup and the current kilometers of the vehicle at that time.
 */
public class VehicleCheckup implements Serializable {
    private LocalDate date;
    private int currentKms;

    /**
     * Sets the date of the checkup.
     *
     * @param date The date of the checkup to be set.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Sets the current kilometers of the vehicle at the time of the checkup.
     *
     * @param currentKms The current kilometers to be set.
     */
    public void setCurrentKms(int currentKms) {
        this.currentKms = currentKms;
    }

    /**
     * Constructs a VehicleCheckup object with the specified date and current kilometers.
     *
     * @param date       The date of the checkup.
     * @param currentKms The current kilometers of the vehicle at the time of the checkup.
     * @throws InputMismatchException If the provided date is before today's date or if the current kilometers are negative.
     */
    public VehicleCheckup(LocalDate date, int currentKms) {
        if (currentKms < 0) {
            throw new InputMismatchException("Current Kms should be a positive value");
        }

        this.date = date;
        this.currentKms = currentKms;
    }

    /**
     * Retrieves the date of the checkup.
     *
     * @return The date of the checkup.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Retrieves the current kilometers of the vehicle at the time of the checkup.
     *
     * @return The current kilometers of the vehicle.
     */
    public int getCurrentKms() {
        return currentKms;
    }


    public String toString() {
        return String.valueOf(date);
    }
}

