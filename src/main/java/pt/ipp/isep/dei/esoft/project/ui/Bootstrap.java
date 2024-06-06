package pt.ipp.isep.dei.esoft.project.ui;


import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.repository.enums.*;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The type Bootstrap.
 */
public class Bootstrap implements Runnable {

    public void run() {
        addOrganization();
        addUsers();
        addJobs();
        addSkills();
        addCollaborators();
        addVehicles();
        addGreenSpaces();
        addTeams();
        addEntries();
    }

    private void addOrganization() {
        //TODO: add organizations bootstrap here
        //get organization repository
        OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();
        Organization organization = new Organization("MusgoSublime");
        organization.addEmployee(new SystemUser("hrm@hrm.app"));
        organization.addEmployee(new SystemUser("vfm@vfm.app"));
        organizationRepository.add(organization);
    }

    private void addUsers() {
        //TODO: add Authentication users here: should be created for each user in the organization
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_HRM, AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_VFM, AuthenticationController.ROLE_VFM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_GSM, AuthenticationController.ROLE_GSM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_COL, AuthenticationController.ROLE_COL);

        authenticationRepository.addUserWithRole("João", "hrm@hrm.app", "JOAo1234_",
                AuthenticationController.ROLE_HRM);

        authenticationRepository.addUserWithRole("Mário", "vfm@vfm.app", "MARio2236",
                AuthenticationController.ROLE_VFM);

        authenticationRepository.addUserWithRole("Leonor", "gsm@gsm.app", "LEONOr1852",
                AuthenticationController.ROLE_GSM);

        authenticationRepository.addUserWithRole("Vasco", "gsm2@gsm2.app", "VASco3344",
                AuthenticationController.ROLE_GSM);

