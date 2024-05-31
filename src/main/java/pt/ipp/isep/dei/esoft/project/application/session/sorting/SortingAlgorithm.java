package pt.ipp.isep.dei.esoft.project.application.session.sorting;

import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;

import java.util.Comparator;
import java.util.List;

public interface SortingAlgorithm extends Comparator<GreenSpaceDTO> {
    void sort(List<GreenSpaceDTO> list);
}
