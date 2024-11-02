package visualization;

import javafx.application.Platform;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import utils.ArrayUtils;

public class VisualizerController {
    private HBox root;

    public VisualizerController(HBox root) {
        this.root = root;
    }

    public void visualizeArray(int[] array) {
        double containerWidth = this.root.getWidth(); // Get current width
        double containerHeight = this.root.getHeight(); // Get current height

        // Avoid division by zero if container dimensions are not yet set
        if (containerWidth <= 0 || containerHeight <= 0) {
            return;
        }

        // Calculate total spacing between bars
        double totalSpacing = (array.length - 1) * 3;

        // Adjust container width by subtracting total spacing
        double adjustedWidth = containerWidth - totalSpacing;

        // Calculate exact width for each bar
        double barWidth = adjustedWidth / array.length;
        double maxValue = ArrayUtils.getMaxValue(array); // Get maximum value in the array

        // Ensure updates happen on the JavaFX Application Thread
        Platform.runLater(() -> {
            root.getChildren().clear(); // Clear existing bars

            for (int value : array) {
                Rectangle bar = new Rectangle();
                bar.setWidth(barWidth);

                // Scale height of each bar relative to the maximum value in the array
                double scaledHeight = (value / maxValue) * containerHeight;
                bar.setHeight(scaledHeight);

                bar.setFill(Color.BLUE);
                root.getChildren().add(bar);
            }
        });
    }
}
