import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HeartHealthSystem extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 900, 600);

            //setting the title for the main screen UI
            Label titleLabel = new Label("Welcome to Heart Health Imaging and Recording System");
            titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
            root.setTop(titleLabel);
            BorderPane.setAlignment(titleLabel, Pos.CENTER);
            BorderPane.setMargin(titleLabel, new Insets(20));

            //creating buttons for patient intake form and patient view
            Button intakeFormButton = new Button("Patient Intake Form");
            Button patientViewButton = new Button("Patient View");
            Button ctScanViewButton = new Button("CT Scan Tech View");

            //event handlers for the buttons
            intakeFormButton.setOnAction(e -> {
                //create a new atientIntakeForm
                PatientIntakeForm patientIntakeForm = new PatientIntakeForm(scene);
                //set the scene to the patient intake form scene
                primaryStage.setScene(patientIntakeForm.getScene());
            });

            patientViewButton.setOnAction(e -> {
                // create a new instance of Patient with the previous scene
                Patient patient = new Patient(scene);
                // using the patientLoginScene method to get the patient login scene
                primaryStage.setScene(patient.patientLoginScene(primaryStage));
            });

            ctScanViewButton.setOnAction(e -> {
                //show input dialog for patient ID
                String patientId = showPatientIdInputDialog(primaryStage);
                if (patientId != null && !patientId.isEmpty()) {
                    CTScanTechView ctScan = new CTScanTechView(patientId, scene);
                    primaryStage.setScene(ctScan.getScene(primaryStage));
                } else {
                    System.out.println("Patient ID not provided.");
                }
            });

            //layout setup
            VBox buttonsVBox = new VBox(20, intakeFormButton, patientViewButton, ctScanViewButton);
            buttonsVBox.setAlignment(Pos.CENTER);
            BorderPane.setMargin(buttonsVBox, new Insets(100, 0, 0, 0));
            root.setCenter(buttonsVBox);

            primaryStage.setTitle("Heart Health System");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String showPatientIdInputDialog(Stage primaryStage) {
        //create a new stage for the input dialog
        Stage dialogStage = new Stage();
        dialogStage.initOwner(primaryStage);

        //create input fields
        Label label = new Label("Enter Patient ID:");
        TextField textField = new TextField();

        //create OK button
        Button okButton = new Button("OK");
        okButton.setOnAction(e -> {
            //retrieve text from the text field and close the dialog
            dialogStage.close();
        });

        //create layout
        VBox vbox = new VBox(10, label, textField, okButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        //set scene and show dialog
        Scene dialogScene = new Scene(vbox);
        dialogStage.setScene(dialogScene);
        dialogStage.showAndWait();

        //return text from the text field
        return textField.getText();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
