package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.DocumentTypeRepository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RegisterCollaboratorUI implements Runnable{
    private final RegisterCollaboratorController controller;
    private String name;
    private int phone;
    private String birthdate;
    private String admissionDate;
    private String address;
    private Job job;
    private DocumentTypeRepository idDocumentType;
    private int idDocumentNumber;

    public RegisterCollaboratorUI(){
        controller = new RegisterCollaboratorController();
    }

    private RegisterCollaboratorController getController(){
        return controller;
    }
    public void run() {
        System.out.println("\n\n--- Register Collaborator ------------------------");

        job = displayAndSelectJob();
        idDocumentType = displayAndSelectDocumentType();

        requestData();
        submitData();

    }

    private void submitData() {
        boolean success = getController().registerCollaborator(name, phone, birthdate, admissionDate, address, idDocumentNumber, job, idDocumentType);
        if(success){
            System.out.println("\nCollaborator successfully registered!");
        }
        else{
            System.out.println("\nCollaborator registration failed!");
        }
    }

    private void requestData() {
        Scanner sc = new Scanner(System.in);

        this.idDocumentNumber = requestIdDocumentNumber();

        this.name = requestName();

        this.phone = requestPhone();

        this.birthdate = requestBirthdate();

        this.admissionDate = requestAdmissionDate();

        this.address = requestAddress();
    }


    private Job displayAndSelectJob(){
        ArrayList<Job> jobList = controller.getJobList();
        int listSize = jobList.size();
        int answer = -1;

        Scanner sc = new Scanner(System.in);

        while(answer < 1 || answer > listSize){
            displayJobList(jobList);
            System.out.println("Select the collaborator's job: ");
            answer = sc.nextInt();
        }

        Job job = jobList.get(answer - 1);
        return job;
    }

    private void displayJobList(ArrayList<Job> jobList){
        int i = 1;
        for (Job job : jobList){
            System.out.println("  " + i + " - " + job.getJobName());
            i++;
        }
    }

    private void displayDocumentTypeList(List<DocumentTypeRepository> documentTypeList){
        int i = 1;
        for (DocumentTypeRepository docType : documentTypeList){
            System.out.println("  " + i + " - " + docType);
            i++;
        }
    }

    private DocumentTypeRepository displayAndSelectDocumentType(){
        List<DocumentTypeRepository> docTypesList = controller.getDocTypesList();
        int listSize = docTypesList.size();
        int answer = -1;

        Scanner sc = new Scanner(System.in);

        while(answer < 1 || answer > listSize){
            displayDocumentTypeList(docTypesList);
            System.out.println("Select the collaborator's ID Document's type: ");
            answer = sc.nextInt();
        }

        DocumentTypeRepository docType = docTypesList.get(answer - 1);
        return docType;
    }

    private String requestName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Name (a-Z, no special characters or numbers): ");
        return sc.nextLine();
    }

    private int requestIdDocumentNumber() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter Document Number: ");
            return sc.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("Must be a number!");
            System.out.println("Enter Document Number: ");
            sc.nextLine();
            return sc.nextInt();
        }
    }

    private int requestPhone() {
        Scanner sc = new Scanner(System.in);
        try{
            System.out.println("Enter Phone Number (9 digits): ");
            return sc.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("Must be a number!");
            System.out.println("Enter Phone Number (9 digits): ");
            sc.nextLine();
            return sc.nextInt();
        }
    }

    private String requestBirthdate() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Birth Date (DD-MM-YY): ");
        return sc.nextLine();
    }

    private String requestAdmissionDate() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Admission Date (DD-MM-YY): ");
        return sc.nextLine();
    }

    private String requestAddress() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Address: ");
        return sc.nextLine();
    }
}

