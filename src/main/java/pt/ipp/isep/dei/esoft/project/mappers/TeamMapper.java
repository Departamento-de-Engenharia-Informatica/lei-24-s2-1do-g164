package pt.ipp.isep.dei.esoft.project.mappers;

import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.dto.TeamDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Team mapper.
 */
public class TeamMapper {

    /**
     * To dto team dto.
     *
     * @param team the team
     * @return the team dto
     */
    public static TeamDTO toDto(Team team) {
        List<String> collaboratorNames = new ArrayList<>();
        for (var collaborator : team.getCollaborators()) {
            collaboratorNames.add(collaborator.getName());
        }

        return new TeamDTO(collaboratorNames);
    }


    /**
     * To dto list list.
     *
     * @param teams the teams
     * @return the list
     */
    public static List<TeamDTO> toDtoList(ArrayList<Team> teams) {
        ArrayList<TeamDTO> teamDTOList = new ArrayList<>();
        for (var team : teams) {
            teamDTOList.add(toDto(team));
        }
        return teamDTOList;
    }
}