        authenticationRepository.addUserWithRole("Diogo", "col@col.app", "DIOGo1122",
                AuthenticationController.ROLE_COL);

    }

    private void addCollaborators() {
        RegisterCollaboratorController controller = new RegisterCollaboratorController();
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();

        ArrayList<Skill> skills = skillRepository.getSkillList();


        controller.registerCollaborator("Marco", 913456123, "04-07-2001",
                "05-08-2020", "Rua das Aves", 12845678, new Job("Trail Steward"),
                DocumentTypeENUM.CITIZEN_CARD,123456789, "marco2001@hotmail.com");


        ArrayList<Skill> marcoSkills = new ArrayList<>();
        marcoSkills.add(skillRepository.getSkillList().get(0));
        marcoSkills.add(skillRepository.getSkillList().get(1));
        marcoSkills.add(skillRepository.getSkillList().get(3));
        marcoSkills.add(skillRepository.getSkillList().get(4));

        collaboratorRepository.assignSkills(collaboratorRepository.getCollaboratorList().get(0), marcoSkills);


        controller.registerCollaborator("Ana", 987456765, "08-10-2002",
                "08-08-2021", "Rua das Aves", 78986789, new Job("Arborist"),
                DocumentTypeENUM.CITIZEN_CARD, 123993123, "ana23@gmail.com");


        ArrayList<Skill> anaSkills = new ArrayList<>();
        anaSkills.add(skillRepository.getSkillList().get(2));

        collaboratorRepository.assignSkills(collaboratorRepository.getCollaboratorList().get(1), anaSkills);

        controller.registerCollaborator("Ambrosio", 937996795, "08-10-2002",
                "08-08-2021", "Avenida da republica", 54946780, new Job("Park Ranger"),
                DocumentTypeENUM.CITIZEN_CARD, 123883123, "abrosio@gmail.com");


        ArrayList<Skill> ambrosioSkills = new ArrayList<>();
        ambrosioSkills.add(skillRepository.getSkillList().get(5));
        ambrosioSkills.add(skillRepository.getSkillList().get(2));

        collaboratorRepository.assignSkills(collaboratorRepository.getCollaboratorList().get(2), ambrosioSkills);

        controller.registerCollaborator("Rita", 917996795, "08-10-2002",
                "08-08-2021", "Rua das Aves", 89087650, new Job("Botanist"),
                DocumentTypeENUM.CITIZEN_CARD, 123103123, "rita@hotmail.com");

        ArrayList<Skill> ritaSkills = new ArrayList<>();
        ritaSkills.add(skillRepository.getSkillList().get(5));
        ritaSkills.add(skillRepository.getSkillList().get(6));
        ritaSkills.add(skillRepository.getSkillList().get(7));
        ritaSkills.add(skillRepository.getSkillList().get(8));

        collaboratorRepository.assignSkills(collaboratorRepository.getCollaboratorList().get(3), ritaSkills);

        controller.registerCollaborator("Afonso", 993056123, "04-07-2009",
                "05-08-2020", "Avenida 31", 19735678, new Job("Trail Steward"),
                DocumentTypeENUM.CITIZEN_CARD, 199256789, "afonso@hotmail.pt");

        ArrayList<Skill> afonsoSkills = new ArrayList<>();
        afonsoSkills.add(skillRepository.getSkillList().get(8));
        afonsoSkills.add(skillRepository.getSkillList().get(9));
        afonsoSkills.add(skillRepository.getSkillList().get(10));
        collaboratorRepository.assignSkills(collaboratorRepository.getCollaboratorList().get(4), afonsoSkills);


        controller.registerCollaborator("Diogo", 903059123, "04-07-2003",
                "05-08-2020", "Rua jose", 19738878, new Job("Trail Steward"),
                DocumentTypeENUM.CITIZEN_CARD, 199256889, "diogo@gmail.com");

        ArrayList<Skill> diogoSkills = new ArrayList<>();
        diogoSkills.add(skillRepository.getSkillList().get(11));
        diogoSkills.add(skillRepository.getSkillList().get(8));
        collaboratorRepository.assignSkills(collaboratorRepository.getCollaboratorList().get(5), diogoSkills);


        controller.registerCollaborator("Gabi", 903059123, "04-07-1990",
                "05-08-2020", "Rua crazy", 19032878, new Job("Trail Steward"),
                DocumentTypeENUM.CITIZEN_CARD, 190956889, "gabixux@gmail.com");

        ArrayList<Skill> gabiSkills = new ArrayList<>();
        gabiSkills.add(skillRepository.getSkillList().get(12));
        collaboratorRepository.assignSkills(collaboratorRepository.getCollaboratorList().get(6), gabiSkills);

        controller.registerCollaborator("Leonor", 993456183, "04-07-2001",
                "05-09-2020", "Rua feliz", 87645678, new Job("Trail Steward"),
                DocumentTypeENUM.CITIZEN_CARD, 199056789, "leonor@gmail.com");


        ArrayList<Skill> leonorskills = new ArrayList<>();
        leonorskills.add(skillRepository.getSkillList().get(7));
        leonorskills.add(skillRepository.getSkillList().get(10));


        collaboratorRepository.assignSkills(collaboratorRepository.getCollaboratorList().get(7), leonorskills);

        controller.registerCollaborator("Dinis", 987459183, "04-07-2001",
                "05-09-2020", "Rua feliz", 89045678, new Job("Trail Steward"),
                DocumentTypeENUM.CITIZEN_CARD, 139354799, "dinis@gmail.com");


        ArrayList<Skill> dinis = new ArrayList<>();

        dinis.add(skillRepository.getSkillList().get(13));


        collaboratorRepository.assignSkills(collaboratorRepository.getCollaboratorList().get(8), dinis);


        controller.registerCollaborator("Sandra", 937339133, "04-07-2001",
                "05-09-2020", "Rua feliz", 87855678, new Job("Trail Steward"),
                DocumentTypeENUM.CITIZEN_CARD, 1342654799, "sandra@gmail.com");


        ArrayList<Skill> sandra = new ArrayList<>();

        sandra.add(skillRepository.getSkillList().get(12));
        sandra.add(skillRepository.getSkillList().get(13));


        collaboratorRepository.assignSkills(collaboratorRepository.getCollaboratorList().get(9), sandra);




    }

    /**
     * Add jobs.
     */
    public void addJobs() {
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

    /**
     * Add skills.
     */
    public void addSkills() {
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

    /**
     * Add vehicles.
     */
    public void addVehicles() {
        VehicleRepository repo = Repositories.getInstance().getVehicleRepository();
        repo.registerVehicle("Mercedes", "Class A", "87-UI-28", VehicleTypeENUM.LIGHT_VEHICLE,
                1415, 1200, 25000, "02-07-2020", "12-09-2018", 20000);
        repo.registerVehicle("BMW", "i8", "57-OI-98", VehicleTypeENUM.LIGHT_VEHICLE,
                1215, 990, 90000, "12-12-2019", "10-10-2018", 40000);
        repo.registerVehicle("Ford", "Carrinha", "80-07-LX", VehicleTypeENUM.HEAVY_VEHICLE,
                9000, 8000, 200000, "25-12-2021", "25-12-2021", 300000);
    }

    /**
     * Add green spaces.
     */
    public void addGreenSpaces() {
        GreenSpaceRepository repo = Repositories.getInstance().getGreenSpaceRepository();
        repo.registerGreenSpace(new GreenSpace(GreenSpaceTypeENUM.LARGE_SIZED_PARK, "Cidade", "Av. Menéres", 123, "gsm@gsm.app"));
        repo.registerGreenSpace(new GreenSpace(GreenSpaceTypeENUM.MEDIUM_SIZED_PARK, "Covelo", "Av. Feliz 123", 50, "gsm@gsm.app"));
        repo.registerGreenSpace(new GreenSpace(GreenSpaceTypeENUM.GARDEN, "São Roque", "Rua Triste 22", 3, "gsm2@gsm2.app"));
    }


    /**
     * Add teams.
     */
    public void addTeams() {

        TeamRepository teamRepository = Repositories.getInstance().getTeamRepository();
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        Collaborator c1= collaboratorRepository.getCollaboratorList().get(3);
        Collaborator c2 = collaboratorRepository.getCollaboratorList().get(4);
        ArrayList<Collaborator> collaborators1= new ArrayList<>();
        collaborators1.add(c1);
        collaborators1.add(c2);
        Collaborator c3= collaboratorRepository.getCollaboratorList().get(5);
        Collaborator c4= collaboratorRepository.getCollaboratorList().get(6);
        Collaborator c5= collaboratorRepository.getCollaboratorList().get(7);
        Collaborator c6= collaboratorRepository.getCollaboratorList().get(8);
        Collaborator c7= collaboratorRepository.getCollaboratorList().get(9);



        ArrayList<Collaborator> collaborators2= new ArrayList<>();
        collaborators2.add(c3);
        collaborators2.add(c4);
        ArrayList<Collaborator> collaborators3= new ArrayList<>();
        collaborators3.add(c5);
        collaborators3.add(c6);
        collaborators3.add(c7);

        Team team1 = new Team(collaborators1, c1.getSkills(),TeamStatusENUM.PENDING);
        teamRepository.registerTeam(team1);
        Team team2= new Team(collaborators2, c2.getSkills(), TeamStatusENUM.PENDING);
        teamRepository.registerTeam(team2);
        Team team3= new Team(collaborators3, c5.getSkills(), TeamStatusENUM.PENDING);
        teamRepository.registerTeam(team3);

    }

    /**
     * Add entries.
     */
    public void addEntries() {

    }

}