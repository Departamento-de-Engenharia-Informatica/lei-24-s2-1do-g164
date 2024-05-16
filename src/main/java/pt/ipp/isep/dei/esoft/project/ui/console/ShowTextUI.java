package pt.ipp.isep.dei.esoft.project.ui.console;

import org.apache.commons.lang3.StringUtils;

/**
 * Represents a user interface for displaying text.
 */
public class ShowTextUI implements Runnable {

    private String text;

    /**
     * Constructs a new ShowTextUI instance with the provided text.
     *
     * @param text The text to be displayed.
     * @throws IllegalArgumentException If the provided text is null or empty.
     */
    public ShowTextUI(String text) {
        if (StringUtils.isBlank(text))
            throw new IllegalArgumentException("ShowTextUI does not support null or empty text");

        this.text = text;
    }

    /**
     * Runs the ShowTextUI, displaying the text.
     */
    public void run() {
        System.out.println("\n");
        System.out.println(this.text);
        System.out.println("\n");
    }
}
