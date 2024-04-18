package pt.ipp.isep.dei.esoft.project.domain;

public class VehicleCheckup {
    private String date;
    private int currentKms;

    public VehicleCheckup(String date, int currentKms){
        this.date = date;
        this.currentKms = currentKms;
    }

    public String getDate() {
        return date;
    }

    public int getCurrentKms() {
        return currentKms;
    }
}

