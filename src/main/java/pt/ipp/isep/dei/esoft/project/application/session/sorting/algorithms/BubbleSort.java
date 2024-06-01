package pt.ipp.isep.dei.esoft.project.application.session.sorting.algorithms;

import pt.ipp.isep.dei.esoft.project.application.session.sorting.SortingAlgorithm;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The type Bubble sort.
 */
public class BubbleSort implements SortingAlgorithm, Comparator<GreenSpaceDTO> {

    @Override
    public void sort(List<GreenSpaceDTO> greenSpaceDTOList) {
        // Get the size of the list
        int n = greenSpaceDTOList.size();
        // Create a boolean variable to check if the list is sorted
        boolean swapped;
        // Iterate through the list
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            // Iterate through the list
            for (int j = 0; j < n - i - 1; j++) {
                // Compare two elements
                if (compare(greenSpaceDTOList.get(j), greenSpaceDTOList.get(j + 1)) > 0) {
                    // Swap elements
                    Collections.swap(greenSpaceDTOList, j, j + 1);
                    swapped = true;
                }
            }

            // If no two elements were swapped in the inner loop, the array is already sorted
            if (!swapped) {
                break;
            }
        }
    }

    @Override
    public int compare(GreenSpaceDTO o1, GreenSpaceDTO o2) {
        return o2.area - o1.area;
    }
}
