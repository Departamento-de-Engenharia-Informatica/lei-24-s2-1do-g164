package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateTaskController;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.TaskCategory;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Manages the user interface for creating a task.
 */
public class CreateTaskUI implements Runnable {

    private final CreateTaskController controller;
    private String taskReference;
    private String taskDescription;
    private String taskInformalDescription;
    private String taskTechnicalDescription;
    private int taskDuration;
    private double taskCost;
    private String taskCategoryDescription;
    private String employeeEmail;

    /**
     * Constructs a new CreateTaskUI instance.
     */
    public CreateTaskUI() {
        controller = new CreateTaskController();
    }

    /**
     * Gets the controller.
     *
     * @return The controller.
     */
    private CreateTaskController getController() {
        return controller;
    }

    /**
     * Runs the CreateTaskUI.
     */
    @Override
    public void run() {
        System.out.println("\n\n--- Create Task ------------------------");

        taskCategoryDescription = displayAndSelectTaskCategory();

        requestData();

        submitData();
    }

    /**
     * Submits the task creation data.
     */
    private void submitData() {
        Optional<Task> task = getController().createTask(taskReference, taskDescription, taskInformalDescription, taskTechnicalDescription, taskDuration, taskCost, taskCategoryDescription);

        if (task.isPresent()) {
            System.out.println("\nTask successfully created!");
        } else {
            System.out.println("\nTask not created!");
        }
    }

    /**
     * Requests data from the user.
     */
    private void requestData() {

        // Request the Task Reference from the console
        taskReference = requestTaskReference();

        // Request the Task Description from the console
        taskDescription = requestTaskDescription();

        // Request the Task Informal Description from the console
        taskInformalDescription = requestTaskInformalDescription();

        // Request the Task Technical Description from the console
        taskTechnicalDescription = requestTaskTechnicalDescription();

        // Request the Task Duration from the console
        taskDuration = requestTaskDuration();

        // Request the Task Cost from the console
        taskCost = requestTaskCost();
    }

    /**
     * Requests the task cost from the user.
     *
     * @return The task cost.
     */
    private double requestTaskCost() {
        Scanner input = new Scanner(System.in);
        System.out.print("Task Cost: ");
        return input.nextDouble();
    }

    /**
     * Requests the task duration from the user.
     *
     * @return The task duration.
     */
    private int requestTaskDuration() {
        Scanner input = new Scanner(System.in);
        System.out.print("Task Duration: ");
        return input.nextInt();
    }

    /**
     * Requests the task technical description from the user.
     *
     * @return The task technical description.
     */
    private String requestTaskTechnicalDescription() {
        Scanner input = new Scanner(System.in);
        System.out.print("Task Technical Description: ");
        return input.nextLine();
    }

    /**
     * Requests the task informal description from the user.
     *
     * @return The task informal description.
     */
    private String requestTaskInformalDescription() {
        Scanner input = new Scanner(System.in);
        System.out.print("Task Informal Description: ");
        return input.nextLine();
    }

    /**
     * Requests the task description from the user.
     *
     * @return The task description.
     */
    private String requestTaskDescription() {
        Scanner input = new Scanner(System.in);
        System.out.print("Task Description: ");
        return input.nextLine();
    }

    /**
     * Requests the task reference from the user.
     *
     * @return The task reference.
     */
    private String requestTaskReference() {
        Scanner input = new Scanner(System.in);
        System.out.print("Task Reference: ");
        return input.nextLine();
    }

    /**
     * Displays the task categories and allows the user to select one.
     *
     * @return The selected task category description.
     */
    private String displayAndSelectTaskCategory() {
        // Display the list of task categories
        List<TaskCategory> taskCategories = controller.getTaskCategories();

        int listSize = taskCategories.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayTaskCategoryOptions(taskCategories);
            System.out.print("Select a task category: ");
            answer = input.nextInt();
        }

        return taskCategories.get(answer - 1).getDescription();
    }

    /**
     * Displays the task category options.
     *
     * @param taskCategories The list of task categories to display.
     */
    private void displayTaskCategoryOptions(List<TaskCategory> taskCategories) {
        // Display the task categories as a menu with number options to select
        int i = 1;
        for (TaskCategory taskCategory : taskCategories) {
            System.out.println("  " + i + " - " + taskCategory.getDescription());
            i++;
        }
    }
}
