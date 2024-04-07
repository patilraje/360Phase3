import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PatientIntakeForm {

    private Scene previousScene;

    public PatientIntakeForm(Scene previousScene) {
        this.previousScene = previousScene;
    }

    public Scene getScene() {
        BorderPane root = new BorderPane();

        // Patient intake Form Label
        Label titleLabel = new Label("Patient Intake Form");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Patient information fields
        Label firstNameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();

        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();

        Label dateOfBirthLabel = new Label("Date of Birth:");
        TextField dateOfBirthField = new TextField();

        GridPane formGrid = new GridPane();
        formGrid.setAlignment(Pos.CENTER);
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.addRow(0, firstNameLabel, firstNameField);
        formGrid.addRow(1, lastNameLabel, lastNameField);
        formGrid.addRow(2, dateOfBirthLabel, dateOfBirthField);

        // Save button
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            savePatientInfo(firstNameField.getText(), lastNameField.getText(), dateOfBirthField.getText());
            clearFields(firstNameField, lastNameField, dateOfBirthField);
        });

        // Back Button
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(previousScene);
        });

        HBox buttonBox = new HBox(10, saveButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox formLayout = new VBox(20, titleLabel, formGrid, buttonBox);
        formLayout.setAlignment(Pos.CENTER);
        formLayout.setPadding(new Insets(50));

        root.setCenter(formLayout);

        return new Scene(root, 600, 400);
    }

    private void savePatientInfo(String firstName, String lastName, String dateOfBirth) {
        // Generate file name based on first name and last name
        String fileName = firstName.toLowerCase() + "_" + lastName.toLowerCase() + "_patientinfo.txt";

        // Specify the directory path where patient information files are stored
        String directoryPath = "/Users/apple/cse240/360/HW4/patient_info/";
        File directory = new File(directoryPath);
        directory.mkdir(); // Create the directory if it doesn't exist

        // Create the full file path
        String filePath = directoryPath + fileName;

        // Write patient information to the file
        writeToFile(filePath, firstName, lastName, dateOfBirth);
        System.out.println("Patient information saved to file: " + filePath);

        // You might want to provide feedback to the user here, confirming that the
        // information has been successfully saved
    }

    // Method to write patient information to a text file
    private void writeToFile(String filePath, String firstName, String lastName, String dateOfBirth) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("First Name: " + firstName + "\n");
            writer.write("Last Name: " + lastName + "\n");
            writer.write("Date of Birth: " + dateOfBirth + "\n");
        } catch (IOException ex) {
            System.out.println("An error occurred while writing to the file.");
            ex.printStackTrace();
        }
    }

    // Method to clear input fields after saving patient information
    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }
}
