package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.DocumentTypeRepository;

import java.util.ArrayList;
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

        this.idDocumentNumber = requestIdDocumentNumber(sc);

        this.name = requestName(sc);

        this.phone = requestPhone(sc);

        this.birthdate = requestBirthdate(sc);

        this.admissionDate = requestAdmissionDate(sc);

        this.address = requestAddress(sc);

        sc.close();
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

    private String requestName(Scanner input) {
        System.out.print("Enter Name: ");
        return input.nextLine();
    }

    private int requestIdDocumentNumber(Scanner input) {
        System.out.print("Enter ID Document Type: ");
        return input.nextInt();
    }

    private int requestPhone(Scanner input) {
        System.out.print("Enter Phone Number: ");
        return input.nextInt();
    }

    private String requestBirthdate(Scanner input) {
        System.out.print("Enter Birthdate (YYYY-MM-DD): ");
        return input.nextLine();
    }

    private String requestAdmissionDate(Scanner input) {
        System.out.print("Enter Admission Date (YYYY-MM-DD): ");
        return input.nextLine();
    }

    private String requestAddress(Scanner input) {
        System.out.print("Enter Address: ");
        return input.nextLine();
    }
}

