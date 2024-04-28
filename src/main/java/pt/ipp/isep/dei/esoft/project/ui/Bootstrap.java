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
        addVehicles();
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
        SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();

        ArrayList<Skill> skills = skillRepository.getSkillList();


        collaboratorRepository.registerCollaborator("Marco", 913456123, "04-07-2001",
                "05-08-2020", "Rua das Aves", 12345678, new Job("Trail Steward"),
                DocumentTypeRepository.CITIZEN_CARD, CollaboratorStatus.DEACTIVATED, 123456789, "adeus@hotmail.pt");


        ArrayList<Skill> marcoSkills = new ArrayList<>();
        marcoSkills.add(skillRepository.getSkillList().get(0));
        marcoSkills.add(skillRepository.getSkillList().get(1));
        marcoSkills.add(skillRepository.getSkillList().get(3));
        marcoSkills.add(skillRepository.getSkillList().get(4));

        collaboratorRepository.assignSkills(collaboratorRepository.getCollaboratorList().get(0), marcoSkills);


        collaboratorRepository.registerCollaborator("Ana", 987456765, "08-10-2002",
                "08-08-2021", "Rua das Aves", 78986789, new Job("Arborist"),
                DocumentTypeRepository.CITIZEN_CARD, CollaboratorStatus.DEACTIVATED,123993123, "ola23@mial.com");


        ArrayList<Skill> anaSkills = new ArrayList<>();
        anaSkills.add(skillRepository.getSkillList().get(2));

        collaboratorRepository.assignSkills(collaboratorRepository.getCollaboratorList().get(1), anaSkills);

        collaboratorRepository.registerCollaborator("Ambrosio", 937996795, "08-10-2002",
                "08-08-2021", "Rua das Aves", 54946780, new Job("Park Ranger"),
                DocumentTypeRepository.CITIZEN_CARD, CollaboratorStatus.DEACTIVATED, 123123123, "ola@mial.com");


        ArrayList<Skill> ambrosioSkills = new ArrayList<>();
        ambrosioSkills.add(skillRepository.getSkillList().get(5));

        collaboratorRepository.assignSkills(collaboratorRepository.getCollaboratorList().get(2), ambrosioSkills);

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

    public void addVehicles() {
        VehicleRepository repo = Repositories.getInstance().getVehicleRepository();
        repo.registerVehicle("Mercedes", "Class A", "87-UI-28", VehicleTypeRepository.LIGHT_VEHICLE,
                1415, 1200, 25000, "02-07-2020", "12-09-2018", 20000);
        repo.registerVehicle("BMW", "i8", "57-OI-98", VehicleTypeRepository.LIGHT_VEHICLE,
                1215, 990, 90000, "12-12-2019", "10-10-2018", 40000);
        repo.registerVehicle("Ford", "Carrinha", "80-07-LX", VehicleTypeRepository.HEAVY_VEHICLE,
                9000, 8000, 200000, "25-12-2021", "25-12-2021", 300000);
    }
}