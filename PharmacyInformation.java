import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PharmacyInformation {
    private String patientId;

    public PharmacyInformation(String patientId, String lastName) {
        this.patientId = patientId;
    }

    public Scene getScene(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Labels for fields
        Label pharmacyLabel = new Label("Pharmacy:");
        Label addressLabel = new Label("Address:");

        // Text fields for editable information
        TextField pharmacyField = new TextField();
        TextField addressField = new TextField();

        // Read and populate fields with existing data
        readAndPopulateFields(pharmacyField, addressField);

        // Button to save changes
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            // Save changes to the file
            saveChanges(pharmacyField.getText(), addressField.getText());
        });

        // Layout setup
        GridPane gridPane = new GridPane();
        gridPane.addRow(0, pharmacyLabel, pharmacyField);
        gridPane.addRow(1, addressLabel, addressField);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(20, gridPane, saveButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(50));

        root.setCenter(vbox);

        return new Scene(root, 400, 300);
    }

    private void readAndPopulateFields(TextField pharmacyField, TextField addressField) {
        // Read data from file and populate fields
    }

    private void saveChanges(String pharmacy, String address) {
        // Save changes to the file
    }

    public Node getContent(Stage primaryStage) {
        return getScene(primaryStage).getRoot();
    }
}
