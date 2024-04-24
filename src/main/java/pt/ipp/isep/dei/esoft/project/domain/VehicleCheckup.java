package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.Date;

public class VehicleCheckup {
    private Date date;
    private int currentKms;
    Vehicle vehicle;

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCurrentKms(int currentKms) {
        this.currentKms = currentKms;
    }



    public VehicleCheckup(Vehicle vehicle ,Date date, int currentKms){
        this.date = date;
        this.currentKms = currentKms;
    }

    public Date getDate() {
        return date;
    }

    public int getCurrentKms() {
        return currentKms;
    }


}

