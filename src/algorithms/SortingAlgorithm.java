package algorithms;

import visualization.VisualizerController;

public abstract class SortingAlgorithm {
    protected static int sleepTime = 10;

    // Abstract method for sorting an array
    public abstract void sort(int[] array, VisualizerController controller);

    // Setter to change sleepTime for all algorithms
    public static void setSleepTime(int newSleepTime) {
        sleepTime = newSleepTime;
    }

    // Getter to get sleepTime for all algorithms
    public static int getSleepTime() {
        return sleepTime;
    }
}
