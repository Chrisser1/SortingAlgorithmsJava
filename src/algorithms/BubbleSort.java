package algorithms;

import visualization.VisualizerController;

public class BubbleSort extends SortingAlgorithm {
    @Override
    public void sort(int[] array, VisualizerController controller) {
        new Thread(() -> { // Run sorting on a separate thread
            try {
                for (int i = 0; i < array.length - 1; i++) {
                    for (int j = 0; j < array.length - i - 1; j++) {
                        // Update visualization
                        controller.visualizeArray(array, j, j+1);
                        Thread.sleep(sleepTime); // Slow down for visualization effect

                        if (array[j] > array[j + 1]) {
                            int temp = array[j];
                            array[j] = array[j + 1];
                            array[j + 1] = temp;

                        }
                    }
                }
                controller.visualizeArray(array);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
