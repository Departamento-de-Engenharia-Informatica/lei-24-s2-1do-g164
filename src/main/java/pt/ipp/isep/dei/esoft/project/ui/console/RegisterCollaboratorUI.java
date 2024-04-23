package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.DocumentTypeRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.HrmUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * User interface for registering a new collaborator.
 */
public class RegisterCollaboratorUI implements Runnable {
    private final RegisterCollaboratorController controller;
    private String name;
    private int phone;
    private String birthdate;
    private String admissionDate;
    private String address;
    private Job job;
    private DocumentTypeRepository idDocumentType;
    private int idDocumentNumber;

    /**
     * Constructs a new RegisterCollaboratorUI instance.
     */
    public RegisterCollaboratorUI() {
        controller = new RegisterCollaboratorController();
    }

    private RegisterCollaboratorController getController() {
        return controller;
    }

    /**
     * Executes the user interface for registering a collaborator.
     */
    public void run() {
        System.out.println("\n\n--- Register Collaborator ------------------------");

        job = displayAndSelectJob();
        idDocumentType = displayAndSelectDocumentType();

        requestData();
        submitData();
    }

    /**
     * Submits the collected data to register a new collaborator.
     */
    private void submitData() {
        boolean success = getController().registerCollaborator(name, phone, birthdate, admissionDate, address, idDocumentNumber, job, idDocumentType);
        if (success) {
            System.out.println("\nCollaborator successfully registered!");
        } else {
            System.out.println("\nCollaborator registration failed!");
        }
    }

    /**
     * Requests input data from the user to populate collaborator information.
     */
    private void requestData() {
        Scanner sc = new Scanner(System.in);

        this.idDocumentNumber = requestIdDocumentNumber();
        this.name = requestName();
        this.phone = requestPhone();
        this.birthdate = requestBirthdate();
        this.admissionDate = requestAdmissionDate();
        this.address = requestAddress();
    }

    /**
     * Displays the list of available jobs and prompts the user to select one.
     *
     * @return The selected job.
     */
    private Job displayAndSelectJob() {
        ArrayList<Job> jobList = controller.getJobList();
        int listSize = jobList.size();
        int answer = -1;

        Scanner sc = new Scanner(System.in);

        while (answer < 0 || answer > listSize) {
            displayJobList(jobList);
            System.out.print("\nSelect the collaborator's job: ");
            answer = sc.nextInt();
        }

        if (answer == 0) {
            redirectToHrmUI();
        }
        return jobList.get(answer - 1);
    }

    /**
     * Displays a list of jobs to choose from.
     *
     * @param jobList The list of jobs to display.
     */
    private void displayJobList(ArrayList<Job> jobList) {
        int i = 1;
        System.out.println();
        for (Job job : jobList) {
            System.out.println("  " + i + " - " + job.getJobName());
            i++;
        }
        System.out.println("  0 - Cancel");
    }

    /**
     * Displays the list of available document types and prompts the user to select one.
     *
     * @return The selected document type.
     */
    private DocumentTypeRepository displayAndSelectDocumentType() {
        List<DocumentTypeRepository> docTypesList = controller.getDocTypesList();
        int listSize = docTypesList.size();
        int answer = -1;

        Scanner sc = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayDocumentTypeList(docTypesList);
            System.out.print("\nSelect the collaborator's ID Document's type: ");
            answer = sc.nextInt();
        }

        return docTypesList.get(answer - 1);
    }

    /**
     * Displays a list of document types to choose from.
     *
     * @param documentTypeList The list of document types to display.
     */
    private void displayDocumentTypeList(List<DocumentTypeRepository> documentTypeList) {
        int i = 1;
        System.out.println();
        for (DocumentTypeRepository docType : documentTypeList) {
            System.out.println("  " + i + " - " + docType);
            i++;
        }
    }

    /**
     * Requests the collaborator's name from the user.
     *
     * @return The entered name.
     */
    private String requestName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Name (a-Z, no special characters or numbers): ");
        return sc.nextLine();
    }

    /**
     * Requests the collaborator's ID document number from the user.
     *
     * @return The entered ID document number.
     */
    private int requestIdDocumentNumber() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("\nEnter Document Number: ");
            return sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("\nMust be a number!");
            sc.nextLine(); // Consume invalid input
            return requestIdDocumentNumber(); // Retry input
        }
    }

    /**
     * Requests the collaborator's phone number from the user.
     *
     * @return The entered phone number.
     */
    private int requestPhone() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("\nEnter Phone Number (9 digits): ");
            return sc.nextInt();

        } catch (InputMismatchException e) {
            System.out.print("\nMust be a number!");
            sc.nextLine(); // Consume invalid input
            return requestPhone(); // Retry input
        }
    }

    /**
     * Requests the collaborator's birthdate from the user.
     *
     * @return The entered birthdate.
     */
    private String requestBirthdate() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Birth Date (DD-MM-YYYY): ");
        return sc.nextLine();
    }

    /**
     * Requests the collaborator's admission date from the user.
     *
     * @return The entered admission date.
     */
    private String requestAdmissionDate() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Admission Date (DD-MM-YYYY): ");
        return sc.nextLine();
    }

    /**
     * Requests the collaborator's address from the user.
     *
     * @return The entered address.
     */
    private String requestAddress() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Address: ");
        return sc.nextLine();
    }

    /**
     * Redirects to the Human Resources Management UI.
     */
    private void redirectToHrmUI() {
        MenuItem item = new MenuItem(AuthenticationController.ROLE_HRM, new HrmUI());
        item.run();
    }

}
