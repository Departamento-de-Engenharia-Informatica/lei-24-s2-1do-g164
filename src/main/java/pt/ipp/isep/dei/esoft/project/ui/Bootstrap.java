package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;

public class Bootstrap implements Runnable {

    //Add some task categories to the repository as bootstrap
    public void run() {
        addTaskCategories();
        addOrganization();
        addUsers();
        addJobs();
        addSkills();
        addCollaborators();
    }

    private void addOrganization() {
        //TODO: add organizations bootstrap here
        //get organization repository
        OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();
        Organization organization = new Organization("This Company");
        organization.addEmployee(new SystemUser("admin@this.app"));
        organization.addEmployee(new SystemUser("employee@this.app"));
        organization.addEmployee(new SystemUser("t@t.tt"));
        organizationRepository.add(organization);
    }

    private void addTaskCategories() {
        //TODO: add bootstrap Task Categories here

        //get task category repository
        TaskCategoryRepository taskCategoryRepository = Repositories.getInstance().getTaskCategoryRepository();
        taskCategoryRepository.add(new TaskCategory("Analysis"));
        taskCategoryRepository.add(new TaskCategory("Design"));
        taskCategoryRepository.add(new TaskCategory("Implementation"));
        taskCategoryRepository.add(new TaskCategory("Development"));
        taskCategoryRepository.add(new TaskCategory("Testing"));
        taskCategoryRepository.add(new TaskCategory("Deployment"));
        taskCategoryRepository.add(new TaskCategory("Maintenance"));
    }

    private void addUsers() {
        //TODO: add Authentication users here: should be created for each user in the organization
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_EMPLOYEE,
                AuthenticationController.ROLE_EMPLOYEE);

        authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Tester Administrator", "t@t.tt", "t",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Employee", "employee@this.app", "pwd",
                AuthenticationController.ROLE_EMPLOYEE);
    }

    private void addCollaborators() {
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();

        var skills = Repositories.getInstance().getSkillRepository().getSkillList();

        var skills1 = new ArrayList<Skill>();
        skills1.add(skills.get(0));
        skills1.add(skills.get(1));

        collaboratorRepository.registerCollaborator("Marco", 913456123, "04/07/2001",
                "05/08/2020", "Rua das Aves", 123456789, new Job("Pintor"),
                DocumentTypeRepository.ID_CARD, skills1);

        var skills2 = new ArrayList<Skill>();
        skills2.add(skills.get(1));
        skills2.add(skills.get(2));

        collaboratorRepository.registerCollaborator("Ana", 987456765, "08/10/2002",
                "08/08/2021", "Rua das Aves", 789867899, new Job("Videografo"),
                DocumentTypeRepository.ID_CARD, skills2);
    }
    public void addSkills(){
        SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();
        skillRepository.registerSkill("Pintura");
        skillRepository.registerSkill("Cozinha");
        skillRepository.registerSkill("Videografia");
    }

    public void addJobs(){
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();
        jobRepository.registerJob("Mecanico");
        jobRepository.registerJob("caloiro");
        jobRepository.registerJob("barbeiro");
    }
}