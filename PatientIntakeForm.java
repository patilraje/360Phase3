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

        //patient intake Form Label
        Label titleLabel = new Label("Patient Intake Form");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        //patient information fields
        Label firstNameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();

        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label phoneLabel = new Label("Phone:");
        TextField phoneField = new TextField();

        Label insuranceLabel = new Label("Insurance ID:");
        TextField insuranceField = new TextField();

        GridPane formGrid = new GridPane();
        formGrid.setAlignment(Pos.CENTER);
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.addRow(0, firstNameLabel, firstNameField);
        formGrid.addRow(1, lastNameLabel, lastNameField);
        formGrid.addRow(2, emailLabel, emailField);
        formGrid.addRow(3, phoneLabel, phoneField);
        formGrid.addRow(4, insuranceLabel, insuranceField);

        //save button
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            //get user input
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String insuranceId = insuranceField.getText();

            //creating a unique patient ID (5 digits)
            String patientId = generatePatientId();

            //creating file name based on the patient ID
            String fileName = patientId + "_PatientInfo.txt";

            // Specify the directory path where you want to store the text files
            String directoryPath = "/Users/apple/cse240/360/HW4/patient_info/";
            File directory = new File(directoryPath);
            directory.mkdir(); //create the directory if it doesn't exist

            //create the full file path
            String filePath = directoryPath + fileName;

            //write patient information to the file
            writeToFile(filePath, firstName, lastName, email, phone, insuranceId, patientId);
        });

        //back Button
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

    //this method generates a unique patient ID (5 digits)
    private String generatePatientId() {
        //a simple random 5 digit ID is generated
        return String.format("%05d", (int) (Math.random() * 100000));
    }

    //method to write patient information to a text file
    private void writeToFile(String filePath, String firstName, String lastName, String email, String phone, String insuranceId, String patientID) {
        try (FileWriter writer = new FileWriter(filePath)) {
        	writer.write("Patient ID: " + patientID + "\n");
            writer.write("First Name: " + firstName + "\n");
            writer.write("Last Name: " + lastName + "\n");
            writer.write("Email: " + email + "\n");
            writer.write("Phone: " + phone + "\n");
            writer.write("Insurance ID: " + insuranceId + "\n");
            System.out.println("Patient information saved to file: " + filePath);
        } catch (IOException ex) {
            System.out.println("An error occurred while writing to the file.");
            ex.printStackTrace();
        }
    }
}
