package visualization;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import utils.ArrayUtils;

public class VisualizerController {
    private HBox root;

    public VisualizerController(HBox root) {
        this.root = root;
    }

    // Consolidated visualizeArray method with optional indices for highlights
    public void visualizeArray(int[] array, int lookingAtIndex, int comparingToIndex, int minIdx) {
        double containerWidth = this.root.getWidth();
        double containerHeight = this.root.getHeight();

        // Avoid division by zero if container dimensions are not yet set
        if (containerWidth <= 0 || containerHeight <= 0) {
            return;
        }

        double totalSpacing = (array.length - 1) * 3;
        double adjustedWidth = containerWidth - totalSpacing;
        double barWidth = adjustedWidth / array.length;
        double maxValue = ArrayUtils.getMaxValue(array);

        Platform.runLater(() -> {
            root.getChildren().clear();

            for (int i = 0; i < array.length; i++) {
                int value = array[i];
                Rectangle bar = new Rectangle();
                bar.setWidth(barWidth);

                double scaledHeight = (value / maxValue) * containerHeight;
                bar.setHeight(scaledHeight);

                // Determine bar color based on highlight indices
                if (i == lookingAtIndex) {
                    bar.setFill(Color.YELLOW); // Current index being evaluated
                } else if (i == comparingToIndex) {
                    bar.setFill(Color.RED); // Currently comparing
                } else if (i == minIdx) {
                    bar.setFill(Color.GREEN); // Current minimum
                } else {
                    bar.setFill(Color.BLUE); // Default color
                }

                // Wrap bar in a VBox to make it grow upwards
                VBox barWrapper = new VBox(bar);
                barWrapper.setAlignment(Pos.BOTTOM_CENTER);

                root.getChildren().add(barWrapper);
            }
        });
    }

    // Overloaded methods to provide backward compatibility and ease of use
    public void visualizeArray(int[] array, int lookingAtIndex, int comparingToIndex) {
        visualizeArray(array, lookingAtIndex, comparingToIndex, -1);
    }

    public void visualizeArray(int[] array) {
        visualizeArray(array, -1, -1, -1);
    }
}
