package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.SystemUser;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.TaskCategory;
import pt.ipp.isep.dei.esoft.project.repository.*;

public class Bootstrap implements Runnable {

    //Add some task categories to the repository as bootstrap
    public void run() {
        addTaskCategories();
        addOrganization();
        addUsers();
        addJobs();
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
        authenticationRepository.addUserRole(AuthenticationController.ROLE_HRM, AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_VFM,
                AuthenticationController.ROLE_VFM);

        authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin",
                AuthenticationController.ROLE_HRM);

        authenticationRepository.addUserWithRole("HRM", "t@t.tt", "t",
                AuthenticationController.ROLE_HRM);

        authenticationRepository.addUserWithRole("VFM", "vfm@vfm.app", "pwd",
                AuthenticationController.ROLE_VFM);
    }

    public void addJobs(){
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();
        jobRepository.registerJob("Mecanico");
        jobRepository.registerJob("caloiro");
        jobRepository.registerJob("barbeiro");
    }
}