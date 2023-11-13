package edu.sdccd.cisc191.src.main.java.edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

public class GroceryStoreApp extends Application {
    private GroceryStore2D store2D;

    // HashMaps to store prices for each item in Dairy and Snacks aisles
    private Map<String, Double> dairyPrices;
    private Map<String, Double> snacksPrices;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        store2D = new GroceryStore2D(3, 3); // Initial size of 3x3

        // Initializes prices for Dairy items
        dairyPrices = new HashMap<>();
        dairyPrices.put("Milk", 2.99);
        dairyPrices.put("Cheese", 4.99);
        dairyPrices.put("Yogurt", 1.99);

        // Initializes prices for Snacks items
        snacksPrices = new HashMap<>();
        snacksPrices.put("Chips", 1.99);
        snacksPrices.put("Cookies", 3.49);
        snacksPrices.put("Nuts", 5.99);

        primaryStage.setTitle("Grocery Store App");

        GridPane grid = createGrid();
        addComponents(grid);

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createGrid() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        return grid;
    }

    private void addComponents(GridPane grid) {
        ObservableList<String> aisleOptions = FXCollections.observableArrayList(
                "Dairy", "Snacks", "Bakery", "Produce"
        );

        // Aisle dropdown menu
        ComboBox<String> aisleDropdown = new ComboBox<>(aisleOptions);
        aisleDropdown.setPromptText("Select Aisle");
        grid.add(aisleDropdown, 0, 0);

        // Options ListView
        ListView<String> optionsListView = new ListView<>();
        grid.add(optionsListView, 0, 1);

        // Price label
        Label priceLabel = new Label("Price: ");
        grid.add(priceLabel, 0, 2);

        // Event handler for aisle selection
        aisleDropdown.setOnAction(event -> {
            String selectedAisle = aisleDropdown.getValue();
            updateOptionsAndPrice(selectedAisle, optionsListView, priceLabel);
        });
    }

    private void updateOptionsAndPrice(String aisle, ListView<String> optionsListView, Label priceLabel) {
        // Logic to update options and prices based on the selected aisle
        // Uses HashMap to get prices for each item
        String selectedAisle = aisle;
        if ("Dairy".equals(aisle)) {
            ObservableList<String> dairyOptions = FXCollections.observableArrayList(
                    "Milk", "Cheese", "Yogurt"
            );
            optionsListView.setItems(dairyOptions);
            priceLabel.setText("Price: "); // No default price, as it will be updated based on selection
        } else if ("Snacks".equals(aisle)) {
            ObservableList<String> snacksOptions = FXCollections.observableArrayList(
                    "Chips", "Cookies", "Nuts"
            );
            optionsListView.setItems(snacksOptions);
            priceLabel.setText("Price: "); // No default price, as it will be updated based on selection
        }

        // Event handler for option selection
        optionsListView.setOnMouseClicked(event -> {
            String selectedOption = optionsListView.getSelectionModel().getSelectedItem();
            if (selectedOption != null) {
                // Updates the price label based on the selected item and aisle
                double price = getPriceForItem(selectedAisle, selectedOption);
                priceLabel.setText("Price: $" + price);
            }
        });
    }

    private double getPriceForItem(String aisle, String item) {
        // Gets the price from the appropriate HashMap based on the selected aisle
        if ("Dairy".equals(aisle)) {
            return dairyPrices.getOrDefault(item, 0.0);
        } else if ("Snacks".equals(aisle)) {
            return snacksPrices.getOrDefault(item, 0.0);
        }
        return 0.0; // Default price if aisle is not recognized
    }
}