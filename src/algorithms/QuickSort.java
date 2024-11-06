package algorithms;

import visualization.VisualizerController;

public class QuickSort extends SortingAlgorithm {
    @Override
    public void sort(int[] array, VisualizerController controller) {
        new Thread(() -> {
            try {
                quickSort(array, 0, array.length - 1, controller);
                controller.visualizeArray(array);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void quickSort(int[] array, int low, int high, VisualizerController controller) throws InterruptedException {
        if (low < high) {
            int pivotIndex = partition(array, low, high, controller);

            // Visualize the array after partitioning
            controller.visualizeArray(array, pivotIndex, -1, -1);
            Thread.sleep(sleepTime);

            quickSort(array, low, pivotIndex - 1, controller);
            quickSort(array, pivotIndex + 1, high, controller);
        }
    }

    private int partition(int[] array, int low, int high, VisualizerController controller) throws InterruptedException {
        int pivot = array[high];
        int i = low - 1;

        // Highlight the pivot element
        controller.visualizeArray(array, high, -1, -1);
        Thread.sleep(sleepTime);

        for (int j = low; j < high; j++) {
            // Highlight the current comparison index
            final int finalI = i;
            final int finalJ = j;
            controller.visualizeArray(array, high, finalJ, finalI);
            Thread.sleep(sleepTime);

            if (array[j] <= pivot) {
                i++;
                // Swap elements and update visualization
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;

                // Highlight the swap visually
                controller.visualizeArray(array, high, finalJ, i);
                Thread.sleep(sleepTime);
            }
        }

        // Final swap to place the pivot in the correct position
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        // Visualize the pivot in its final position
        controller.visualizeArray(array, i + 1, -1, -1);
        Thread.sleep(sleepTime);

        return i + 1;
    }
}
