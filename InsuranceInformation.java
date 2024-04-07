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

public class InsuranceInformation {
    private String patientId;

    public InsuranceInformation(String patientId, String lastName) {
        this.patientId = patientId;
    }

    public Scene getScene(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Labels for fields
        Label insuranceIdLabel = new Label("Insurance ID:");
        Label phoneNumberLabel = new Label("Phone number:");

        // Text fields for editable information
        TextField insuranceIdField = new TextField();
        TextField phoneNumberField = new TextField();

        // Read and populate fields with existing data
        readAndPopulateFields(insuranceIdField, phoneNumberField);

        // Button to save changes
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            // Save changes to the file
            saveChanges(insuranceIdField.getText(), phoneNumberField.getText());
        });

        // Layout setup
        GridPane gridPane = new GridPane();
        gridPane.addRow(0, insuranceIdLabel, insuranceIdField);
        gridPane.addRow(1, phoneNumberLabel, phoneNumberField);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(20, gridPane, saveButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(50));

        root.setCenter(vbox);

        return new Scene(root, 400, 300);
    }

    private void readAndPopulateFields(TextField insuranceIdField, TextField phoneNumberField) {
        File infoFile = new File("/Users/apple/cse240/360/HW4/patient_info/", patientId + "_PatientInfo.txt");
        if (infoFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(infoFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Insurance ID:")) {
                        insuranceIdField.setText(line.substring(line.indexOf(":") + 2));
                    } else if (line.startsWith("Phone:")) {
                        phoneNumberField.setText(line.substring(line.indexOf(":") + 2));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveChanges(String insuranceId, String phoneNumber) {
        File infoFile = new File("/Users/apple/cse240/360/HW4/patient_info/", patientId + "_PatientInfo.txt");
        if (infoFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(infoFile))) {
                StringBuilder fileContent = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Insurance ID:")) {
                        // Replace the existing insurance ID with the updated insurance ID
                        fileContent.append("Insurance ID: ").append(insuranceId).append("\n");
                    } else if (line.startsWith("Phone:")) {
                        // Replace the existing phone number with the updated phone number
                        fileContent.append("Phone: ").append(phoneNumber).append("\n");
                    } else {
                        fileContent.append(line).append("\n");
                    }
                }

                // Write the updated content back to the file
                try (FileWriter writer = new FileWriter(infoFile)) {
                    writer.write(fileContent.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Node getContent(Stage primaryStage) {
        return getScene(primaryStage).getRoot();
    }
}
