package pt.ipp.isep.dei.esoft.project.application.controller;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;



/**
 * Controller class for creating team proposals.
 */
public class CreateteamProposalController {

    /** Repositories for accessing skills, teams, and collaborators
     *
     */
    private SkillRepository skillRepository;
    private TeamRepository teamRepository;
    private CollaboratorRepository collaboratorRepository;

    /**
     * Constructs a CreateTeamProposalController and initializes repositories.
     */
    public CreateteamProposalController(){
        skillRepository = Repositories.getInstance().getSkillRepository();
        collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        teamRepository = Repositories.getInstance().getTeamRepository();

    }

    /**
     * Creates a team proposal based on specified criteria.
     *
     * @param max    The maximum number of team members allowed.
     * @param min    The minimum number of team members required.
     * @param skills The required skills for the team.
     * @return The created team proposal.
     * @throws InputMismatchException if there are not enough collaborators with the required skills
     *                                or if a team cannot be formed with the given criteria.
     */
    public Team createTeamProposal(int max, int min, ArrayList<Skill> skills){
        // ArrayList containing collaborators with at least one of the required skills
        var collaborators = new ArrayList<Collaborator>();

        //Retrieve collaborators with required skills
        for (var s : skills) {
            var collaboratorsBySkill = this.collaboratorRepository.getDeactivatedCollaboratorsBySkill(s);

            if (collaboratorsBySkill.size() == 0){
                var errorMessage = new StringBuilder();
                errorMessage.append("No collaborators found for skill ");
                errorMessage.append(s.getSkillName());

                throw new InputMismatchException(errorMessage.toString());
            }

            for (var c : collaboratorsBySkill){
                if (!collaborators.contains(c))
                    collaborators.add(c);
            }
        }
//Sort collaborators by the number of skills they possess
        Collections.sort(collaborators, new Comparator<Collaborator>() {
            @Override
            public int compare(Collaborator c1, Collaborator c2) {
                return Integer.compare(c1.getSkills().size(), c2.getSkills().size());
            }
        });
        // Initialize variables
        var teamMembers = new ArrayList<Collaborator>();
        var skillsCopy = skills;

        // Select team members based on skills and availability
        for(var c : collaborators){
            if (teamMembers.size() < max){
                for(var s : c.getSkills()){
                    if(skillsCopy.contains(s)){
                        skillsCopy.remove(s);
                        if(!teamMembers.contains(c)) {
                            teamMembers.add(c);
                        }
                    }
                }
            }
        }

        // Add additional members if necessary to meet minimum team size
        for (var c : collaborators){
            if (teamMembers.size() < min && teamMembers.size() < max){
                for(var s : c.getSkills()){
                    if(skills.contains(s)){
                        if(!teamMembers.contains(c)) {
                            teamMembers.add(c);
                        }
                    }
                }
            }
        }

        // Check if team meets minimum size requirement
        if (teamMembers.size() < min) {
            var errorMessage = new StringBuilder();
            errorMessage.append("Could not form a team with a minimum of ");
            errorMessage.append(min);

            throw new InputMismatchException(errorMessage.toString());
        }

        // Create team proposal and register it
        Team team = new Team(teamMembers, skills);
        if(teamRepository.registerTeam(team)){
            // Activate collaborators in the team
            for (var c : teamMembers) {
                c.activateCollaborator();
            }

            return team;
        } else {
            throw new InputMismatchException("Could not create team");
        }
    }
}
