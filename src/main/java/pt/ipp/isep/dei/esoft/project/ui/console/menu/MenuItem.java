package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * Represents a menu item.
 */
public class MenuItem {
    private final String description;
    private final Runnable ui;

    /**
     * Initializes a new instance of MenuItem.
     *
     * @param description The description of the menu item.
     * @param ui          The UI associated with the menu item.
     * @throws IllegalArgumentException If description is null or empty, or if ui is null.
     */
    public MenuItem(String description, Runnable ui) {
        if (StringUtils.isBlank(description)) {
            throw new IllegalArgumentException("MenuItem description cannot be null or empty.");
        }
        if (Objects.isNull(ui)) {
            throw new IllegalArgumentException("MenuItem does not support a null UI.");
        }

        this.description = description;
        this.ui = ui;
    }

    /**
     * Runs the associated UI.
     */
    public void run() {
        this.ui.run();
    }

    /**
     * Checks if the menu item has the specified description.
     *
     * @param description The description to check.
     * @return True if the menu item has the specified description, false otherwise.
     */
    public boolean hasDescription(String description) {
        return this.description.equals(description);
    }

    /**
     * Returns the string representation of the menu item.
     *
     * @return The description of the menu item.
     */
    @Override
    public String toString() {
        return this.description;
    }
}
