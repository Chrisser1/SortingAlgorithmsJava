package algorithms;

import visualization.VisualizerController;

public class QuickSort extends SortingAlgorithm {
    @Override
    public void sort(int[] array, VisualizerController controller) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int low, int high) {
        new Thread(() -> {
            try {
                if (low < high) {
                    int pivotIndex = partition(array, low, high);
                    quickSort(array, low, pivotIndex - 1);
                    quickSort(array, pivotIndex + 1, high);

                    Thread.sleep(sleepTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }
}
