package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.util.InputMismatchException;

public class VehicleCheckup {
    private LocalDate date;
    private int currentKms;

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCurrentKms(int currentKms) {
        this.currentKms = currentKms;
    }

    public VehicleCheckup(LocalDate date, int currentKms) {
        if (date.isBefore(LocalDate.now())) {
            throw new InputMismatchException("Date provided for check-up should be after today's date");
        }

        if (currentKms < 0) {
            throw new InputMismatchException("Current Kms should be a positive value");
        }

        this.date = date;
        this.currentKms = currentKms;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getCurrentKms() {
        return currentKms;
    }
}

