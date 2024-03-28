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


//Patient LOG IN screen
public class Patient {
    private Scene previousScene;

    public Patient(Scene previousScene) {
        this.previousScene = previousScene;
    }

    public Scene patientLoginScene(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Labels and fields for username and password
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        TextField passwordField = new TextField();

        VBox loginFields = new VBox(10, usernameLabel, usernameField, passwordLabel, passwordField);
        loginFields.setAlignment(Pos.CENTER);
        loginFields.setPadding(new Insets(50));

        //login button
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            //performing login validation here
            //if login is successful, show patient data view
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (validateLogin(username, password)) {
                //navigate to PatientDataView
            	//enters patient view using patient id, patient data view verfies if tests are done
                PatientDataView patientDataView = new PatientDataView(primaryStage, username);
                Scene patientDataScene = patientDataView.getScene();
                primaryStage.setScene(patientDataScene);
            } else {
                //display error message for incorrect credentials
                showError(primaryStage, "Wrong username or password");
            }
               

        });

        // Back Button
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(previousScene);
        });

        HBox backButtonBox = new HBox(backButton);
        backButtonBox.setAlignment(Pos.CENTER);
        
        HBox loginButtonBox = new HBox(loginButton);
        loginButtonBox.setAlignment(Pos.CENTER);
        
        VBox loginView = new VBox(20, loginFields, loginButtonBox, backButtonBox);
        loginView.setAlignment(Pos.CENTER);
        


        root.setCenter(loginView);

        return new Scene(root, 900, 600);
    }

    private boolean validateLogin(String username, String password) {
    	//login validation goes here
    	//for now as long as both fields are not empty you can log in 
        return !username.isEmpty() && !password.isEmpty();
    }

    private void showError(Stage primaryStage, String message) {
        //create and show an error message
        BorderPane root = new BorderPane();
        Label errorLabel = new Label(message);
        errorLabel.setFont(Font.font("Arial", 20));
        errorLabel.setTextFill(Color.RED);
        root.setCenter(errorLabel);
        Scene errorScene = new Scene(root, 400, 200);
        primaryStage.setScene(errorScene);
    }
}
