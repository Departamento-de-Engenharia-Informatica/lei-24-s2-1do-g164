package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateteamProposalController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import java.util.ArrayList;

public class CreateTeamProposalUI implements Runnable{
    private final CreateteamProposalController controller;
    private final SkillRepository skillRepository;

    public CreateTeamProposalUI() {
        controller = new CreateteamProposalController();
        skillRepository = Repositories.getInstance().getSkillRepository();
    }


    @Override
    public void run() {
        System.out.println("create team proposal hehehehehe");
        var skills = skillRepository.getSkillList();

        var skills2 = new ArrayList<Skill>();
        skills2.add(skills.get(0));
        skills2.add(skills.get(1));

        controller.createTeamProposal(5,1,skills2);
    }
}
