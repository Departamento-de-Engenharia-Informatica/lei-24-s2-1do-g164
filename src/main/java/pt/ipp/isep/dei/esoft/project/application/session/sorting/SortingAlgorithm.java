package pt.ipp.isep.dei.esoft.project.application.session.sorting;

import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;

import java.util.Comparator;
import java.util.List;

/**
 * The interface Sorting algorithm.
 */
public interface SortingAlgorithm extends Comparator<GreenSpaceDTO> {
    /**
     * Sort.
     *
     * @param greenSpaceDTOList the greenSpaceDTOList
     */
    void sort(List<GreenSpaceDTO> greenSpaceDTOList);
}
