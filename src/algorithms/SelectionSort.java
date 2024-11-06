package algorithms;

import javafx.application.Platform;
import visualization.VisualizerController;

public class SelectionSort extends SortingAlgorithm {
    @Override
    public void sort(int[] array, VisualizerController controller) {
        new Thread(() -> {
            try {
                for (int i = 0; i < array.length - 1; i++) {
                    int minIdx = i;
                    for (int j = i + 1; j < array.length; j++) {
                        // Update visualization on each comparison
                        controller.visualizeArray(array, i, j, minIdx);
                        Thread.sleep(sleepTime);

                        if (array[j] < array[minIdx]) {
                            minIdx = j;
                            // Update visualization when new minimum is found
                            controller.visualizeArray(array, i, j, minIdx);
                            Thread.sleep(sleepTime);
                        }
                    }

                    // Swap the elements
                    int temp = array[minIdx];
                    array[minIdx] = array[i];
                    array[i] = temp;

                    // Visualize the swap
                    controller.visualizeArray(array);
                    Thread.sleep(sleepTime);
                }

                // Final pass to mark the array as fully sorted
                Platform.runLater(() -> controller.visualizeArray(array));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
