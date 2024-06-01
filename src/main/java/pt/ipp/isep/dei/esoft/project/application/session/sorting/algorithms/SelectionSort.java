package pt.ipp.isep.dei.esoft.project.application.session.sorting.algorithms;

import pt.ipp.isep.dei.esoft.project.application.session.sorting.SortingAlgorithm;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;

import java.util.List;

/**
 * The type Selection sort.
 */
public class SelectionSort implements SortingAlgorithm {
    @Override
    public void sort(List<GreenSpaceDTO> greenSpaceDTOList) {
        int n = greenSpaceDTOList.size();
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            // Find the minimum element in unsorted array
            for (int j = i + 1; j < n; j++) {
                if (compare(greenSpaceDTOList.get(j), greenSpaceDTOList.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first
            if (minIndex != i) {
                GreenSpaceDTO temp = greenSpaceDTOList.get(i);
                greenSpaceDTOList.set(i, greenSpaceDTOList.get(minIndex));
                greenSpaceDTOList.set(minIndex, temp);
            }
        }

    }

    @Override
    public int compare(GreenSpaceDTO o1, GreenSpaceDTO o2) {
        return o2.area - o1.area;
    }
}
