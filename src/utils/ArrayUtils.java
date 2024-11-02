package utils;

import java.util.Random;

public class ArrayUtils {
    private static final Random RANDOM = new Random();

    // Method to get the maximum value in a double array
    public static double getMaxValue(double[] array) {
        double maxValue = Double.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }
        return maxValue;
    }

    // Overloaded method to get the maximum value in an int array
    public static int getMaxValue(int[] array) {
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }
        return maxValue;
    }

    public static int[] generateRandomArray(int size, int maxValue) {
        int[] array = new int[size];

        for (int i = 0; i < array.length; i++) {
            array[i] = RANDOM.nextInt(0, maxValue);
        }

        return array;
    }
}
