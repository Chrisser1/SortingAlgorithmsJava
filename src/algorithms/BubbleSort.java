package algorithms;

import javafx.application.Platform;
import visualization.VisualizerController;

public class BubbleSort extends SortingAlgorithm {
    @Override
    public void sort(int[] array, VisualizerController controller) {
        new Thread(() -> { // Run sorting on a separate thread
            try {
                for (int i = 0; i < array.length - 1; i++) {
                    for (int j = 0; j < array.length - i - 1; j++) {
                        if (array[j] > array[j + 1]) {
                            int temp = array[j];
                            array[j] = array[j + 1];
                            array[j + 1] = temp;

                            // Update visualization on the JavaFX Application Thread
                            Platform.runLater(() -> controller.visualizeArray(array));
                            Thread.sleep(10); // Slow down for visualization effect
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
