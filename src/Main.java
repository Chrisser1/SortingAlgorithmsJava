// Algorithms
import algorithms.BubbleSort;
import algorithms.QuickSort;
import algorithms.SelectionSort;
import algorithms.SortingAlgorithm;
// JavaFX
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Slider;
// Utils
import utils.ArrayUtils;
import visualization.VisualizerController;

public class Main extends Application {
    private static VisualizerController controller;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 400;
    private static final int ARRAY_SIZE = 100;
    private int[] array = ArrayUtils.generateRandomArray(ARRAY_SIZE, 1000); // Generate initial array

    @Override
    public void start(Stage stage) {
        // Initialize the visualization container and controller
        HBox barContainer = new HBox(2); // 2px spacing between bars
        barContainer.setMinHeight(300); // Set minimum height for visualization area
        barContainer.setMinWidth(400);  // Set a reasonable minimum width
        barContainer.setMaxWidth(Double.MAX_VALUE); // Allow expansion but within limits

        controller = new VisualizerController(barContainer);

        // Set up the generate button and control area
        Button generateButton = new Button("Generate New Array");
        generateButton.setOnAction(e -> {
            array = ArrayUtils.generateRandomArray(ARRAY_SIZE, 1000); // Generate a new array of size 10
            controller.visualizeArray(array); // Update visualization with new array
        });

         // Set up the sorting method dropdown (ComboBox)
        ComboBox<SortingAlgorithm> sortingMethods = new ComboBox<>();
        sortingMethods.getItems().addAll(new BubbleSort(), new SelectionSort(), new QuickSort());
        sortingMethods.setValue(new BubbleSort()); // Default selection

        // Add event handler for sorting method selection
        Button sortButton = new Button("Sort Array");
        sortButton.setOnAction(e -> {
            SortingAlgorithm selectedAlgorithm = sortingMethods.getValue();
            selectedAlgorithm.sort(array, controller); // Sort the array using the selected algorithm
            controller.visualizeArray(array); // Update visualization after sorting
        });

        // Add a sleepTime changer
        Slider sleepTimeSlider = new Slider(1, 500, SortingAlgorithm.getSleepTime());
        sleepTimeSlider.setShowTickLabels(true);
        sleepTimeSlider.setShowTickMarks(true);

        // Listener to update sleepTime based on slider value
        sleepTimeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            SortingAlgorithm.setSleepTime(newValue.intValue()); // Update sleepTime dynamically
        });

        HBox controlPanel = new HBox(10); // 10px spacing between controls
        controlPanel.getChildren().addAll(generateButton, sortButton, sortingMethods, sleepTimeSlider);

        // Set up the main layout with VBox to include control panel and visualization area
        VBox root = new VBox(10); // 10px spacing between sections
        root.getChildren().addAll(controlPanel, barContainer);
        VBox.setVgrow(barContainer, Priority.ALWAYS); // Let barContainer expand

        // Set up the scene
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setTitle("Array Visualization");
        stage.setScene(scene);
        stage.show();

        // Delay initial visualization until layout is fully initialized
        Platform.runLater(() -> controller.visualizeArray(this.array));

        // Add listeners to handle resizing on barContainer specifically
        barContainer.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            Platform.runLater(() -> controller.visualizeArray(this.array));
        });

        barContainer.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            Platform.runLater(() -> controller.visualizeArray(this.array));
        });
    }

    public static VisualizerController getController() {
        return controller;
    }

    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}
