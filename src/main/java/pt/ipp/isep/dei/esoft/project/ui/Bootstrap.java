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
        organization.addEmployee(new SystemUser("hrm@hrm.app"));
        organization.addEmployee(new SystemUser("vfm@vfm.app"));
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

        authenticationRepository.addUserWithRole("João", "hrm@hrm.app", "a",
                AuthenticationController.ROLE_HRM);

        authenticationRepository.addUserWithRole("Mário", "vfm@vfm.app", "a",
                AuthenticationController.ROLE_VFM);
    }

    private void addCollaborators() {
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();

        var skills = Repositories.getInstance().getSkillRepository().getSkillList();

        var skills1 = new ArrayList<Skill>();
        skills1.add(skills.get(0));
        skills1.add(skills.get(1));

        collaboratorRepository.registerCollaborator("Marco", 913456123, "04-07-2001",
                "05-08-2020", "Rua das Aves", 123456789, new Job("Pintor"),
                DocumentTypeRepository.ID_CARD);

        var skills2 = new ArrayList<Skill>();
        skills2.add(skills.get(1));
        skills2.add(skills.get(2));

        collaboratorRepository.registerCollaborator("Ana", 987456765, "08-10-2002",
                "08-08-2021", "Rua das Aves", 789867899, new Job("Videografo"),
                DocumentTypeRepository.ID_CARD);
    }

    public void addJobs(){
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();
        jobRepository.registerJob("Park Ranger");
        jobRepository.registerJob("Landscape Architect");
        jobRepository.registerJob("Arborist");
        jobRepository.registerJob("Groundskeeper");
        jobRepository.registerJob("Horticulturist");
        jobRepository.registerJob("Botanist");
        jobRepository.registerJob("Ecologist");
        jobRepository.registerJob("Park Manager");
        jobRepository.registerJob("Urban Forester");
        jobRepository.registerJob("Natural Resource Manager");
        jobRepository.registerJob("Park Maintenance Supervisor");
        jobRepository.registerJob("Wildlife Biologist");
        jobRepository.registerJob("Environmental Educator");
        jobRepository.registerJob("Irrigation Specialist");
        jobRepository.registerJob("Greenhouse Manager");
        jobRepository.registerJob("Community Garden Coordinator");
        jobRepository.registerJob("Trail Steward");
        jobRepository.registerJob("Sustainability Coordinator");
        jobRepository.registerJob("Park Events Coordinator");
        jobRepository.registerJob("Conservation Technician");
    }

    public void addSkills(){
        SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();
        skillRepository.registerSkill("Plant Identification");
        skillRepository.registerSkill("Soil Management");
        skillRepository.registerSkill("Pruning and Trimming");
        skillRepository.registerSkill("Watering Techniques");
        skillRepository.registerSkill("Pest and Disease Management");
        skillRepository.registerSkill("Companion Planting");
        skillRepository.registerSkill("Garden Design");
        skillRepository.registerSkill("Propagation");
        skillRepository.registerSkill("Seasonal Gardening");
        skillRepository.registerSkill("Organic Gardening Practices");
        skillRepository.registerSkill("Harvesting and Storage");
        skillRepository.registerSkill("Tool Maintenance");
        skillRepository.registerSkill("Environmental Awareness");
        skillRepository.registerSkill("Botany");
        skillRepository.registerSkill("Garden Photography");
        skillRepository.registerSkill("Landscaping");
        skillRepository.registerSkill("Container Gardening");
        skillRepository.registerSkill("Herbalism");
        skillRepository.registerSkill("Community Engagement");
        skillRepository.registerSkill("Continuous Learning");
        skillRepository.registerSkill("Plant Health Diagnosis");
        skillRepository.registerSkill("Fertilization Techniques");
        skillRepository.registerSkill("Mulching and Weed Control");
        skillRepository.registerSkill("Seed Saving");
        skillRepository.registerSkill("Rainwater Harvesting");
        skillRepository.registerSkill("Wildlife Gardening");
        skillRepository.registerSkill("Indoor Plant Care");

    }
}