package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.enums.EntryStatusENUM;
import pt.ipp.isep.dei.esoft.project.repository.enums.UrgencyDegreeENUM;

/**
 * The type To do entry dto.
 */
public class ToDoEntryDTO {

    /**
     * The Description.
     */
    public String description;
    /**
     * The Expected duration.
     */
    public int expectedDuration;
    /**
     * The Green space.
     */
    public GreenSpace greenSpace;
    /**
     * The Urgency degree.
     */
    public UrgencyDegreeENUM urgencyDegree;
    /**
     * The Entry status.
     */
    public EntryStatusENUM entryStatus;

    /**
     * Instantiates a new To do entry dto.
     *
     * @param description      the description
     * @param expectedDuration the expected duration
     * @param greenSpace       the green space
     * @param urgencyDegree    the urgency degree
     * @param entryStatus      the entry status
     */
    public ToDoEntryDTO(String description, int expectedDuration, GreenSpace greenSpace, UrgencyDegreeENUM urgencyDegree, EntryStatusENUM entryStatus) {

        this.description = description;
        this.expectedDuration = expectedDuration;
        this.greenSpace = greenSpace;
        this.urgencyDegree = urgencyDegree;
        this.entryStatus = entryStatus;
    }

    /**
     * Instantiates a new To do entry dto.
     *
     * @param description      the description
     * @param expectedDuration the expected duration
     * @param greenSpace       the green space
     * @param urgencyDegree    the urgency degree
     */
    public ToDoEntryDTO(String description, int expectedDuration, GreenSpace greenSpace, UrgencyDegreeENUM urgencyDegree) {

        this.description = description;
        this.expectedDuration = expectedDuration;
        this.greenSpace = greenSpace;
        this.urgencyDegree = urgencyDegree;
        this.entryStatus = EntryStatusENUM.PENDING;
    }

    public String toString() {
        return description + " - " +
                "Expected Duration: " + expectedDuration + " - " +
                "Status: " + entryStatus + " - " +
                "Green Space: " + greenSpace.getName() + " - " +
                "Urgency Degree: " + urgencyDegree;
    }

}
