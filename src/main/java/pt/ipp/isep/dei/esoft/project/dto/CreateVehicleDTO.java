package pt.ipp.isep.dei.esoft.project.dto;

/**
 * The type Create vehicle dto.
 */
public class CreateVehicleDTO {
    /**
     * The Brand.
     */
    public String brand;
    /**
     * The Model.
     */
    public String model;
    /**
     * The Vehicle id.
     */
    public String vehicleID;
    /**
     * The Gross weight.
     */
    public double grossWeight;
    /**
     * The Tare.
     */
    public double tare;
    /**
     * The Current km.
     */
    public int currentKm;
    /**
     * The Register date.
     */
    public String registerDate;
    /**
     * The Acquisition date.
     */
    public String acquisitionDate;
    /**
     * The Checkup frequency.
     */
    public int checkupFrequency;

    /**
     * Instantiates a new Create vehicle dto.
     *
     * @param brand            the brand
     * @param model            the model
     * @param vehicleID        the vehicle id
     * @param grossWeight      the gross weight
     * @param tare             the tare
     * @param currentKm        the current km
     * @param registerDate     the register date
     * @param acquisitionDate  the acquisition date
     * @param checkupFrequency the checkup frequency
     */
    public CreateVehicleDTO(String brand, String model, String vehicleID, double grossWeight, double tare, int currentKm, String registerDate, String acquisitionDate, int checkupFrequency) {
        this.brand = brand;
        this.model = model;
        this.vehicleID = vehicleID;
        this.grossWeight = grossWeight;
        this.tare = tare;
        this.currentKm = currentKm;
        this.registerDate = registerDate;
        this.acquisitionDate = acquisitionDate;
        this.checkupFrequency = checkupFrequency;
    }

    /**
     * Instantiates a new Create vehicle dto.
     */
    public CreateVehicleDTO() {
    }
}
