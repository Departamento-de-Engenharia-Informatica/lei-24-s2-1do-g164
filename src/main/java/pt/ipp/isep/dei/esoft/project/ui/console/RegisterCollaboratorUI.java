package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.DocumentTypeRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.HrmUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private int taxpayerNumber;
    private String email;

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
        boolean success = getController().registerCollaborator(name, phone, birthdate, admissionDate, address, idDocumentNumber, job, idDocumentType, taxpayerNumber, email);
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
        this.taxpayerNumber = requestTaxpayerNumber();
        this.name = requestName();
        this.email = requestEmail();
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

        displayJobList(jobList);
        int value;
        do {
            String in = Utils.readLineFromConsole("\nSelect a job: ");
            try {
                assert in != null;
                value = Integer.parseInt(in);
            } catch (NumberFormatException ex) {
                value = -1;
            }
        } while (value < 0 || value > listSize);

        if (value == 0) {
            redirectToHrmUI();
        }
        return jobList.get(value - 1);
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
        displayDocumentTypeList(docTypesList);
        int value;
        do {
            String in = Utils.readLineFromConsole("\nSelect a document type: ");
            try {
                assert in != null;
                value = Integer.parseInt(in);
            } catch (NumberFormatException ex) {
                value = -1;
            }
        } while (value < 0 || value > listSize);

        if (value == 0) {
            redirectToHrmUI();
        }

        return docTypesList.get(value - 1);
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
        String value;
        do {
            value = Utils.readLineFromConsole("Enter Name (a-Z, no special characters or numbers): ");
        } while (!isValidName(value));
        return value;
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

    private int requestTaxpayerNumber() {
        int value;
        do {
            String in = Utils.readLineFromConsole("Enter Tax Payer Number (9 digits): ");
            try {
                assert in != null;
                value = Integer.parseInt(in);
            } catch (NumberFormatException ex) {
                value = -1;
            }
        } while (!hasLessThanXdigits(value, 9) || value < 0);
        return value;
    }

    /**
     * Requests the collaborator's phone number from the user.
     *
     * @return The entered phone number.
     */
    private int requestPhone() {
        int phone;
        int value;
        do {
            String in = Utils.readLineFromConsole("Enter phone number (9 digits): ");
            try {
                assert in != null;
                value = Integer.parseInt(in);
            } catch (NumberFormatException ex) {
                value = -1;
            }
        } while (!hasLessThanXdigits(value, 9) || value < 0);
        return value;
    }

    private String requestEmail() {
        Scanner sc = new Scanner(System.in);
        String admissionDate;
        do{
            System.out.print("\nEnter E-mail: ");
            admissionDate = sc.nextLine();
        }while (!isValidEmailFormat(admissionDate));
        return admissionDate;
    }

    /**
     * Requests the collaborator's birthdate from the user.
     *
     * @return The entered birthdate.
     */
    private String requestBirthdate() {
        Scanner sc = new Scanner(System.in);
        String birthDate;
        do{
        System.out.print("\nEnter Birth Date (DD-MM-YYYY): ");
        birthDate = sc.nextLine();
        }while (!isValidDateFormat(birthDate));
        return birthDate;
    }

    /**
     * Requests the collaborator's admission date from the user.
     *
     * @return The entered admission date.
     */
    private String requestAdmissionDate() {
        Scanner sc = new Scanner(System.in);
        String admissionDate;
        do{
            System.out.print("\nEnter Admission Date (DD-MM-YYYY): ");
            admissionDate = sc.nextLine();
        }while (!isValidDateFormat(admissionDate));
        return admissionDate;
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

    private static boolean isValidDateFormat(String dateString) {
        String regex = "^(0[1-9]|[1-2][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4})$";
        Pattern p = Pattern.compile(regex);
        if (dateString == null) {
            return false;
        }
        Matcher m = p.matcher(dateString);
        if(!m.matches()){
            System.out.println("Invalid date format!");
        }
        return m.matches();
    }

    private static boolean isValidEmailFormat(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern p = Pattern.compile(regex);
        if (email == null) {
            return false;
        }
        Matcher m = p.matcher(email);
        if(!m.matches()){
            System.out.println("Invalid email format!");
        }
        return m.matches();
    }

    private static boolean hasLessThanXdigits(int phone, int n_digits) {
        String phoneS = String.valueOf(phone);
        String regex = "^\\d{" + n_digits + "}$";
        Pattern p = Pattern.compile(regex);
        if (phoneS == null){
            return false;
        }
        Matcher m = p.matcher(phoneS);
        if(!m.matches()){
            System.out.println("Invalid number format!");
        }
        return m.matches();
    }

    private static boolean isValidName(String name) {
        // Regex to check valid skill name (letters and spaces only).
        String regex = "^[a-zA-Z ]+$";
        Pattern p = Pattern.compile(regex);
        if (name == null) {
            return false;
        }
        Matcher m = p.matcher(name);
        if(!m.matches()){
            System.out.println("Invalid name!");
        }
        return m.matches();
    }


}
