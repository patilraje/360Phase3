import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PatientLogin {
    private Scene previousScene;
    private String patientInfoDirectory;

    public PatientLogin(Scene previousScene, String patientInfoDirectory) {
        this.previousScene = previousScene;
        this.patientInfoDirectory = patientInfoDirectory;
    }

    public Scene patientLoginScene(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Labels and fields for first name, last name, and date of birth
        Label firstNameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();

        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();

        Label dobLabel = new Label("Date of Birth (YYYY-MM-DD):");
        TextField dobField = new TextField();

        VBox loginFields = new VBox(10, firstNameLabel, firstNameField, lastNameLabel, lastNameField, dobLabel, dobField);
        loginFields.setAlignment(Pos.CENTER);
        loginFields.setPadding(new Insets(50));

        // Login button
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            // Performing login validation
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String dob = dobField.getText();
            if(validateLogin(firstName, lastName, dob)) {
                // Navigate to PatientDataView
                PatientDataView patientDataView = new PatientDataView(firstName, lastName); // Pass primaryStage
                Scene patientDataScene = patientDataView.getScene(primaryStage); // Remove primaryStage argument
                primaryStage.setScene(patientDataScene);
            } else {
                // Display error message for incorrect credentials
                showError(primaryStage, "Invalid credentials");
            }
        });

        // Back Button
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> primaryStage.setScene(previousScene));

        HBox backButtonBox = new HBox(backButton);
        backButtonBox.setAlignment(Pos.CENTER);
        
        HBox loginButtonBox = new HBox(loginButton);
        loginButtonBox.setAlignment(Pos.CENTER);
        
        VBox loginView = new VBox(20, loginFields, loginButtonBox, backButtonBox);
        loginView.setAlignment(Pos.CENTER);

        root.setCenter(loginView);

        return new Scene(root, 900, 600);
    }

		    private boolean validateLogin(String firstName, String lastName, String dob) {
		        // Read patient information from files and compare with input
		        String resultFileName = firstName.toLowerCase() + "_" + lastName.toLowerCase() + "_patientinfo.txt";
		        //File resultFile = new File("/Users/apple/cse240/360/HW4/patient_result/", resultFileName);
		        try {
		            File patientFile = new File("/Users/apple/cse240/360/HW4/patient_info/", resultFileName);
		            if (!patientFile.exists()) {
		                System.out.println("Patient file does not exist.");
		                return false; // Patient file does not exist
		            }
		            
		            BufferedReader reader = new BufferedReader(new FileReader(patientFile));
		            String line;
		       
		            // Read and discard the first line (header)
		            //reader.readLine();
		            // Read the next three lines for patient information
		            String storedFirstName = reader.readLine().split(":")[1].trim(); // First name
		            //System.out.println(storedFirstName);
		            String storedLastName = reader.readLine().split(":")[1].trim(); // Last name
		            System.out.println(storedLastName);
		            String storedDob = reader.readLine().split(":")[1].trim(); // Date of birth
		            System.out.println(storedDob);
		            reader.close();
		            
		            // Compare stored information with input
		            return firstName.equalsIgnoreCase(storedFirstName) && 
		                   lastName.equalsIgnoreCase(storedLastName) && 
		                   dob.equals(storedDob);
		        } catch (IOException e) {
		            System.out.println("Error reading patient file: " + e.getMessage());
		            return false;
		        } catch (Exception e) {
		            System.out.println("An unexpected error occurred: " + e.getMessage());
		            return false;
		        }
		    }


    private void showError(Stage primaryStage, String message) {
        // Create and show an error message
        BorderPane root = new BorderPane();
        Label errorLabel = new Label(message);
        errorLabel.setFont(Font.font("Arial", 20));
        errorLabel.setTextFill(Color.RED);
        root.setCenter(errorLabel);
        Scene errorScene = new Scene(root, 400, 200);
        primaryStage.setScene(errorScene);
    }
}
