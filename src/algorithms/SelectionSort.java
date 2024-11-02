package algorithms;

import visualization.VisualizerController;

public class SelectionSort extends SortingAlgorithm {
    @Override
    public void sort(int[] array, VisualizerController controller) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = array[minIdx];
            array[minIdx] = array[i];
            array[i] = temp;
        }
    }
}
