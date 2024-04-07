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

public class PersonalInformation {
    private String firstName;
    private String lastName;

    public PersonalInformation(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Scene getScene(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Labels for fields
        Label nameLabel = new Label("Name:");
        Label dobLabel = new Label("Date of Birth:");

        // Text fields for editable information
        TextField nameField = new TextField();
        TextField dobField = new TextField();

        // Read and populate fields with existing data
        readAndPopulateFields(nameField, dobField);

        // Button to save changes
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            // Save changes to the file
            saveChanges(nameField.getText(), dobField.getText());
        });

        // Layout setup
        GridPane gridPane = new GridPane();
        gridPane.addRow(0, nameLabel, nameField);
        gridPane.addRow(1, dobLabel, dobField);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(20, gridPane, saveButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(50));

        root.setCenter(vbox);

        return new Scene(root, 400, 300);
    }

    private void readAndPopulateFields(TextField nameField, TextField dobField) {
        File infoFile = new File("/Users/apple/cse240/360/HW4/patient_info/", firstName + "_" + lastName + "_PatientInfo.txt");
        if (infoFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(infoFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Date of Birth:")) {
                        dobField.setText(line.substring(line.indexOf(":") + 2));
                    }
                }
                // Set the name field
                nameField.setText(firstName + " " + lastName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveChanges(String name, String dob) {
        File infoFile = new File("/Users/apple/cse240/360/HW4/patient_info/", firstName + "_" + lastName + "_PatientInfo.txt");
        if (infoFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(infoFile))) {
                StringBuilder fileContent = new StringBuilder();
                String line;
                boolean lastNameUpdated = false; // Flag to check if last name has been updated
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("First Name:")) {
                        // Replace the existing name with the updated name
                        fileContent.append("First Name: ").append(name.split(" ")[0]).append("\n");
                        fileContent.append("Last Name: ").append(name.split(" ")[1]).append("\n");
                    } else if (line.startsWith("Date of Birth:")) {
                        // Replace the existing date of birth with the updated date of birth
                        fileContent.append("Date of Birth: ").append(dob).append("\n");
                    } else if (line.startsWith("Last Name:") && !lastNameUpdated) {
                        // Replace the existing last name line with the new last name
                        fileContent.append("Last Name: ").append(name.split(" ")[1]).append("\n");
                        lastNameUpdated = true; // Set the flag to true to indicate last name has been updated
                    } else if (!line.startsWith("Last Name:")) {
                        // Append other lines except the last name line
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
